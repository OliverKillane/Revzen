# Group 28 DRP

## Description
DRP group 28 featuring Nurdayana Muhd Faisal, Alfie Chenery, Miles Nash and Oliver Killane. Developing an app-based solution to prevent revision procrastination and study session distraction by mobile devices.

The video presented of the problem can be found [here](https://drive.google.com/file/d/1YDk0OAX-id19LFdZmLnov0wUPu3iIrON/view).

<img src="https://user-images.githubusercontent.com/44177991/194718315-e5ecc53a-61dd-4afe-886b-97f8f7f570fd.png" alt="drawing" width="180"/> <img src="https://user-images.githubusercontent.com/44177991/194718318-5684129e-82d8-4a3d-970a-d1f714b98dd0.png" alt="drawing" width="180"/> <img src="https://user-images.githubusercontent.com/44177991/194718328-f40219b8-29d1-4842-b50a-cbdd3a3f8779.png" alt="drawing" width="180"/> <img src="https://user-images.githubusercontent.com/44177991/194718324-e3e3916c-5053-4ac5-a5af-6d2dc38df8be.png" alt="drawing" width="180"/> <img src="https://user-images.githubusercontent.com/44177991/194718322-d712c46b-72d2-46b6-a128-823b8d96b60f.png" alt="drawing" width="180"/>

## Presentation
The [final presentation slides](presentation/final_presentation_slides.pdf) and [marketing material](presentation/project_pitch_leaflet.pdf) can be found in `presentation/`.

## Functionality
This project is no longer actively maintained, and has been mirrored from gitlab. The Heroku deployment no longer runs & URIs are invalid. The CI/CD for this project was developed for gitlab & is not functional on github.

## Documentation:
The documentation for the backend is generated from the `dev` branch by our CI and can be found [here](https://ok220.pages.doc.ic.ac.uk/group-28-drp/backend_doc/revzen_backend/index.html).

## Git Hygiene
Project includes marks for proper repository management. This project is no longer actively developed, and the CI is for gitlab, so is not functional here.

### Dev Branch
When making changes for new features, branch from, and eventually merge into the `dev` branch. Master is used for the public facing, released version. This allows for the group to test for bugs, use and experience new features, and show new feature developments to stakeholders before publicly releasing. 

When merging into `dev`, deployment is done for our internal testing. Public deployment is done in master.

1. Branch from `dev`
2. Work in new branch
3. Create merge request for the feature
4. Get request reviewed, go through cycles of review & improvement.
5. Merge into `dev`, can now use test deployment.
6. `dev` pushed into Master.

### Development Backend
Can be found [here](https://revzen-backend-dev.herokuapp.com/) hosted on heroku.

### Production Backend
Can be found [here](https://revzen-backend-prod.herokuapp.com/) hosted on heroku.
### Branch Names
```
Prefix/name-goes-here
```

| Prefix | Description                                                                                                                                                            |
|--------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| enh    | An enhancement, either a new feature, or improving an existing one. CI and test changes included. This should change both code behaviour and what behaviour we expect. |
| fix    | A bug fix to correct deviation of behaviour from expected.                                                                                                             |
| doc    | Adding documentation, should not change any code behaviour! Comments only.                                                                                             |
| ref    | Refactoring of code, includes code changes but should not change behaviour.                                                                                            |
| any    | Anything not in the other categories.                                                                                                                                  |
### Commits
Commits must have a descriptive name, and contain the shortcodes of those working on the commit.
```
In enh/show-users-online:

Added green dot to show online status in friends menu
[ ac320, ok220 ]
```

### Merge Requests
- All merge requests need to be reviewed by another team member.
- Ideally before merging, the branch should be rebased against `dev`, though careful merging is fine.

For rebasing, simply go to the branch and use:
```bash
git rebase -i dev  # -i for interactive, and always against dev
```
Can then pick changes, save & add before using
```bash
git rebase --continue
```
Once the rebase is complete, a force push is required
```bash
git push --force
```
