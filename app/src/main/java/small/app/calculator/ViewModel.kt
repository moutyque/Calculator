package small.app.calculator

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

class ViewModel : ViewModel() {
    var currentOp: MutableLiveData<Char> = MutableLiveData<Char>()
    var currentResult: MutableLiveData<Double> = MutableLiveData<Double>()
    var display: MutableLiveData<String> = MutableLiveData<String>()


    init {
        currentResult.value = 0.0
        currentOp.value = 'C'
        display.value = ""
    }

    fun addSeparator(digit: Char) {
        var str = display.value!!
        if (digit.equals('.') && str.contains('.')) {
            Log.e("ViewModel :", "Cannot add twice decimal separator")
        } else if (digit.equals('.') && (str.endsWith('-') || str.isEmpty())) {
            str = str + '0' + '.'
        } else {
            str += digit
        }
        display.value = str
    }

    fun addDigit(digit: Char) {
        val int = digit.toInt() - 48
        when (int) {
            in 0..9 -> display.value = display.value!! + digit
            else -> Log.e("ViewModel :", "Character not allowed to display in calculator")
        }
    }

    fun clear() {
        currentResult.value = 0.0
        display.value = ""
    }

    fun changeOp(op: Char) {

        if (op.equals('-') && display.value!!.isEmpty()) {//Add negative char
            display.value = op.toString()
        } else if (op.equals('+') && !display.value!!.isEmpty()
            && display.value!!.length == 1
            && display.value!![0].equals('-')
        ) {//Remove negative char
            display.value = ""
        } else {
            when (op) {
                '+', '-', '*', '/', 'E' -> updateResult(op)
                else ->
                    Log.e("ViewModel :", "Operation not valid")
            }
        }
    }

    private fun updateResult(op: Char) {

        //TODO : error when combining op

        var str: String = display.value!!.toString()
        var result: Double = currentResult.value!!
        var newVal: Double = if (str.isEmpty()) {
            0.0
        } else {
            str.toDouble()
        }

        //Calculate new result
        when (currentOp.value) {
            '+' -> result = BigDecimal.valueOf(result).add(BigDecimal.valueOf(newVal)).toDouble()
            '-' -> result =
                BigDecimal.valueOf(result).subtract(BigDecimal.valueOf(newVal)).toDouble()
            '*' -> result *= newVal
            '/' -> result = BigDecimal.valueOf(result).divide(BigDecimal.valueOf(newVal)).toDouble()
            else -> result = newVal
        }
        currentResult.value = result
        //Refresh display
        display.value = ""

        //If equal op display the result
        if (op.equals('E')) {
            display.value = currentResult.value!!.toString()
            currentResult.value = 0.0
        }

        currentOp.value = op


    }

    fun getDisplay(): String {
        return display.value!!.toString()
    }

    fun getResult(): String {
        return currentResult.value!!.toString()
    }

    fun onDigit(view: View) {
        if (view is Button) {
            var btn: Button = view as Button
            var digit = btn.text[0]
            addDigit(digit)
        }
    }

    fun onOperator(view: View) {
        if (view is Button) {
            var btn: Button = view as Button
            var digit = btn.text[0]
            changeOp(digit)
        }
    }

    fun onClear(view: View) {
        clear()
    }

    fun onSeparator(view: View) {
        if (view is Button) {
            var btn: Button = view as Button
            var digit = btn.text[0]
            addSeparator(digit)
        }
    }

    fun onEqual(view: View) {
        updateResult('E')
    }


}