package com.karolapp.ideaappkt.ui.view.presenter

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.ui.contract.AuthContract


class LoginPresenterImpl(authView: AuthContract.AuthView) : AuthContract.AuthPresenter{

    private var loginView: AuthContract.AuthView = authView
    private val tag: String = "Login"

    override fun handleLogin(name: String, password: String, activity: Activity?, mAuth: FirebaseAuth) {
        mAuth.signInWithEmailAndPassword(name, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    loginView.loginSuccess()
                    Log.i(tag, "signInWithEmail:success", task.exception)
                    // val user = mAuth.currentUser

                } else {
                    loginView.authFailure()
                    // If sign in fails, display a message to the user.
                    Log.w(tag, "signInWithEmail:failure", task.exception)

                }
            }
        // ...
    }

}
