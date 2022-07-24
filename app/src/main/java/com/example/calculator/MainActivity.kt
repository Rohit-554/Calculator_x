package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var boolean: Boolean = false
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    var item = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        val clickableViews: List<View> =
            listOf(
                _one,
                two,
                three,
                four,
                five,
                six,
                seven,
                eight,
                nine,
                zero,
                dot,
                clr,
                Percent,
                divide,
                multiply,
                sub,
                plus,
                equals,
                AC
            )
        for (item in clickableViews) {
            item.setOnClickListener {
                btnTxt(it)
                boolean = true
            }
        }
    }

    private fun btnTxt(view: View) {
        when (view.id) {
            R.id._one -> setText(view)
            R.id.two -> setText(view)
            R.id.three -> setText(view)
            R.id.four -> setText(view)
            R.id.five -> setText(view)
            R.id.six -> setText(view)
            R.id.seven -> setText(view)
            R.id.eight -> setText(view)
            R.id.nine -> setText(view)
            R.id.zero -> setText(view)
            R.id.dot -> dot()
            R.id.clr -> oneByOne()
            R.id.plus -> operatorOperation(view)
            R.id.sub -> operatorOperation(view)
            R.id.multiply -> operatorOperation(view)
            R.id.divide -> operatorOperation(view)
            R.id.Percent -> operatorOperation(view)
            R.id.equals -> onEquals()
            R.id.AC -> onClear()

        }
    }

    private fun setText(view: View) {

        val value = tv_display?.text.toString()

        if (value.startsWith("0")) {
            val replace = (view as Button).text.toString()
            tv_display?.text= value.replace("0",replace)
        }
        else{
            tv_display.append((view as Button).text)
        }
        lastNumeric = true
        lastDot = false
    }

    private fun onClear() {
        tv_display.text = ""
    }

    private fun dot() {
        if (lastNumeric && !lastDot) {
            tv_display?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun operatorOperation(view: View) {
        tv_display?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tv_display?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }

        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("+")
            value.contains("-")
            value.contains("*")
            value.contains("/")
            value.contains("%")
        }
    }

    private fun onEquals() {
        var tvValue = tv_display?.text.toString()
        var prefix = ""
        if (lastNumeric) {
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var a = splitValue[0]
                    val b = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        a = prefix + a
                    }
                    val result = a.toDouble() - b.toDouble()
                    tv_display?.text = zeroNeglect((result).toString())
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var a = splitValue[0]
                    val b = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        a = prefix + a
                    }
                    val result = a.toDouble() + b.toDouble()
                    tv_display?.text = zeroNeglect((result).toString())
                } else if (tvValue.contains("x")) {
                    val splitValue = tvValue.split("x")
                    var a = splitValue[0]
                    val b = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        a = prefix + a
                    }
                    val result = a.toDouble() * b.toDouble()
                    tv_display?.text = zeroNeglect((result).toString())
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var a = splitValue[0]
                    val b = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        a = prefix + a
                    }
                    val result = a.toDouble() / b.toDouble()
                    tv_display?.text = zeroNeglect((result).toString())
                } else if (tvValue.contains("%")) {
                    val splitValue = tvValue.split("%")
                    var a = splitValue[0]
                    val b = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        a = prefix + a
                    }
                    val result = (a.toDouble() * b.toDouble()) / 100
                    tv_display?.text = zeroNeglect((result).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }

        }
    }

    private fun zeroNeglect(result: String): String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    private fun oneByOne() {
        val value = tv_display?.text.toString()
        if (value.isNotEmpty()) {
            tv_display?.text = value.substring(0, value.length - 1)

        }
    }

    private fun zero() {
        val value = tv_display?.text.toString()

        if (value.startsWith("0")) {
//            val splitValue = value.split("0")
//            var a = splitValue[0]
//            val b = splitValue[1]
//            if (prefix.isNotEmpty()) {
//                a = prefix + a
//            }
//            val result = a.toDouble() - b.toDouble()
            tv_display?.text = value.substring(1,value.length)

        }


    }
}


