package com.example.tippy

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sbTip.progress = 15
        tvTipPercentage.text = "15%"
        tipDescription(15)
        sbTip.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i("MainActivity", "SeekBar Change")
                tvTipPercentage.text = "$progress%"
                tipDescription(progress)
                tipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) { }

            override fun onStopTrackingTouch(p0: SeekBar?) { }

        })

        etBase.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("MainActivity", "EditText Change")
                tipAndTotal()
            }

        })
    }

    private fun tipDescription(tipPer: Int) {
        val tipValue: String
        when (tipPer) {
            in 0..9 -> tipValue = "Poor"
            in 10..19 -> tipValue = "Good"
            else -> tipValue = "Great"
        }

        tvTipGrade.text = tipValue
    }

    private fun tipAndTotal() {

        if(etBase.text.isEmpty()){
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }

        val base = etBase.text.toString().toDouble()
        val tip = sbTip.progress

        val tipAmount = base*tip/100
        val total = base+tipAmount

        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(total)

    }
}