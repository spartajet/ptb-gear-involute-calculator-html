package de.ptb.length.involute

import de.ptb.length.check.InputCheck
import de.ptb.length.check.InputCheckAngle
import de.ptb.length.check.InputCheckResult
import de.ptb.length.check.InputCheckResultAngle
import de.ptb.length.math.format

/**
 * @description
 * @create 2017-07-21 上午9:42
 * @email spartajet.guo@gmail.com
 */
abstract class ParaAngle(fixed: Boolean, val unit: String, var valueLimitMax: Angle, var valueLimitMin: Angle, val digitsAfterDotSecond: Int) : Para(fixed), IAngle {
    val inputCheck: InputCheckAngle = InputCheckAngle(this.digitsAfterDotSecond, this.valueLimitMax, this.valueLimitMin)

    /**
     * The Input value.
     */
    protected var inputValue: Angle = Angle()
    /**
     * The Result value.
     */
    protected var result_Value: Angle = Angle()
    /**
     * The Result min.
     */
    protected var resultValueMin: Angle = Angle()
    /**
     * The Result max.
     */
    protected var resultValueMax: Angle = Angle()

    /**
     * The First value.
     */
    protected var firstValue: Angle = Angle()
    /**
     * The Round value.
     */
    protected var round_Value: Angle = Angle()
    /**
     * The Round contradiction.
     */
    protected var round_Contradiction: Angle = Angle()
    /**
     * The Contradiction.
     */
    protected var angle_Contradiction: Angle = Angle()

    override fun roundValue(): Boolean {
        if (this.result_Value == ANGLE_MAX_VALUE) {
            return false
        }
        val roundSecond = this.result_Value.second.format(this.digitsAfterDotSecond + 1).toDouble()
        this.round_Value = Angle(this.result_Value.degree, this.result_Value.minute, roundSecond)
        return true
    }


    override fun roundContradiction(): Boolean {
        if (this.angle_Contradiction == ANGLE_MAX_VALUE) {
            return false
        }
        val roundContradictionSecond = this.angle_Contradiction.second.format(this.digitsAfterDotSecond + 1).toDouble()
        this.round_Contradiction = Angle(this.angle_Contradiction.degree, this.angle_Contradiction.minute, roundContradictionSecond)
        return true
    }


    override fun addChar(c: Char): InputCheckResult {
        val resultAngle = this.inputCheck.addChar(c) as InputCheckResultAngle
        this.inputValue = Angle(resultAngle.value)
        return resultAngle
    }

    /**
     * Clear.
     */
    override fun clear() {
        super.clear()
        this.inputCheck.clear()
        this.inputValue = Angle()
        this.refresh()
    }

    override fun refresh() {
        super.refresh()
        this.known = this.inputValue != ANGLE_ZERO_VALUE && this.inputValue != ANGLE_MAX_VALUE
        if (this.known) {
            this.result_Value = Angle(this.inputValue)
        } else {
            this.result_Value = Angle(ANGLE_MAX_VALUE)
        }
        this.firstValue = Angle(ANGLE_MAX_VALUE)
        this.resultValueMax = Angle(ANGLE_MIN_VALUE)
        this.resultValueMin = Angle(ANGLE_MAX_VALUE)
        this.round_Value = Angle(ANGLE_MAX_VALUE)
        this.angle_Contradiction = Angle(ANGLE_MAX_VALUE)
        this.round_Contradiction = Angle(ANGLE_MAX_VALUE)
    }

    /**
     * Refresh value.

     * @param angle the angle
     */
    protected fun refreshValue(angle: Angle) {
        if (this.checkLimit(angle)) {
            this.result_Value = Angle(angle)
            this.refreshMinMax(angle)
            this.fixed(angle)
        } else {
            this.result_Value = Angle(ANGLE_MAX_VALUE)
            this.resultValueMin = Angle(ANGLE_MAX_VALUE)
            this.resultValueMax = Angle(ANGLE_MIN_VALUE)
        }
    }

    /**
     * Fixed.

     * @param angle the angle
     */
    protected fun fixed(angle: Angle) {
        if (first) {
            this.result_Value = Angle(firstValue)
        } else {
            this.firstValue = Angle(angle)
        }
        if (this.fixed) {
            this.result_Value = Angle(this.inputValue)
        }
    }

    /**
     * Refresh min max.

     * @param angle the angle
     */
    protected fun refreshMinMax(angle: Angle) {
        if (this.valueLimitMin.bigger(angle)) {
            this.valueLimitMin = Angle(angle)
        }
        if (this.valueLimitMax.smaller(angle)) {
            this.valueLimitMax = Angle(angle)
        }
    }

    /**
     * Check limit boolean.

     * @param angle the angle
     * *
     * @return the boolean
     */
    protected fun checkLimit(angle: Angle): Boolean {
        if (angle.bigger(this.valueLimitMin) && angle.smaller(this.valueLimitMax)) {
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

    override fun isCalculationSucceed() = super.calculationSucceed

    override fun isKnown() = super.known

    override fun resultValue() = this.result_Value.toReal()

    override fun isFixed() = this.fixed

    override fun getRoundValueString() = this.round_Value.toString()
    override fun setFixed(value: Boolean) {
        this.fixed = value
    }

    override fun getRoundContradictionString() = this.round_Contradiction.toString()

    override fun getValueLimitMin() = this.valueLimitMin.toReal()

    override fun getValueLimitMax() = this.valueLimitMax.toReal()

    override fun getInputCheck(): InputCheck = this.inputCheck


    override fun setInputValue(value: Angle) {
        this.inputValue = Angle(inputValue)
        this.result_Value = Angle(inputValue)
    }

    override fun setValueString(valueString: String) {
        this.valueString = valueString
        this.known = this.valueString == ""
    }

    override fun getInputValueString(): String = this.inputValue.toString()

    override fun calculateValue() = false

    override fun calculateContradiction() = false
}