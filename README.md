# CatsKMM

## Kotlin Multiplatform app.

The Android UI is implemented using Jetpack Compose, while the iOS UI is implemented using SwiftUI. Each UI has its own set of ViewModels. The API and repository are shared between iOS and Android, and they are written in Kotlin. For API calls, we utilize Ktor, and dependency injection is handled with Koin.

This app retrieves data from [The Cat API](https://thecatapi.com). It consists of two screens. The first screen displays a list of all cat breeds, along with their photos, country of origin flags, and temperaments. Users can filter the list by typing the breed's name in the search bar. Tapping on a list item redirects the user to the details screen, where they can view additional information and more photos of the cat breed.
