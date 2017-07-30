package de.ptb.length.involute

import de.ptb.length.check.InputCheckInt

/**
 * @description
 * @create 2017-07-20 下午9:20
 * @email spartajet.guo@gmail.com
 */
abstract class ParaInt(fixed: Boolean, val unit: String, val valueLimitMax: Int, val valueLimitMin: Int, val digitsLimitMax: Int) : Para(fixed) {

    val inputCheck: InputCheckInt = InputCheckInt(this.valueLimitMax, this.valueLimitMin, this.digitsLimitMax)
    /**
     * The Value.
     */
    var inputValue: Int = Int.MAX_VALUE
        set(value) {
            field = value
            resultValue = value
        }
    /**
     * The Result value.
     */
    var resultValue: Int = 0
    /**
     * The Result min.
     */
    var resultValueMin: Int = 0
    /**
     * The Result max.
     */
    protected var resultValueMax: Int = 0
    /**
     * The First value.
     */
    protected var firstValue: Int = 0
    /**
     * The Round value.
     */
    var round_Value: Int = 0
    /**
     * The Round contradiction.
     */
    var round_Contradiction: Int = 0

    override fun clear() {
        super.clear()
        this.inputCheck.clear()
        this.inputValue = 0
        this.refresh()
    }

    override fun refresh() {
        super.refresh()
        super.refresh()
        this.known = this.inputValue != 0 && this.inputValue != Int.MAX_VALUE
        if (this.known) {
            this.resultValue = this.inputValue
            this.firstValue = this.inputValue
        } else {
            this.resultValue = Int.MAX_VALUE
            this.firstValue = Int.MAX_VALUE
        }
        this.firstValue = Int.MAX_VALUE
        this.resultValueMax = -Int.MAX_VALUE
        this.resultValueMin = Int.MAX_VALUE
        this.round_Value = Int.MAX_VALUE
        this.contradiction = Double.MAX_VALUE
        this.round_Contradiction = Int.MAX_VALUE
    }

    /**
     * Check limit boolean.

     * @param number the number
     * *
     * @return the boolean
     */
    protected fun checkLimit(number: Int): Boolean {
        if (number >= this.valueLimitMin && number < this.valueLimitMax) {
            this.known = true
        } else {
            this.known = false
            this.calculationSucceed = false
        }
        return known
    }

    /**
     * Refresh inputValue.

     * @param number the number
     */
    fun refreshValue(number: Int) {
        if (this.checkLimit(number)) {
            this.resultValue = number
            this.refreshMinMax(number)
            this.fixed(number)
        } else {
            this.resultValue = Int.MAX_VALUE
            this.resultValueMax = -Int.MAX_VALUE
            this.resultValueMin = Int.MAX_VALUE
        }
    }

    /**
     * Refresh min max.

     * @param number the number
     */
    private fun refreshMinMax(number: Int) {
        if (this.resultValueMin > number) {
            this.resultValueMin = number
        }
        if (this.resultValueMax < number) {
            this.resultValueMax = number
        }
    }

    override fun toString(): String {
        return "input value: " + this.inputValue + "; know: " + this.known + "; fixed: " + fixed + "; result value: " + resultValue + "; first value: " + firstValue + "; contradiction: " + contradiction + "; result value max: " + resultValueMax + "; result value min: " + resultValueMin + "; calculate success: " + calculationSucceed + "; round value: " + round_Value + "; round contradiction: " + contradiction
    }

    /**
     * Fixed.

     * @param number the number
     */
    fun fixed(number: Int) {
        if (this.first) {
            this.resultValue = firstValue
        } else {
            this.firstValue = number
        }
        if (this.fixed) {
            this.resultValue = this.inputValue
            this.refreshMinMax(number)
        }
    }

    override fun roundValue(): Boolean {
        if (this.resultValue == Int.MAX_VALUE) {
            return false
        }
        this.round_Value = this.resultValue
        return true
    }

    override fun roundContradiction(): Boolean {
        if (this.contradiction == Double.MAX_VALUE) {
            return false
        }
        this.round_Contradiction = this.contradiction.toInt()
        return true
    }

    override fun addChar(c: Char) = this.inputCheck.addChar(c)


}