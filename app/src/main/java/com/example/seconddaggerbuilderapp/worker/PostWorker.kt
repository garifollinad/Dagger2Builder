package com.example.seconddaggerbuilderapp.worker

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.example.seconddaggerbuilderapp.di.WorkerKey
import com.example.seconddaggerbuilderapp.repository.PostDaoRepository
import com.example.seconddaggerbuilderapp.repository.PostDaoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.reactivex.Single
import javax.inject.Inject

class PostWorker @Inject constructor(
    private val application: Application,
    workerParameters: WorkerParameters,
    private val postDaoRepository: PostDaoRepository
) : RxWorker(application, workerParameters) {

    override fun createWork(): Single<Result> {
        Log.d("yel", "addresses worker startWork()")

        return postDaoRepository.getDaoPosts()
            .map { result ->
                Log.d("yel", result.toString())
                postDaoRepository.insertPosts(result)
                Result.success()
            }
            .doFinally {
                Log.d("yel", "addresses worker endWork()")
            }
    }

    @Module
    abstract class Builder {
        @Binds
        @IntoMap
        @WorkerKey(PostWorker::class)
        abstract fun bindAddressesWorker(worker: PostWorker): RxWorker
    }
}