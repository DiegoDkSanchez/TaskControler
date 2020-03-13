package com.example.taskcontroler.presentation.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.util.ActiviyNavigation
import com.example.core.util.LiveMessageEvent
import com.example.data.repository.GoogleManager
import com.example.taskcontroler.R
import com.example.taskcontroler.presentation.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    val googleManager: GoogleManager
) : BaseViewModel() {
    private companion object {
        const val GOOGLE_SIGN_IN: Int = 9001
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    val startActivityForResultEvent = LiveMessageEvent<ActiviyNavigation>()

    fun onResultFromActivity(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                singIn(task.getResult(ApiException::class.java))
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.e("TEST", "Google sign in failed", e)
            }
        }
    }

    fun singInGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.resources.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
        val signInIntent = googleSignInClient.signInIntent

        startActivityForResultEvent.sentEvent {
            startActivityForResult(
                signInIntent,
                GOOGLE_SIGN_IN
            )
        }
    }

    fun singIn(account: GoogleSignInAccount?) = viewModelScope.launch(Dispatchers.Main) {
        account?.let {
            Log.v("TEST", "Loading with credencials")
            withContext(Dispatchers.IO) {
                googleManager.fetchUserFromGoogle(account)
            }
        }
    }
}