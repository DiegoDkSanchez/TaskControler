package com.example.core.interactor.usercase

import com.example.core.interactor.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class LoginWithGoogleUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(account: GoogleSignInAccount) = repository.authWithGoogle(account)
}