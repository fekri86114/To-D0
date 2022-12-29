# 📱 To-D0
A simple application for planning your day! Developed with Kotlin, Android-XML. Used: RecyclerView, Room(Dao, Datanase, Entity), ...

# 📝 Dependency
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

# ❕ More
I got help from https://github.com/tanyagupta0201/ToDo-List-App

I updated some parts of the code. An I used this project for learning and working with Room.

Happy codding!! 😈😄
