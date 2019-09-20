package com.karolapp.ideaappkt

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.karolapp.ideaappkt.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var listener: OnFragmentInteractionListener? = null
    lateinit var fragmentWelcomeBinding: FragmentWelcomeBinding
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentWelcomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome, container, false)

        val view = fragmentWelcomeBinding.root


        return inflater.inflate(R.layout.fragment_welcome, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         navController = Navigation.findNavController(view)

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    fun goCreateAccount(view : View) {
        val myToast = Toast.makeText(context,"success",Toast.LENGTH_SHORT)
        myToast.show()
Log.i("tagg","creatr"+view.id)
        navController!!.navigate(R.id.action_welcomeFragment_to_loginFragment)
    }

    fun goToSingIn(v :View) {
        Log.i("tagg","creatr1"+v.id)
        navController!!.navigate(R.id.action_welcomeFragment_to_loginFragment)
    }


}
