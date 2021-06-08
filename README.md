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

## Other Libraries:
* [Glide for Android][15]

If you download or clone this repo, in order to make it work, you should follow the instructions given in the official documentation regarding on [how to add Firebase to your project][15].

In case of Cloud Firestore, you can use a List of Movie objects as below:
  
    val movies = listOf(
        Movie("A Love Affair In Transit", 1994, 9.2, ""),
        Movie("Adrift", 1972, 9.1, ""),
        Movie("All About You", 1974, 9.0, ""),
        Movie("Blood Bone And Beasts", 2008, 9.0, ""),
        Movie("Changing To Victory", 1957, 8.9, ""),
        Movie("Down The Rabbit Hole", 1993, 8.9, ""),
        Movie("En Route", 2003, 8.9, ""),
        Movie("Into The Darkness", 1994, 8.8, ""),
        Movie("Lighting Shadows", 1966, 8.8, ""),
        Movie("Locked", 2001, 8.8, ""),
        Movie("Lost Girl", 1999, 8.8, ""),
        Movie("Love Africa", 1994, 8.7, ""),
        Movie("Movie Night", 2010, 8.7, ""),
        Movie("Outdoor Movie Night", 2002, 8.7, ""),
        Movie("She", 1980, 8.7, ""),
        Movie("The Great Big Bridge", 1999, 8.6, ""),
        Movie("The Life Of Richard Parker", 1990, 8.6, ""),
        Movie("The Lost Girl", 1975, 8.6, ""),
        Movie("The Love Of Mine", 1954, 8.6, ""),
        Movie("The Night Is Young", 1995, 8.6, ""),
        Movie("The Walk", 1991, 8.6, ""),
        Movie("The Way We Get By", 2002, 8.6, ""),
        Movie("Thriller Movie Night", 1946, 8.6, ""),
        Movie("Top Of The World", 1997, 8.6, ""),
        Movie("Train To Hell", 1977, 8.6, ""),
    )
  
  As you can see, there is no URL in the Movie object, so you should populate that value your self. Here is an [archive][]. You can do it using Cloud Storage as I did, or any other Hosting Service. In case of the Realtime Database, you can use the same list, but you should change the value of "rating" to a negative one. Why? So we can simply order the results accoding to that field.
  
![alt text](https://i.ibb.co/YLKgf2j/All.png)

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
