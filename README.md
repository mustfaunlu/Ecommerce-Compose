<h1 align="center">E-commerce Compose App</h1></br>
<p align="center">  
A sample e-commerce app built to demonstrate the use of Modern Android development tools. E-commerce App is a modern Android application that uses the latest tools and Jetpack Compose. Built with Kotlin and Android Architecture Components, it uses the latest cutting-edge technologies, components, and patterns. 
MVVM + Clean Architecture approach.

</p></br>

<p align="center">
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/mustfaunlu"><img alt="Profile" src="https://img.shields.io/badge/github-mustfaunlu-blue"/></a> 
</p>

## Screenshots (Dark Theme & Light Theme)

### App videos are available at the bottom of the page.
Just portrait mode is supported for now.
This app is still under development and more screens will be added soon.

| SCREEN SIGNUP                     | SCREEN SIGNIN                   | SCREEN RESETPW                 | SCREEN HOME                  | SCREEN DETAIL                  | SCREEN FAV                  | SCREEN CART                  | SCREEN PAYMENT                  | SCREEN PROFILE                  |
|-----------------------------------|---------------------------------|--------------------------------|------------------------------|--------------------------------|-----------------------------|------------------------------|---------------------------------|---------------------------------|
| ![](/previews/create_account.png) | ![](/previews/login_screen.png) | ![](/previews/forgot_pass.png) | ![](/previews/home.png)      | ![](/previews/detail.png)      | ![](/previews/fav.png)      | ![](/previews/cart.png)      | ![](/previews/payment.png)      | ![](/previews/profile.png)      |
| ![](/previews/create_dark.png)    | ![](/previews/login_dark.png)   | ![](/previews/forgot_dark.png) | ![](/previews/home_dark.png) | ![](/previews/detail_dark.png) | ![](/previews/fav_dark.png) | ![](/previews/cart_dark.png) | ![](/previews/payment_dark.png) | ![](/previews/profile_dark.png) |


## Tech stack & Open-source libraries

- Minimum SDK level 24
- 100% [Kotlin](https://kotlinlang.org/)
  based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
  and [Flow](https://developer.android.com/kotlin/flow) & [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
    - A single-activity architecture, using
      the [Navigation Component](https://developer.android.com/guide/navigation) to manage composable navigation.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an
      action when lifecycle state changes
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn't destroyed on UI changes.
    - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain
      layer that sits between the UI layer and the data layer.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data
      layer that contains application data and business logic.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) -
  Dependency Injection Library
- [Retrofit](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java
- [OkHttp](https://square.github.io/okhttp/) An HTTP client that efficiently make network requests
- [Moshi](https://github.com/square/moshi) Moshi is a modern JSON library for Android, Java and
  Kotlin. It makes it easy to parse JSON into Java and Kotlin classes.
- [Room](https://developer.android.com/training/data-storage/room) The Room persistence library
  provides an abstraction layer over SQLite to allow for more robust database access while
  harnessing the full power of SQLite.
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) WorkManager
  is an API that makes it easy to schedule deferrable, asynchronous tasks that are expected to run
  even if the app exits or device restarts.
- [Local Notifications](https://developer.android.com/training/notify-user/build-notification) A
  notification is a message that Android displays outside your app's UI to provide the user with
  reminders, communication from other people, or other timely information from your app.
- [kJWT](https://github.com/nefilim/kjwt) Functional Kotlin & Arrow based library for generating and
  verifying JWTs and JWSs.
- [Firebase](https://firebase.google.com/) - Used for authentication, crashlytics, analytics,
  firestore and messaging.
    - [Firebase Authentication](https://firebase.google.com/docs/auth) Firebase Authentication
      provides backend services, easy-to-use SDKs, and ready-made UI libraries to authenticate users
      to your app.
    - [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) Firebase Crashlytics is a
      lightweight, realtime crash reporter that helps you track, prioritize, and fix stability
      issues that erode your app quality.
    - [Firebase Analytics](https://firebase.google.com/docs/analytics) Firebase Analytics is a free
      app measurement solution that provides insight on app usage and user engagement.
    - [Firebase Firestore](https://firebase.google.com/docs/firestore) Cloud Firestore is a
      flexible, scalable database for mobile, web, and server development from Firebase and Google
      Cloud.
    - [Firebase Messaging(FCM)](https://firebase.google.com/docs/cloud-messaging) Firebase Cloud
      Messaging (FCM) is a cross-platform messaging solution that lets you reliably send messages at
      no cost.
- [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences) Store
  private primitive data in key-value pairs.
- Testing
    - [Mockito](https://site.mockito.org/) A mocking framework that tastes really good. It lets you
      write beautiful tests with a clean & simple API
    - [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) A scriptable web
      server for testing HTTP clients
    - [Truth](https://truth.dev/) A library for performing assertions in tests
    - [Turbine](https://github.com/cashapp/turbine) A small testing library for kotlinx.coroutines
      Flow

## Architecture

This app
uses [MVVM (Model View View-Model) + Clean Architecture](https://developer.android.com/jetpack/docs/guide#recommended-app-arch)
architecture

![](https://user-images.githubusercontent.com/21035435/69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0.png)

## API

E-commerce App uses the [DummyJson](https://dummyjson.com/) for constructing RESTful API.<br>
And also uses [Firebase](https://firebase.google.com/) for authentication, crashlytics, analytics,
firestore and messaging.

## App Videos

- Login as a firebase user: Create your own user from the "Create Account" screen.</br>



- If the application is not in the foreground or background and the notification settings are
  enabled, a local notification is sent every 15 minutes</br>



- Firebase push notification sample</br>



- JWT token expiration sample: Our application automatically refreshes the JWT token every 3
  minutes. If the token has expired and the application is sent to the background, upon returning to
  the foreground, you will be directed to the login screen. This ensures the security of your
  session and requires you to log in again only if necessary.</br>


