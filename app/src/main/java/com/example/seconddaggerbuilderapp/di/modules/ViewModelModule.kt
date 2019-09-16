package com.example.seconddaggerbuilderapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seconddaggerbuilderapp.di.ViewModelKey
import com.example.seconddaggerbuilderapp.di.factories.DaggerViewModelFactory
import com.example.seconddaggerbuilderapp.ui.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    internal abstract fun provideStandardSignInViewModel(viewModel: PostViewModel): ViewModel

}