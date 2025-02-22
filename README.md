This is an Android app built using Jetpack Compose and MVVM architecture. The app allows users to:

View a list of users.
Search for a user by ID.
Refresh the user list with pull-to-refresh.
Add new users by navigating to an edit screen.
Features
Search: Enter a user ID in the search bar to filter users.
Live Data: Observes changes in user data automatically.
Pull-to-Refresh: Swipe down to refresh the user list.
Error Handling: Displays error messages with retry options.
Loading States: Shows shimmer effects while loading data.
Screens

Home Screen
Shows a greeting message.
Search bar for filtering users by ID.
Displays a list of all users or a single user if found.
Add User
Navigate using the Add button to add new users.
HomeViewModel: Handles fetching and managing user data.
LocationCard: Displays user details (Name, Country, Latitude, Longitude).
inputvalidator(): Validates that the user ID is at least 4 characters long.
Requirements
Kotlin
Jetpack Compose
Hilt for Dependency Injection
Accompanist for Shimmer and Pull-to-Refresh effects

How to Run
Clone the repository.
Open the project in Android Studio.
Build and run on an emulator or physical device.
