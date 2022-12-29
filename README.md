# To-D0
A simple application for planning your day! Developed with Kotlin, Android-XML, Calendar, ...

# Dependency
to add plugins `build.gradle:app`:
     
     plugins {
     
          ...
          id 'kotlin-android-extensions'
          id 'kotlin-kapt'
     }
     
     ...

     android {
     
          ...
  
          buildFeatures {
               viewBinding true
          }
  
     }
     
     dependencies {
          
          ...
          
          def room_version = "2.4.3"
          implementation "androidx.room:room-runtime:$room_version"
          implementation "androidx.room:room-ktx:$room_version"
          kapt "androidx.room:room-compiler:$room_version"
     }

# More
I got help from https://github.com/tanyagupta0201/ToDo-List-App
I updated some part of code
