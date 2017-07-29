package de.ptb.length.check

/**
 * "Represents the helix angle Beta, is the angle between a tangent to a
 * reference helix and the reference cylinder envelope line through the tangent
 * contact point" .<br></br>
 * Equation:
 * 1 - 2 from ISO 21771:2007 (Beta= arccos(Mn/Mt))
 * 2 - 23 from ISO 21771:2007 (Beta = arccos((z*Mn/Dr)))
 * 3 - 4.3.2 from ISO 21771:2007  (Beta = 90-gamma)
 * 4 - 3 from ISO 21771:2007  (Beta = atan (((cos(gamma)*Mt))/Mn))
 * 5 - 14 from ISO 21771:2007  (Beta = arccos(tan(alfaN)/tan(alfaT)))
 * 6 - 2.3 from DIN 3960 (Beta = arccos(sqrt((Mn2/Mb2) - (tan2 alfaN))))
 * 7 - 2.5 from DIN 3960 (Beta = arccos((Mn*cos(alfaT))/Mb))
 *
 *
 *
 *
 * According to ISO/FDIS 21771:2007 Value is given in radians<br></br>
 *
 *
 * Copyright &copy 2010 [Physikalisch-Technische
   * Bundesanstalt, Braunschweig und Berlin, Germany](http://www.ptb.de) <br></br>
 * All rights reserved

 * @author [Frank H&aumlrtig (Frank
 * * Haertig)](mailto:frank.haertig@ptb.de)
 * *
 * @author Cristiam Cometta Conde
 * *
 * @author Marcus Diego Pinto Freitas
 * *
 * @version 1.0 2010-03-02 (cc, hae, mf)
 */

/**
 * The type Input check int.

 * @description
 * *
 * @create 2017 -03-27 下午2:51
 * *
 * @email gxz04220427 @163.com
 */
class InputCheckInt(val valueLimitMax: Int, val valueLimitMin: Int, val digitsLimitMax: Int, val result: InputCheckResultInt = InputCheckResultInt()) : InputCheck(result) {

    /**
     * The Unit.
     */
    private val unit: String = "mm"
    /**
     * The Signal flag.
     * true: positive;
     * false: negative
     */
    private var signalFlag: Boolean = true
    /**
     * The Cursor.
     */
    private var cursor1: InputCheck.Cursor = Cursor.CURSOR_START
    /**
     * The Character list.
     */
    private val characterList: MutableList<Char> = mutableListOf()

    override fun addChar(c: Char): InputCheckResult {
        this.result.code = ERROR_CODE_NO_ERROR
        this.result.message = ERROR_MESSAGE_NO_ERROR
        if (c.toInt() == 8) {
            this.backSpaceCheck()
        } else {
            when (this.cursor1) {
                Cursor.CURSOR_START -> this.firstCharCheck(c)
                Cursor.CURSOR_SIGNAL, InputCheck.Cursor.CURSOR_INTEGER -> this.integerPartCheck(c)
            }
        }
        this.generateResultValue()
        this.generateResultValueString()
        return this.result
    }

    override fun clear() {
        this.characterList.clear()
        this.signalFlag = true
        this.cursor1 = InputCheck.Cursor.CURSOR_START
        this.result.code = ERROR_CODE_NO_ERROR
        this.result.message = ERROR_MESSAGE_NO_ERROR
        this.result.valueString = ""
    }

    /**
     * First char check.

     * @param c the c
     */
    private fun firstCharCheck(c: Char) {
        when (c) {
            '0' -> {
                this.result.code = ERROR_CODE_ZERO_INT_PREFIX_ERROR
                this.result.message = ERROR_MESSAGE_ZERO_INT_PREFIX_ERROR
                this.result.valueString = ""
                this.result.value = 0
            }
            '+'
            -> {
            }
            '-' -> {
                this.signalFlag = false
                this.cursor1 = InputCheck.Cursor.CURSOR_SIGNAL
            }
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> if (this.checkTempNumber(c)) {
                this.characterList.add(c)
                this.cursor1 = InputCheck.Cursor.CURSOR_INTEGER
            }
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER
            }
        }
    }

    /**
     * Check temp number boolean.

     * @param c the c
     * *
     * @return the boolean
     */
    fun checkTempNumber(c: Char): Boolean {
        if (this.characterList.size == digitsLimitMax) {
            this.result.code = ERROR_CODE_MORE_INTEGER_DIGITS
            this.result.message = ERROR_MESSAGE_MORE_INTEGER_DIGITS
            return false
        }
        val builder = StringBuilder(this.characterList.size + 1)
        this.characterList.forEach { it -> builder.append(it) }
        builder.append(c)
        var tempValue = builder.toString().toInt()
        if (!this.signalFlag) {
            tempValue = -tempValue
        }
        if (tempValue > this.valueLimitMax) {
            this.result.code = ERROR_CODE_BIGGER
            this.result.message = ERROR_MESSAGE_BIGGER + this.valueLimitMax
            return false
        } else if (tempValue < this.valueLimitMin) {
            this.result.code = ERROR_CODE_SMALLER
            this.result.message = ERROR_MESSAGE_SMALLER
            return false
        }
        return true
    }

    /**
     * Integer part check.

     * @param c the c
     */
    private fun integerPartCheck(c: Char) {
        when (c) {
            '+' -> if (this.checkTempNumber(c)) {
                this.signalFlag = true
            }
            '-' -> if (this.checkTempNumber(c)) {
                this.signalFlag = false
            }
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> if (this.checkTempNumber(c)) {
                this.characterList.add(c)
            }
        }
    }

    /**
     * Back space check.
     */
    private fun backSpaceCheck() {
        when (cursor1) {
            InputCheck.Cursor.CURSOR_START -> {
            }
            InputCheck.Cursor.CURSOR_SIGNAL -> {
                this.cursor1 = InputCheck.Cursor.CURSOR_START
                this.signalFlag = true
            }
            InputCheck.Cursor.CURSOR_INTEGER -> {
                this.characterList.removeAt(this.characterList.size - 1)
                if (this.characterList.size == 0) {
                    if (!this.signalFlag) {
                        this.cursor1 = InputCheck.Cursor.CURSOR_SIGNAL
                    } else {
                        this.cursor1 = InputCheck.Cursor.CURSOR_START
                    }
                }
            }
        }
    }

    /**
     * Generate result inputValue.
     */
    private fun generateResultValue() {
        if (this.cursor1 == InputCheck.Cursor.CURSOR_START || this.cursor1 == InputCheck.Cursor.CURSOR_SIGNAL) {
            this.result.value = 0
            return
        }
        val builder = StringBuilder()
        if (!this.signalFlag) {
            builder.append('-')
        }
        this.characterList.forEach { it -> builder.append(it) }
        this.result.value = builder.toString().toInt()
    }

    /**
     * Generate result value string.
     */
    private fun generateResultValueString() {
        if (this.cursor1 == InputCheck.Cursor.CURSOR_START) {
            this.result.valueString = ""
            return
        }
        if (this.cursor1 == InputCheck.Cursor.CURSOR_SIGNAL) {
            this.result.valueString = "-"
            return
        }
        val builder = StringBuilder()
        val tempList: MutableList<Char> = mutableListOf()
        for (i in this.characterList.indices.reversed()) {
            val index = this.characterList.size - 1 - i
            if (index > 0 && index % 3 == 0) {
                tempList.add(0, ',')
            }
            tempList.add(0, this.characterList[i])
        }
        if (!this.signalFlag) {
            tempList.add(0, '-')
        }
        tempList.forEach { it -> builder.append(it) }
        this.result.valueString = builder.toString()
    }
}