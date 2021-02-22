# Blog-App
### This is a blog app that reads a list of existing blogs from an API and persists them on the device. It allows the user to add new posts to the blog, and comments to the posts.

## App Flow
1. At the homescreen of the blog, the user is required to input their name. This is saved and the user is taken to the next screen.
2.  In the next screen the user sees a list of posts gotten from the internet. The user can open each post to read them, and the comments below the posts.
3. A button at the bottom right of the screen takes the user to a screen to add new comments. 
4. When a comment is added, the username saved initialy is tagged against the new comment.
5. The app also allows the user to add their own posts. From the list of posts, the user can click on a button at the bottom of the screen to add new posts.
7. Each new post has a comment section, where new comments can be added.

## Technologies Used
* Android
* Kotlin
* Viewmodel
* Livedata
* Retrofit
* Room Database
* Navigation Component
