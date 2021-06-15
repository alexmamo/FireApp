# FireApp - Clean Architecture

[FireApp][1] is an open-source project that is built around [Firebase Products][2], especially for **learning purposes**. This application is written entirely in [Kotlin][3] using [Android Architecture Components][4] and MVVM architecture pattern. You'll see in the code of this repo, how Firebase Products are working together.

Below you can find the docs for each tehnology that is used in this app:

## Firebase Products:
* [Firebase Authentication][5] using [Google Provider][6]
* [Cloud Firestore][7]
* [Firebase Realtime Database][8]
* [Cloud Storage for Firebase][16]

## Android Architecture Components:
* [LiveData][9]
* [ViewModel][10]

## Dependency Injection:
* [Hilt for Android][11]

## Asynchronous Programming:
* [Kotlin Coroutines][12]
* [Asynchronous Flow][13]

## Other Android Components:
* [Android Navigation Component][14]
* [View Binding][19]

## Other Libraries:
* [Glide for Android][15]

If you download or clone this repo, in order to make it work, you should follow the instructions given in the official documentation regarding on [how to add Firebase to your project][15].

In case of Cloud Firestore, you can use a List of Movie objects as below:
  
    val movies = listOf(
        Movie("A Love Affair In Transit", 1994, 9.2, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Adrift", 1972, 9.1, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("All About You", 1974, 9.0, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Blood Bone And Beasts", 2008, 9.0, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Changing To Victory", 1957, 8.9, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Down The Rabbit Hole", 1993, 8.9, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("En Route", 2003, 8.9, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Into The Darkness", 1994, 8.8, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Lighting Shadows", 1966, 8.8, ", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.""),
        Movie("Locked", 2001, 8.8, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Lost Girl", 1999, 8.8, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Love Africa", 1994, 8.7, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Movie Night", 2010, 8.7, ""),
        Movie("Outdoor Movie Night", 2002, 8.7, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("She", 1980, 8.7, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("The Great Big Bridge", 1999, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("The Life Of Richard Parker", 1990, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("The Lost Girl", 1975, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("The Love Of Mine", 1954, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("The Night Is Young", 1995, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("The Walk", 1991, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("The Way We Get By", 2002, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Thriller Movie Night", 1946, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Top Of The World", 1997, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
        Movie("Train To Hell", 1977, 8.6, "", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
    )
  
  As you can see, there is no URL in the Movie object, so you should populate that value your self. Here is an [archive][]. You can do it using Cloud Storage as I did, or any other Hosting Service. In case of the Realtime Database, you can use the same list, but you should change the value of "rating" to a negative one. Why? So we can simply order the results accoding to that field.
  
![alt text](https://i.ibb.co/B48LgJb/AllNew.png)

You can also check the following article writen on Mediul publication:

* [How to create an Android app using multiple Firebase products in Kotlin?][18]

**License**
---
The code in this project is licensed under the Apache License 2.0.

    Copyright 2018 Google LLC

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

**Disclaimer**
---
* This is not an officially supported Google product.

[1]: https://play.google.com/store/apps/details?id=ro.alexmamo.firebase
[2]: https://firebase.google.com/
[3]: https://kotlinlang.org/
[4]: https://developer.android.com/topic/libraries/architecture
[5]: https://firebase.google.com/products/auth
[6]: https://firebase.google.com/docs/auth/android/google-signin
[7]: https://firebase.google.com/docs/firestore
[8]: https://firebase.google.com/docs/database
[9]: https://developer.android.com/topic/libraries/architecture/livedata
[10]: https://developer.android.com/topic/libraries/architecture/viewmodel
[11]: https://developer.android.com/training/dependency-injection/hilt-android
[12]: https://kotlinlang.org/docs/coroutines-overview.html
[13]: https://kotlinlang.org/docs/flow.html
[14]: https://developer.android.com/guide/navigation
[15]: https://github.com/bumptech/glide
[16]: https://firebase.google.com/docs/storage
[17]: http://alexmamo.ro/apps/FireApp/movie_posters.rar
[18]: https://medium.com/firebase-tips-tricks/how-to-create-an-android-app-using-multiple-firebase-products-in-kotlin-16aade81ffec
[19]: https://developer.android.com/topic/libraries/view-binding
