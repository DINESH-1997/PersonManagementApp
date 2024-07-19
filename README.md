# Person Management App

### Overview

This app is designed using MVVM Clean Architecture and leverages modern Android development practices such as Jetpack Compose, Kotlin, Coroutines, and Room Database. The app allows users to manage a list of persons, including adding, viewing, and persisting this data offline. Additionally, the app features location services to display the user's current latitude and longitude upon permission.

### Features

### Person Management

1. **Architecture**: Built with MVVM Clean Architecture to ensure separation of concerns and maintainability.
2. **UI**: Utilizes Jetpack Compose for a modern, declarative UI.
3. **Language**: Developed using Kotlin.
4. **Concurrency**: Employs Coroutines for asynchronous operations.
5. **Database**:
      * Stores the list of persons in a Room database
      * 2.Ensures data persistence, allowing offline access to the list of persons.
6. **API Integration:**
      * Uses the CrudCrud API for managing data.
      * After a successful POST request, the new person is added to the local Room database.
  
### Location Services

1. **Permissions**: Requests location permission from the user.
2. **Current Location**: Displays the user's current latitude and longitude if permission is granted.

# Screen Shorts

### Light Theme

<img src="https://github.com/user-attachments/assets/8be31972-bedf-4c0e-976b-550459374744" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/1bcdc9ed-66ac-45cd-9e47-6b4c1ab044eb" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/00dd5597-617b-4135-b205-2d07fbb05c85" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/c248103a-c9ce-49c9-a8dd-504e19ce5854" width="200" height="400" />

### Dark Theme

<img src="https://github.com/user-attachments/assets/ecb39a66-1d48-4ca8-9431-047b9b083778" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/45922c16-29fc-4b3f-b42f-ab7bc9ebc209" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/c850490b-879a-473a-9b7a-8880b869591f" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/c23910b4-add9-430c-ac40-60a4bde7b4d1" width="200" height="400" />
