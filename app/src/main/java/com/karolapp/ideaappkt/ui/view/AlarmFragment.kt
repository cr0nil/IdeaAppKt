package com.karolapp.ideaappkt.ui.view


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentAlarmBinding
import com.karolapp.ideaappkt.ui.MainActivity
import com.karolapp.ideaappkt.ui.contract.AlarmContract


class AlarmFragment : Fragment(), AlarmContract.View {
    private val PREF_NAME = "alarms"
    private val KEY_NAME = "switch"
    lateinit var sharedPref: SharedPreferences
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
        sharedPref = activity!!.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
        val spinner: Spinner = fragmentAlarmBinding.coinSpinner
        val seekBar = fragmentAlarmBinding.seekBar
        fragmentAlarmBinding.textView5.setText((seekBar.progress).toString())
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            var pval: Float = 0.0F
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                pval = (progress) as Float
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) { //write custom code to on start progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                fragmentAlarmBinding.textView5.setText(pval.toString() + "/" + seekBar.max)
            }
        })

        ArrayAdapter.createFromResource(
            context,
            R.array.planets_array,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item)
            spinner.adapter = adapter
        }


        turnOnAlarm()
        return fragmentAlarmBinding.root
    }

    fun turnOnAlarm() {
        val activity: MainActivity = activity as MainActivity
        val checked = sharedPref.getBoolean(KEY_NAME, false)
        fragmentAlarmBinding.alarmSwitch.isChecked = checked
        
        fragmentAlarmBinding.alarmSwitch.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                val editor: SharedPreferences.Editor = sharedPref.edit()
                editor.putBoolean(KEY_NAME, isChecked)
                editor.commit()

                if (isChecked) {
                    activity.getValueInBackground()
                    fragmentAlarmBinding.alarmSwitch.setText("Turn on alarm  ")

                } else
                    fragmentAlarmBinding.alarmSwitch.setText("Turn off alarm  ")
            }
        })

    }


}
