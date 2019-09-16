package com.example.seconddaggerbuilderapp.api

import com.example.seconddaggerbuilderapp.models.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

}