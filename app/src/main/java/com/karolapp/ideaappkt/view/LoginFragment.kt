package com.karolapp.ideaappkt.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.MainActivity
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentLoginBinding
import com.karolapp.ideaappkt.presenter.LoginPresenterImpl
import com.karolapp.ideaappkt.view.interfaces.AuthView


class LoginFragment : Fragment(), AuthView {
    private lateinit var loginPresenterImpl: LoginPresenterImpl
    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPresenterImpl = LoginPresenterImpl(this)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        fragmentLoginBinding.loginFragment = this
        return fragmentLoginBinding.root
        // Inflate the layout for this fragment

    }

    override fun loginSuccess() {
        val toast = Toast.makeText(context, "success", Toast.LENGTH_SHORT)
        toast.show()
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    fun singIn() {
        loginPresenterImpl.handleLogin(
            fragmentLoginBinding.inEmail.text.toString(),
            fragmentLoginBinding.inPassword.text.toString(),
            activity,
            mAuth
        )

    }

}
