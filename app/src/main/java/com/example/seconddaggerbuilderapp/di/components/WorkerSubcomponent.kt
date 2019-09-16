package com.example.seconddaggerbuilderapp.di.components

import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.example.seconddaggerbuilderapp.worker.PostWorker
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Provider

@Subcomponent(modules = [
    PostWorker.Builder::class
])
interface WorkerSubcomponent {

    fun workers(): Map<Class<out RxWorker>, Provider<RxWorker>>

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun workerParameters(param: WorkerParameters): Builder

        fun build(): WorkerSubcomponent
    }
}