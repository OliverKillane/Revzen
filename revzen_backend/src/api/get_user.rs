//! API method to retrieve basic user data from a friendcode.
//!
//! ## Post Request Fields:
//!
//! | Key            | Type    | Value                                       |
//! |----------------|---------|---------------------------------------------|
//! | user_id        | integer | The google provided 'sub'/subject id.       |
//! | client_version | integer | The api version being used.                 |
//! | friendcode     | integer | The friendcode of the user's data requested |
//!
//! ## Response:
//!
//! | Status            | Meaning                             |
//! |-------------------|-------------------------------------|
//! | 200 - OK          | Session was successfully added      |
//! | 404 - Not Found   | No such user was found.             |
//! | 410 - Gone        | No user has that friendcode         |
//!
//! In event of a 200 - OK the following json is returned
//! ```json
//! {
//!     "username": <user's username: string>
//! }
//! ```
//!
//! ## CURL Example:
//! ```bash
//! curl -X POST -F 'user_id=301' -F 'version=4' -F 'friendcode=2' 'http://127.0.0.1:8000/api/get_user'
//! ```

use crate::{models::User, *};
use rocket::serde::json::Json;

#[derive(FromForm)]
pub struct GetUserRequest {
    #[field(name = uncased("user_id"))]
    user: UserID,

    #[field(name = uncased("version"), validate = eq(BACKEND_VERSION))]
    #[allow(dead_code)]
    client_version: AppVer,

    #[field(name = uncased("friendcode"))]
    friendcode: FriendCode,
}

#[post("/get_user", data = "<user_req>")]
pub(crate) async fn api_get_user(
    db: RevzenDB,
    user_req: Form<GetUserRequest>,
) -> Result<Json<UserDetails>, Status> {
    use crate::schema::users::dsl::{friendcode as friend_code, main_pet, username, users};

    let GetUserRequest {
        user,
        client_version: _,
        friendcode,
    } = user_req.into_inner();

    if db
        .run(move |c| users.find(user).first::<User>(c))
        .await
        .is_ok()
    {
        if let Ok((name, pet)) = db
            .run(move |c| {
                users
                    .filter(friend_code.eq(friendcode))
                    .select((username, main_pet))
                    .first::<(String, i32)>(c)
            })
            .await
        {
            Ok(Json(UserDetails {
                friendcode,
                username: name,
                main_pet: pet,
            }))
        } else {
            Err(Status::Gone)
        }
    } else {
        Err(Status::NotFound)
    }
}
