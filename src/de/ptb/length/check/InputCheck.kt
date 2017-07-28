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

    /**
     * The enum Cursor.
     */
    protected enum class Cursor {
        /**
         * Cursor start cursor.
         */
        CURSOR_START,
        /**
         * Cursor signal cursor.
         */
        CURSOR_SIGNAL,
        /**
         * Cursor integer cursor.
         */
        CURSOR_INTEGER,
        /**
         * Cursor dot cursor.
         */
        CURSOR_DOT,
        /**
         * Cursor decimal cursor.
         */
        CURSOR_DECIMAL,
        /**
         * Cursor angle degree integer cursor.
         */
        CURSOR_ANGLE_DEGREE_INTEGER,
        /**
         * Cursor angle degree signal cursor.
         */
        CURSOR_ANGLE_DEGREE_SIGNAL,
        /**
         * Cursor angle minuts integer cursor.
         */
        CURSOR_ANGLE_MINUTS_INTEGER,
        /**
         * Cursor angle minuts signal cursor.
         */
        CURSOR_ANGLE_MINUTS_SIGNAL,
        /**
         * Cursor angle second integer cursor.
         */
        CURSOR_ANGLE_SECOND_INTEGER,
        /**
         * Cursor angle second dot cursor.
         */
        CURSOR_ANGLE_SECONDE_DOT,
        /**
         * Cursor angle second decimal cursor.
         */
        CURSOR_ANGLE_SECOND_DECIMAL,
        /**
         * Cursor angle second signal cursor.
         */
        CURSOR_ANGLE_SECOND_SIGNAL

    }
}