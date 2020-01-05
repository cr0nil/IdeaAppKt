package com.karolapp.ideaappkt.ui.view.presenter

import android.app.Activity
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.ui.contract.AuthContract
import java.util.regex.Pattern

class RegisterPreseterImpl(authView: AuthContract.AuthView) : AuthContract.AuthPresenter {
    private var registerView: AuthContract.AuthView = authView
     val EMAIL_ADDRESS_PATTERN = Pattern.compile(//to util
         "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
         "\\@" +
         "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
         "(" +
         "\\." +
         "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
         ")+"
)
    fun validEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        if (email == null || email.isEmpty() || EMAIL_ADDRESS_PATTERN.matcher(email as CharSequence).matches()) {
            return false
        }
        return true
    }


    override fun handleLogin(
        name: String,
        password: String,
        mAuth: FirebaseAuth
    ) {
        if (!validEmail(name)) {
            registerView.authFailure()

        }
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