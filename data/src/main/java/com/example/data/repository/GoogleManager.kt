package com.example.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.core.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleManager {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var user: MutableLiveData<User> = MutableLiveData()

    fun fetchUserFromGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            val i = if (task.isSuccessful) {
                val currentUser = auth.currentUser
                val splitName = currentUser?.displayName?.split(" ")
                user.postValue(
                    User(
                        currentUser?.uid ?: "",
                        currentUser?.displayName ?: "",
                        splitName?.get(0)?:"",
                        currentUser?.email ?: "",
                        currentUser?.photoUrl.toString()
                    )
                )
                Log.v("TEST", "Login Successful")
            } else {
                Log.w("TEST", "Login Fail", task.exception)
            }
        }
    }

}
