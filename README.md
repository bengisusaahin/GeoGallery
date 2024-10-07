<h1 align="center">
GeoGallery
</h1>

<p align="center">
  <img src="https://img.shields.io/badge/-Kotlin-7c6fe1?style=flat&logo=kotlin&logoColor=white">
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285f4?style=flat&logo=jetpackcompose&logoColor=white">
</p>

<div align="center">
  
  [![Contributors][contributors-shield]][contributors-url]
  [![Forks][forks-shield]][forks-url]
  [![Stargazers][stars-shield]][stars-url]
  [![Issues][issues-shield]][issues-url]

</div>

GeoGallery is an application designed to allow users to select images from their device's gallery, view them, and display associated location information. This intuitive platform enhances the user experience by combining image viewing with geolocation data.


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#key-features">Key Features</a>
    </li>
    <li><a href="#demo">Demo</a></li>
    <li><a href="#demo">Requirements</a></li>
    <li><a href="#open-source-libraries">Open-Source Libraries</a></li>
    <li><a href="#architecture">Architecture</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
    </li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#licence">Licence</a></li>
  </ol>
</details>

<!-- KEY FEATURES -->
## Key Features
- Select Images: Users can easily select images from their device's gallery.
- View Image Locations: Display geolocation information (latitude and longitude) for the selected images.
- Date and Time: Show the date and time associated with the image.
- User-Friendly Interface: An intuitive and easy-to-navigate UI built with Jetpack Compose.


With GeoGallery, users can explore their images along with geographical context, making it a valuable tool for documenting memories and locations.

<!-- Demo -->
## Demo

## Requirements
- Android 7.0 (Nougat) or higher (Min SDK 24)
- Kotlin 1.5.1 or higher

<!-- Open-Source Libraries -->
## Open-Source Libraries
* Minimum SDK level 26
* [Dependency Injection (Hilt) (2.51.1)](https://developer.android.com/training/dependency-injection/hilt-android) - Used for dependency injection, simplifying the management of application components.
* [Jetpack Compose (1.9.1)](https://developer.android.com/develop/ui/compose) - A modern toolkit for building native UI in Android.
* [Compose Lifecycle (2.8.4)](https://developer.android.com/develop/ui/compose/lifecycle) - Manages lifecycle-aware components in Jetpack Compose.
* [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel#implement) - A design pattern used to separate concerns, making the application more modular, testable, and maintainable.
  * [Lifecycle (2.8.4)](https://developer.android.com/topic/libraries/architecture/lifecycle) - Manages Android lifecycle-aware components.
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn't destroyed on UI changes.
  * [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain
      layer that sits between the UI layer and the data layer.
  * [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in the data
      layer that contains application data and business logic.
* [Coil (2.6.0)](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed by Kotlin Coroutines.
  
<!-- Architecture -->
## Architecture
This Android app uses the MVVM (Model-View-ViewModel) pattern and Clean Architecture principles, organized into four main modules for better scalability and maintainability.

MVVM

- Model: Manages data and business logic, separate from the UI.
- View: Displays the data and interacts with the user.
- ViewModel: Connects the View and Model, handling UI-related logic and state management.

Clean Architecture & Multi Module
- App Module: The core module that integrates all other modules and provides the main entry point of the application.
- Data Module: Handles data sources, such as APIs and databases, and provides data to the Domain Layer.
- Domain Module: Contains the core business logic and use cases, which are independent of external frameworks.
- Feature Module: Encapsulates the app's features, allowing for modular development and testing of individual functionalities.

<!-- GETTING STARTED -->
## Getting Started
  * If You Want to Run the App on Android Studio:

1. Clone this repository to your preferred directory using the following command:

```
git clone https://github.com/bengisusaahin/GeoGallery.git
```
2. Open the cloned project in Android Studio.

3. Build and run the app on an emulator or a physical device.

----------------------------------------------------------------

* Or you can download the APK of the application [here](https://drive.google.com/file/d/1RgIxpYJLNMky4nM836JD1FeD_0PJgiN8/view?usp=sharing)
  
<!-- Contact Section -->
## Contact

<table style="border-collapse: collapse; width: 100%;">
  <tr>
    <td style="padding-right: 10px;">Bengisu Şahin - <a href="mailto:bengisusaahin@gmail.com">bengisusaahin@gmail.com</a></td>
    <td>
      <a href="https://www.linkedin.com/in/bengisu-sahin/" target="_blank">
        <img src="https://img.shields.io/badge/linkedin-%231E77B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" alt="linkedin" style="vertical-align: middle;" />
      </a>
    </td>
  </tr>  
</table>

<!-- LICENCE -->
## Licence
This project is licensed under the [Apache Licence](https://github.com/bengisusaahin/GeoGallery?tab=Apache-2.0-1-ov-file) 

<!-- [linkedin-shield]: https://img.shields.io/badge/linkedin-%231E77B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white alt=linkedin style="margin-bottom: 5px;"
[linkedin-url]: https://www.linkedin.com/in/bengisu-sahin/
