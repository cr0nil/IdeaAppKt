package com.karolapp.ideaappkt.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var fragmentWelcomeBinding: FragmentWelcomeBinding
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentWelcomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_welcome, container, false)
        fragmentWelcomeBinding.welcome = this

        return fragmentWelcomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    fun goCreateAccount() {
        navController!!.navigate(R.id.action_welcomeFragment_to_registerFragment)
    }

    fun goToSingIn() {
        navController!!.navigate(R.id.action_welcomeFragment_to_loginFragment)
    }

}
