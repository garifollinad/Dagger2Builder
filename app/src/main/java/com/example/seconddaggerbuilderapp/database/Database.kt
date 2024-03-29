package com.example.seconddaggerbuilderapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.seconddaggerbuilderapp.models.Post

@Database(entities = [Post::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun postDao(): PostDao
}