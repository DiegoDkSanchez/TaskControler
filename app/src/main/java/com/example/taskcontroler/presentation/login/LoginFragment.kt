package com.example.taskcontroler.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.core.model.User
import com.example.core.util.ActiviyNavigation
import com.example.taskcontroler.R
import com.example.taskcontroler.databinding.FragmentLoginBinding
import com.example.taskcontroler.presentation.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<FragmentLoginBinding>(R.layout.fragment_login, BR.loginViewModel),
    ActiviyNavigation {

    override val viewModel: LoginViewModel? by viewModel()

    companion object {
        fun newIntance() =
            LoginFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.let { safeViewModel ->
            safeViewModel.googleManager.user.observe(viewLifecycleOwner, observerUserData())
            backgroundLogin.setMaxProgress(0.5f)
        }
    }

    fun observerUserData() = Observer<User> {
        Log.v("TEST", it.toString())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        viewModel?.startActivityForResultEvent?.setEventReceiver(this, this)
        if (context != null) {
            viewModel?.context = context!!
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel?.onResultFromActivity(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}