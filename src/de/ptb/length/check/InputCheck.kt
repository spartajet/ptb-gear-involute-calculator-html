package de.ptb.length.check

/**
 * @description
 * @create 2017-07-20 下午12:37
 * @email spartajet.guo@gmail.com
 */
abstract class InputCheck(val inputCheckResult: InputCheckResult) {

    /**
     * The constant ANGLE_DEGREE_SIGNAL.
     */
    var ANGLE_DEGREE_SIGNAL = '°'
    /**
     * The constant ANGLE_MINUTE_SIGNAL.
     */
    var ANGLE_MINUTE_SIGNAL = '′'
    /**
     * The constant ANGLE_SECOND_SIGNAL.
     */
    var ANGLE_SECOND_SIGNAL = '″'
    /**
     * The constant DOT.
     */
    var DOT = '.'

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
    abstract fun clear()
}