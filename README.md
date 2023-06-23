# CatsKMM

## Kotlin Multiplatform app.

The Android UI is implemented using Jetpack Compose, while the iOS UI is implemented using SwiftUI. Each UI has its own set of ViewModels. The API and repository are shared between iOS and Android, and they are written in Kotlin. For API calls, we utilize Ktor, and dependency injection is handled with Koin.

This app retrieves data from [The Cat API](https://thecatapi.com). It consists of two screens. The first screen displays a list of all cat breeds, along with their photos, country of origin flags, and temperaments. Users can filter the list by typing the breed's name in the search bar. Tapping on a list item redirects the user to the details screen, where they can view additional information and more photos of the cat breed.


Kotlin multiplatform mobile (KMM/KMP):
Pros:
- Can scale existing app with KMP (add new functionality to the native iOS/Android app, current codebase can cohabit with KMM)
- Easily add platform specific code (example native libraries, don't need 3rd party libraries for native APIs)
- Android UI Jetpack Compose (again Kotlin), iOS in SwiftUI, native look of the apps (Jetpack Compose can be used for both, but it is still in alpha phase, lose native look)
- Kotlin has compile-time safety (find bugs early)
- One shared code for business logic, one test for both platforms, no duplicated code, DRY (don't repeat yourself)
- Shared code: networking, data storage, validation, business logic (both platform similar/identical use cases, code that is decoupled from underlying platform)
- Speeds up development time, increase productivity
- Build native code, native performance (better than Flutter, ReactNative,..)
- Easy adoption for Android developers
- Knowledge shared between iOS and Android developers


Cons:
- It is in beta phase, not fully matured, less resources online, not production ready?
- Might have a lot of changes in near future, non-backward compatible changes
- iOS limitations, Kotlin translated to Objective-C (no support for Swift yet?), some issues while translating with types, harder to debug on iOS etc.
- Can only use multiplatform libraries written 100% in Kotlin (not Java) (no Dagger-Hilt, Retrofit,...use Koin, Ktor, SQLDelight, Apollo,...)
- More complex to migrate to iOS, learning curve for iOS devs, XCode not fully integrated for KMM (still need to use Android studio for shared code), will Apple ever support a Google product or will we have to use third party libraries/plugins (TouchLabs' xcode-kotlin plugin) forever
- Android devs have to learn Xcode/Swift/iOS, so that they are sure shared Kotlin code works on both platforms
- Doesn't fully support Coroutines, Codable, Default argument or Swift enums, wrap Flow in CommonFlow
- Small community, less resources online, less libraries
- Google has Flutter which is a cross-platform solution
- How to structure in version control system? Mono repository (less alignment debt?), 2 repositories (Android&shared, Swift), 3?
- Modularity on iOS, dependencies duplicated, each module has its own copy (https://medium.com/xorum-io/three-framework-problem-with-kotlin-multiplatform-mobile-16267c5afa53)