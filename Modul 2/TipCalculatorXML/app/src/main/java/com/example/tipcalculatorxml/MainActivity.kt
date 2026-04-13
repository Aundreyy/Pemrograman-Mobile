package com.example.tipcalculatorxml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var etBillAmount: TextInputEditText
    private lateinit var actvTipPercentage: AutoCompleteTextView
    private lateinit var switchRoundUp: MaterialSwitch
    private lateinit var tvTipAmount: TextView

    private var currentPercentage = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBillAmount = findViewById(R.id.etBillAmount)
        actvTipPercentage = findViewById(R.id.actvTipPercentage)
        switchRoundUp = findViewById(R.id.switchRoundUp)
        tvTipAmount = findViewById(R.id.tvTipAmount)

        if (savedInstanceState != null) {
            currentPercentage = savedInstanceState.getInt("PERCENTAGE", 15)
        }

        val tipOptions = resources.getStringArray(R.array.tip_options).toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tipOptions)
        actvTipPercentage.setAdapter(adapter)

        actvTipPercentage.setOnItemClickListener { _, _, position, _ ->
            currentPercentage = tipOptions[position].replace("%", "").toInt()
            calculateTip()
        }

        switchRoundUp.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }

        etBillAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }
        })

        calculateTip()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("PERCENTAGE", currentPercentage)
    }

    private fun calculateTip() {
        val amountStr = etBillAmount.text.toString()
        val amount = amountStr.toDoubleOrNull() ?: 0.0

        var tip = amount.times(currentPercentage).div(100.0)
        if (switchRoundUp.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tvTipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}