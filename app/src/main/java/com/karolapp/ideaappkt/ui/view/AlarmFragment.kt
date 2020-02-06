package com.karolapp.ideaappkt.ui.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentAlarmBinding
import com.karolapp.ideaappkt.ui.MainActivity
import com.karolapp.ideaappkt.ui.contract.AlarmContract


class AlarmFragment : Fragment(), AlarmContract.View {
    private lateinit var fragmentAlarmBinding: FragmentAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAlarmBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false)

        val spinner: Spinner = fragmentAlarmBinding.coinSpinner
        val activity : MainActivity = activity as MainActivity
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            context,
            R.array.planets_array,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        activity.getValueInBackground()
        // Inflate the layout for this fragment
        return fragmentAlarmBinding.root
    }


}
