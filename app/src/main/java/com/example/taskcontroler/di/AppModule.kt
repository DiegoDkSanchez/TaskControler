package com.example.taskcontroler.di

import android.content.Context
import com.example.core.interactor.usercase.LoginWithGoogleUseCase
import com.example.data.repository.GoogleManager
import com.example.taskcontroler.extensions.AlertDialogs
import com.example.taskcontroler.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { LoginWithGoogleUseCase(get()) }

    viewModel { LoginViewModel(get(), get()) }

    single { GoogleManager() }
    single { AlertDialogs() }

}