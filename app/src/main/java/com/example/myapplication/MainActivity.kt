package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val ADD = 0
    private val SUB = 1
    private val MUL = 2
    private val DIV = 3

    private var first = 0F
    private var second = 0F
    private var operator = ADD

    private var isEntering = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        resultTxt.text = round(first)

        setOperatorListeners()
        setDigitButtonListeners()
        setEqualListener()
        setClearListener()
    }

    private fun setClearListener() {
        cBtn.setOnClickListener {
            first = 0F
            second = 0F
            resultTxt.text = "0"
            isEntering = false
            operator = ADD
        }
    }

    private fun setEqualListener() {

        equalBtn.setOnClickListener {

            try {
                if (isEntering) {
                    second = resultTxt.text.toString().toFloat()
                }

                val result = getResult()

                Log.d("mymess", round(second))

                resultTxt.text = round(result)

                first = result

                isEntering = false

            } catch (err: Exception) {
                resultTxt.text = err.message
            }

        }

    }

    private fun setOperatorListeners() {
        addBtn.setOnClickListener(operatorHandler)
        subBtn.setOnClickListener(operatorHandler)
        mulBtn.setOnClickListener(operatorHandler)
        divBtn.setOnClickListener(operatorHandler)
    }

    private fun setDigitButtonListeners() {
        button0.setOnClickListener(digitHandler)
        button1.setOnClickListener(digitHandler)
        button2.setOnClickListener(digitHandler)
        button3.setOnClickListener(digitHandler)
        button4.setOnClickListener(digitHandler)
        button5.setOnClickListener(digitHandler)
        button6.setOnClickListener(digitHandler)
        button7.setOnClickListener(digitHandler)
        button8.setOnClickListener(digitHandler)
        button9.setOnClickListener(digitHandler)
    }

    private val digitHandler = View.OnClickListener { view ->
        //        Log.d("viewid", view.id.toString())

        var digit = 0

        when (view.id) {
            R.id.button0 -> digit = 0
            R.id.button1 -> digit = 1
            R.id.button2 -> digit = 2
            R.id.button3 -> digit = 3
            R.id.button4 -> digit = 4
            R.id.button5 -> digit = 5
            R.id.button6 -> digit = 6
            R.id.button7 -> digit = 7
            R.id.button8 -> digit = 8
            R.id.button9 -> digit = 9
        }

        if (digit == 0) {
            zeroHanlder()
        } else {
            nonZeroHandler(digit)
        }
    }

    private val operatorHandler = View.OnClickListener { view ->

        when (view.id) {
            R.id.addBtn -> operator = ADD
            R.id.subBtn -> operator = SUB
            R.id.mulBtn -> operator = MUL
            R.id.divBtn -> operator = DIV
        }

        if (isEntering) {
            first = resultTxt.text.toString().toFloat()
            isEntering = false
        }

        second = first

    }

    private fun nonZeroHandler(digit: Int) {
        if (!isEntering || resultTxt.text == "0") {
            resultTxt.text = digit.toString()
            isEntering = true
        } else {
            resultTxt.text = resultTxt.text.toString().plus(digit)
        }
    }

    private fun zeroHanlder() {
        if (!isEntering) {
            resultTxt.text = "0"
            isEntering = true
        } else {
            if (resultTxt.text != "0") {
                resultTxt.text = resultTxt.text.toString().plus("0")
            }
        }
    }

    private fun round(number: Float): String {
        val rounded = number.roundToInt()

        return if (number - rounded == 0F) {
            rounded.toString()
        } else {
            number.toString()
        }
    }

    private fun getResult(): Float {

        return when (operator) {
            0 -> (first + second)
            1 -> (first - second)
            2 -> (first * second)
            3 -> {
                if (second == 0F) {
                    throw Exception("Cannot divide by zero")
                }

                first / second
            }
            else -> {
                first
            }
        }
    }
}
