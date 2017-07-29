package de.ptb.length.involute

import de.ptb.length.check.InputCheck
import de.ptb.length.check.InputCheckReal
import de.ptb.length.check.InputCheckResult
import de.ptb.length.check.InputCheckResultReal
import de.ptb.length.math.format

/**
 * @description
 * @create 2017-07-21 上午8:52
 * @email spartajet.guo@gmail.com
 */
open class ParaReal( fixed: Boolean, val lengthAllowedDotBefore: Int, val lengthAllowedDotAfter: Int, val valueLimitMax: Double, val valueLimitMin: Double, val unit: String) : Para(fixed) {

    val inputCheck: InputCheckReal = InputCheckReal(this.lengthAllowedDotBefore, this.lengthAllowedDotAfter, this.valueLimitMax, this
            .valueLimitMin)
    /**
     * The Value.
     */
    var inputValue: Double = 0.0
        set(value) {
            field = value
            this.result_Value = value
        }
    /**
     * The Result value.
     */
    var result_Value: Double = 0.0
    /**
     * The Result min.
     */
    protected var resultValueMin: Double = 0.0
    /**
     * The Result max.
     */
    protected var resultValueMax: Double = 0.0
    /**
     * The First value.
     */
    protected var firstValue: Double = 0.0
    /**
     * The Round value.
     */
    protected var round_Value: Double = 0.0
    /**
     * The Round contradiction.
     */
    protected var round_Contradiction: Double = 0.0


    override fun calculateValue() = false

    override fun calculateContradiction() = false

    override fun roundValue(): Boolean {
        if (this.result_Value == Double.MAX_VALUE) {
            return false
        }
        this.round_Value = this.result_Value.format(this.lengthAllowedDotAfter + 1).toDouble()
        return true
    }

    override fun roundContradiction(): Boolean {
        if (this.contradiction == Double.MAX_VALUE) {
            return false
        }
        this.round_Contradiction = this.contradiction.format(this.lengthAllowedDotAfter + 1).toDouble()
        return true
    }

    override fun addChar(c: Char): InputCheckResult {
        val resultReal = this.inputCheck.addChar(c) as InputCheckResultReal
        this.inputValue = resultReal.value
        return resultReal
    }

    override fun getInputCheck(): InputCheck {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun clear() {
        super.clear()
        this.inputCheck.clear()
        this.inputValue = 0.0
        this.refresh()
    }

    override fun refresh() {
        super.refresh()
        this.known = this.inputValue != Double.MAX_VALUE && this.inputValue != 0.0
        if (this.known) {
            this.result_Value = this.inputValue
        } else {
            this.result_Value = Double.MAX_VALUE
        }
        this.firstValue = Double.MAX_VALUE
        this.resultValueMax = -Double.MAX_VALUE
        this.resultValueMin = Double.MAX_VALUE
        this.round_Value = Double.MAX_VALUE
        this.contradiction = Double.MAX_VALUE
        this.round_Contradiction = Double.MAX_VALUE
    }

    /**
     * Refresh value.

     * @param number the number
     */
    protected fun refreshValue(number: Double) {
        if (this.checkLimit(number)) {
            this.result_Value = number
            this.refreshMinMax(number)
            this.fixed(number)
        } else {
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
        }
    }

    /**
     * Fixed.

     * @param number the number
     */
    private fun fixed(number: Double) {
        if (this.first) {
            this.result_Value = firstValue
        } else {
            this.firstValue = number
        }
        if (this.fixed) {
            this.result_Value = this.inputValue
            //            this.refreshMinMax(number);
        }
    }

    /**
     * Refresh min max.

     * @param number the number
     */
    private fun refreshMinMax(number: Double) {
        if (this.resultValueMin > number) {
            this.resultValueMin = number
        }
        if (this.resultValueMax < number) {
            this.resultValueMax = number
        }
    }

    /**
     * Check limit boolean.

     * @param number the number
     * *
     * @return the boolean
     */
    protected fun checkLimit(number: Double): Boolean {
        if (number >= this.valueLimitMin && number < this.valueLimitMax) {
            this.known = true
        } else {
            this.known = false
            this.calculationSucceed = false
        }
        return known
    }

    override fun toString(): String {
        return "input value: " + this.inputValue + "; know: " + this.known + "; fixed: " + fixed + "; result value: " + result_Value + "; first value: " + firstValue + "; contradiction: " + contradiction + "; result value max: " + resultValueMax + "; result value min: " + resultValueMin + "; calculate success: " + calculationSucceed + "; round value: " + round_Value + "; round contradiction: " + contradiction
    }
}