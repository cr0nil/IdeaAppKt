package com.karolapp.ideaappkt.ui.view.presenter.interfaces

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth

interface LoginPresenter {
    fun handleLogin(name: String, password: String, activity: Activity?, mAuth: FirebaseAuth)
}