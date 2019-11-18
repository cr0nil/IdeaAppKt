package com.karolapp.ideaappkt.ui.contract

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth

class AuthContract {
    interface AuthView {
        fun loginSuccess()
        fun authFailure()
    }

    interface AuthPresenter {
        fun handleLogin(name: String, password: String, activity: Activity?, mAuth: FirebaseAuth)
    }
}