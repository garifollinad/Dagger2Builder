package com.example.seconddaggerbuilderapp.repository

import android.util.Log
import com.example.seconddaggerbuilderapp.api.PostApi
import com.example.seconddaggerbuilderapp.database.Database
import com.example.seconddaggerbuilderapp.models.Post
import io.reactivex.Single
import javax.inject.Inject

interface PostDaoRepository {
    fun getLocalDaoPosts() : Single<List<Post>>
    fun getDaoPosts() : Single<List<Post>>
    fun insertPosts(posts: List<Post>)
}

class PostDaoRepositoryImpl  @Inject constructor(val database: Database, val api: PostApi) : PostDaoRepository {

    override fun getLocalDaoPosts(): Single<List<Post>> {
        return database.postDao().getPosts()
    }

    override fun insertPosts(posts: List<Post>) {
        database.postDao().insertPosts(posts)
    }

    override fun getDaoPosts() : Single<List<Post>> {
        return api.getPosts()
            .map { list ->
                Log.d("posts_log", list.toString())
                list
            }
    }


}