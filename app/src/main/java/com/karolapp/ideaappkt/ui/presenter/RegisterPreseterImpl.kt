package com.karolapp.ideaappkt.ui.view.presenter

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.ui.contract.AuthContract

class RegisterPreseterImpl(authView: AuthContract.AuthView) : AuthContract.AuthPresenter {
    private var registerView: AuthContract.AuthView = authView

    override fun handleLogin(
        name: String,
        password: String,
        activity: Activity?,
        mAuth: FirebaseAuth
    ) {
        mAuth.createUserWithEmailAndPassword(name, password)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    registerView.loginSuccess()
                } else {
                    registerView.authFailure()
                }

            }
    }
}