package com.karolapp.ideaappkt.presenter

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.presenter.interfaces.AuthPresenter
import com.karolapp.ideaappkt.view.interfaces.AuthView


class LoginPresenterImpl(authView: AuthView) : AuthPresenter {

    private var loginView: AuthView? = authView
    private val tag: String = "Login"

    override fun handleLogin(name: String, password: String, activity: Activity?, mAuth: FirebaseAuth) {
        mAuth.signInWithEmailAndPassword(name, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    loginView!!.loginSuccess()
                    Log.i(tag, "signInWithEmail:success", task.exception)
                    // val user = mAuth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(tag, "signInWithEmail:failure", task.exception)

                }
            }
        // ...
    }

}
