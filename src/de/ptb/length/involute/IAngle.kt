package de.ptb.length.involute

import de.ptb.length.check.ICheckable
import de.ptb.length.check.InputCheckResult

/**
 * @description
 * @create 2017-07-20 下午9:02
 * @email spartajet.guo@gmail.com
 */
interface IAngle : ICheckable {
    fun refresh()

    fun clear()

    fun setFixed(value: Boolean)

    fun isCalculationSucceed(): Boolean

    fun isKnown(): Boolean

    fun resultValue(): Double

    fun isFixed(): Boolean

    fun roundValue(): Boolean

    fun roundContradiction(): Boolean

    fun getRoundValueString(): String

    fun getRoundContradictionString(): String

    fun addChar(c: Char): InputCheckResult

    fun getValueLimitMin(): Double

    fun getValueLimitMax(): Double

    fun setInputValue(value: Angle)

    fun setValueString(valueString: String)

    fun getInputValueString(): String

}