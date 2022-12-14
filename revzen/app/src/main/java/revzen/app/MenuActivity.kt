package revzen.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import revzen.app.api.*

class MenuActivity : AppCompatActivity() {
    private lateinit var apiHandler: ApiHandler

    private lateinit var usernameText: TextView
    private lateinit var friendCodeText: TextView
    private lateinit var petXP: TextView

    private lateinit var petImage: ImageView
    private lateinit var petHealthImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu)
        // Assume it has been passed to the intent
        apiHandler = intent.extras?.getParcelable("handler")!!
        usernameText = findViewById(R.id.usernameTextView)
        friendCodeText = findViewById(R.id.userFriendCodeTextView)
        usernameText.text = apiHandler.username
        friendCodeText.text = apiHandler.friendCode.toString()

        petImage = findViewById(R.id.menuPetImageView)
        petHealthImage = findViewById(R.id.menuHealthImageVIew)
        petXP = findViewById(R.id.menuXPTextView)
    }

    override fun onStart() {
        super.onStart()
        apiHandler.getCurrentPet(this::successGet, this::failGet)
    }

    fun goToSessionSetup(_view: View) {
        startActivity(Intent(this, SetupActivity::class.java).apply {
            putExtra(
                "handler",
                apiHandler
            )
            putExtra("studyList", ArrayList<SessionData>())
        })
    }

    fun goToRevisionHistory(_view: View) {
        startActivity(Intent(this, StudyHistoryActivity::class.java).apply {
            putExtra(
                "handler",
                apiHandler
            )
        })
    }

    fun goToLiveRevision(_view: View) {
        startActivity(Intent(this, LiveRevisionActivity::class.java).apply {
            putExtra(
                "handler",
                apiHandler
            )
        })
    }

    fun goToFollowScreen(_view: View) {
        startActivity(Intent(this, FollowActivity::class.java).apply {
            putExtra(
                "handler",
                apiHandler
            )
        })
    }

    fun goToPetSelect(_view: View) {
        startActivity(Intent(this, PetSelectActivity::class.java).apply {
            putExtra(
                "handler",
                apiHandler
            )
        })
    }

    @SuppressLint("SetTextI18n")
    private fun successGet(info: PetStatus) {
        petImage.setImageResource(info.petType.logoImage)
        if (info.petType == Pet.ROCK) {
            petXP.visibility = View.INVISIBLE
            petHealthImage.visibility = View.INVISIBLE
        } else {
            petHealthImage.setImageResource(info.health.image)
            petXP.text = info.xp.toString() + " XP"
            petXP.visibility = View.VISIBLE
            petHealthImage.visibility = View.VISIBLE
        }
        petImage.visibility = View.VISIBLE
    }

    private fun failGet(error: ApiError) {
        AlertDialog.Builder(this).apply {
            setTitle("Error")
            setMessage(
                when (error) {
                    ApiError.NO_SUCH_USER -> R.string.login_failure_no_such_user
                    ApiError.WRONG_VERSION -> R.string.login_failure_outdated_api
                    else -> R.string.login_failure_unspecified_api_error
                }
            )
            setPositiveButton("Ok") { _, _ -> finish() }
            create()
            show()
        }
    }
}