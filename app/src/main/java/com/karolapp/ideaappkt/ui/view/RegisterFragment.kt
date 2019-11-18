package com.karolapp.ideaappkt.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.ui.MainActivity
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentRegisterBinding
import com.karolapp.ideaappkt.ui.contract.AuthContract
import com.karolapp.ideaappkt.ui.view.presenter.RegisterPreseterImpl


class RegisterFragment : Fragment(), AuthContract.AuthView {

    private lateinit var registerPreseterImpl: RegisterPreseterImpl
    private lateinit var registerBinding: FragmentRegisterBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPreseterImpl = RegisterPreseterImpl(this)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        registerBinding.registerFragment = this
        return registerBinding.root
    }

    override fun loginSuccess() {
        val toast = Toast.makeText(context, "success", Toast.LENGTH_SHORT)
        toast.show()
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    override fun authFailure() {
        val toast = Toast.makeText(context, "Failure! Try again", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun createAccount() {
        registerPreseterImpl.handleLogin(
            registerBinding.inEmail.text.toString(),
            registerBinding.inPassword.text.toString(),
            activity,
            mAuth
        )
    }
}
