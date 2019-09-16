package com.example.seconddaggerbuilderapp.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.seconddaggerbuilderapp.R
import com.example.seconddaggerbuilderapp.models.Post
import kz.mobile.mgov.common.di.Injectable
import javax.inject.Inject

class PostFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var posts: ArrayList<Post>? = null
    private var postTv: TextView ?= null

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PostViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            bindViews(view)
            setData()
        }
    }

    private fun bindViews(view: View) = with(view) {
        postTv = findViewById(R.id.postTv)
    }

    private fun setData() {
        viewModel.getPost()
        viewModel.getLocalPost()
        viewModel.liveData.observe(this, Observer {
            when(it) {
                is PostViewModel.PostData.HideLoading -> {

                }
                is PostViewModel.PostData.ShowLoading -> {

                }
                is PostViewModel.PostData.Error -> {
                }
                is PostViewModel.PostData.PostResult -> {
                    posts = it.posts
                    Log.d("posts_log", posts.toString())
                    postTv?.text = posts.toString()
                }
                is PostViewModel.PostData.PostLocalResult -> {
                    posts = it.postsLocal
                    Log.d("posts_local", posts.toString())
                    postTv?.text = posts.toString()
                }
            }
        })
    }

}
