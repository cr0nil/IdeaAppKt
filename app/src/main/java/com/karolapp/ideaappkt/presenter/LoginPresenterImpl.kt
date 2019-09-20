package com.karolapp.ideaappkt.presenter

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.presenter.interfaces.LoginPresenter
import com.karolapp.ideaappkt.view.interfaces.LoginView


class LoginPresenterImpl : LoginPresenter {

    var loginView: LoginView? = null
    private lateinit var mAuth: FirebaseAuth
    val TAG: String = "Login"

    constructor(loginView: LoginView) {
        this.loginView = loginView
    }

    override fun handleLogin(name: String, password: String, activity: Activity) {
        mAuth.signInWithEmailAndPassword(name, password)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    loginView!!.loginSuccess()
                    Log.i(TAG, "signInWithEmail:success", task.exception)
                    // val user = mAuth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)

                }

                // ...
            }

    }
}