package de.ptb.length.involute

import de.ptb.length.check.ICheckable
import de.ptb.length.check.InputCheckResult

/**
 * @description
 * @create 2017-07-20 下午9:11
 * @email spartajet.guo@gmail.com
 */
abstract class Para(var fixed: Boolean = false) : ICheckable {
    /**
     * The Value string.
     */
    var valueString: String = ""
    /**
     * The Calculate count.
     */
    protected var calculateCount: Int = 0

    /**
     * Calculate inputValue boolean.

     * @return the boolean
     */
    protected abstract fun calculateValue(): Boolean

    /**
     * The Contradiction.
     */
    protected var contradiction: Double = 0.0
    /**
     * The known.
     */
     var known: Boolean = false
    /**
     * The Calculation succeed.
     */
     var calculationSucceed: Boolean = false
    /**
     * The First.
     */
     var first: Boolean = false
    /**
     * The Run.
     */
    protected var run: Boolean = false


    /**
     * The Calculation 01.
     */
    protected var calculation_01: Boolean = false
    /**
     * The Calculation 02.
     */
    protected var calculation_02: Boolean = false
    /**
     * The Calculation 03.
     */
    protected var calculation_03: Boolean = false
    /**
     * The Calculation 04.
     */
    protected var calculation_04: Boolean = false
    /**
     * The Calculation 05.
     */
    protected var calculation_05: Boolean = false
    /**
     * The Calculation 06.
     */
    protected var calculation_06: Boolean = false
    /**
     * The Calculation 07.
     */
    protected var calculation_07: Boolean = false
    /**
     * The Calculation 08.
     */
    protected var calculation_08: Boolean = false
    /**
     * The Calculation 09.
     */
    protected var calculation_09: Boolean = false
    /**
     * The Calculation 10.
     */
    protected var calculation_10: Boolean = false
    /**
     * The Calculation 11.
     */
    protected var calculation_11: Boolean = false



    companion object {
        /**
         * The Once more.
         */
        var onceMore: Boolean = false
    }

    /**
     * Calculate contradiction boolean.

     * @return the boolean
     */
    protected abstract fun calculateContradiction(): Boolean

    /**
     * Round value boolean.

     * @return the boolean
     */
    abstract fun roundValue(): Boolean

    /**
     * Round contradiction boolean.

     * @return the boolean
     */
    abstract fun roundContradiction(): Boolean

    /**
     * Add char input check result.

     * @param c the c
     * *
     * @return the input check result
     */
    abstract fun addChar(c: Char): InputCheckResult

    /**
     * Clear.
     */
    open fun clear() {
        this.fixed = false
        this.valueString = ""
        this.known = false
        this.refresh()
    }

    /**
     * Refresh.
     */
    open fun refresh() {
        this.calculationSucceed = true
        this.contradiction = Double.MAX_VALUE
        this.calculateCount = 0
        this.calculation_01 = false
        this.calculation_02 = false
        this.calculation_03 = false
        this.calculation_04 = false
        this.calculation_05 = false
        this.calculation_06 = false
        this.calculation_07 = false
        this.calculation_08 = false
        this.calculation_09 = false
        this.calculation_10 = false
        this.calculation_11 = false
        onceMore = false
        this.first = false
    }


}