package com.example.core.interactor.repository

import androidx.lifecycle.LiveData
import com.example.core.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface UserRepository {
    suspend fun authWithGoogle(account: GoogleSignInAccount): LiveData<User>
}