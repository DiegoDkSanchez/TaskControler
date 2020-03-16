package com.example.taskcontroler.presentation.login

import com.example.core.model.User
import com.example.data.repository.GoogleManager
import com.example.taskcontroler.extensions.AlertDialogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class LoginViewModelTest{

    private val dispatchers = TestCoroutineDispatcher()
    lateinit var viewModel: LoginViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(dispatchers)
        runBlocking {
            viewModel = Mockito.mock(LoginViewModel::class.java)
            viewModel.googleManager = Mockito.mock(GoogleManager::class.java)
            viewModel.alertDialogs = Mockito.mock(AlertDialogs::class.java)
        }
    }

    @Test
    fun `when use google log in`() {
        viewModel.googleManager.user.observeForever{
            Assert.assertEquals(

            )
        }
        viewModel.singInGoogle()
    }
}