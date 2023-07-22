# PhotoGallery
# Description

This is a native mobile app that uses the Flickr API to display a list of photos. The app displays a list of photos, along with the
poster's userid, user icon, and list of any tags associated with the photo.  Tapping on the photo takes the user to a
separate page wherein they can see more detail about the photo. Tapping on a user photo takes the user to a list of
photos by that user.

API's used
  1. flickr.photos.getRecent
  2. flickr.people.getPublicPhotos

 https://www.flickr.com/services/rest/?
 
# Libraries Used
* Retrofit - Retrofit as a REST Client library
* Gson - Gson converter as a Retrofit response mapping library
* Hilt - Dependency injection
* Navigation - Jetpack's navigation component

# Android components
* RecyclerView - Used to list the Albums.
* Constraint layout - Simple, flat hierarchies in a layout.
* Coroutines – For Asynchronous or non-blocking programming
* Flow - Handle streams of data asynchronously
* View binding – To bind the data with the UI and to improve the performance, prevent memory leaks and null pointer exceptions.

# Android Architecture Components (Android Jetpack)
* ViewModel - Allows data to survive configuration changes such as screen rotations.
* LiveData - Lifecycle-aware data holder class to respect the lifecycle Fragments.
* AndroidX - Complete project implemented using AndroidX libraries.

# Design
* The application is developed with TDD and MVVM design patterns.

# Further enhancements
* Handle configuration changes
* Furthermore cosmetics and refactoring is an endless thought.

# Snapshots
![Screenshot_1](https://github.com/vinuwilson/PhotoGallery/assets/5424714/bd8c9a77-e6d2-46e2-81b0-da3f7072bbd7)

![Screenshot_2](https://github.com/vinuwilson/PhotoGallery/assets/5424714/98d4ae7a-4b9f-4c47-9a10-9eaad2c6d359)

![Screenshot_3](https://github.com/vinuwilson/PhotoGallery/assets/5424714/17869090-fecb-4432-9485-0149adafe55a)

![Screenshot_4](https://github.com/vinuwilson/PhotoGallery/assets/5424714/460dd077-180a-4378-9661-1775b5864951)

![Screenshot_5](https://github.com/vinuwilson/PhotoGallery/assets/5424714/56969b2a-1e3f-461c-b84f-737435a7c9d6)
