package com.karolapp.ideaappkt.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentLoginBinding
import com.karolapp.ideaappkt.presenter.LoginPresenterImpl
import com.karolapp.ideaappkt.view.interfaces.LoginView


class LoginFragment : Fragment(), LoginView {
    lateinit var loginPresenterImpl: LoginPresenterImpl
    lateinit var fragmentLoginBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPresenterImpl = LoginPresenterImpl(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        fragmentLoginBinding.setLoginFragment(this)
        return fragmentLoginBinding.getRoot()
        // Inflate the layout for this fragment

    }

    override fun loginSuccess() {
        val toast = Toast.makeText(context, "success", Toast.LENGTH_SHORT)
        toast.show()
    }

    fun singIn() {
        val toast = Toast.makeText(context, "success", Toast.LENGTH_SHORT)
        toast.show()
    }

}
