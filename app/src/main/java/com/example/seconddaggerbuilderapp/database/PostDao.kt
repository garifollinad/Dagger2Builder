package com.example.seconddaggerbuilderapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seconddaggerbuilderapp.models.Post
import io.reactivex.Single

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    @Query("select * from posts")
    fun getPosts(): Single<List<Post>>
}