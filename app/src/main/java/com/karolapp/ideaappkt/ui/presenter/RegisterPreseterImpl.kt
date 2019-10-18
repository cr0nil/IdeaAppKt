package com.karolapp.ideaappkt.ui.view.presenter

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.ui.view.presenter.interfaces.AuthPresenter
import com.karolapp.ideaappkt.ui.view.interfaces.AuthView

class RegisterPreseterImpl(authView: AuthView) : AuthPresenter {
    private var registerView: AuthView? = authView

    override fun handleLogin(name: String, password: String, activity: Activity?, mAuth: FirebaseAuth) {
        mAuth.createUserWithEmailAndPassword(name, password)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    registerView!!.loginSuccess()
                } else {

                }

            }
    }
}