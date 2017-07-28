package de.ptb.length.check

import de.ptb.length.involute.Angle

/**
 * @description
 * @create 2017-07-20 下午3:25
 * @email spartajet.guo@gmail.com
 */
class InputCheckAngle(val secondAllowDigits: Int, val checkLimitMax: Angle, val checkLimitMin: Angle, val result: InputCheckResultAngle = InputCheckResultAngle()) : InputCheck(result) {

    /**
     * The Cursor.
     */
    private var cursor: InputCheck.Cursor = Cursor.CURSOR_START

    /**
     * The Value.
     */
    private var value: Double = 0.0
    /**
     * The Value string.
     */
    private var valueString: String = ""
    /**
     * The Character list.
     */
    private val characterList: MutableList<Char> = mutableListOf()
    /**
     * The Degree list.
     */
    private val degreeList: MutableList<Char> = mutableListOf()
    /**
     * The Minute list.
     */
    private val minuteList: MutableList<Char> = mutableListOf()
    /**
     * The Second integer list.
     */
    private val secondIntegerList: MutableList<Char> = mutableListOf()
    /**
     * The Second decimal list.
     */
    private val secondDecimalList: MutableList<Char> = mutableListOf()

    override fun addChar(c: Char): InputCheckResult {
        this.result.code = ERROR_CODE_NO_ERROR
        this.result.message = ERROR_MESSAGE_NO_ERROR
        if (c.toInt() == 8) {
            this.backSpaceCheck()
        } else {
            when (this.cursor) {
                InputCheck.Cursor.CURSOR_START -> this.firstCharCheck(c)
                InputCheck.Cursor.CURSOR_ANGLE_DEGREE_INTEGER -> this.angleDegreeIntegerCheck(c)
                InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL, InputCheck.Cursor.CURSOR_ANGLE_MINUTS_INTEGER -> this.angleMinuteIntegerCheck(c)
                InputCheck.Cursor.CURSOR_ANGLE_MINUTS_SIGNAL, InputCheck.Cursor.CURSOR_ANGLE_SECOND_INTEGER -> this.angleSecondIntegerCheck(c)
                InputCheck.Cursor.CURSOR_ANGLE_SECONDE_DOT, InputCheck.Cursor.CURSOR_ANGLE_SECOND_DECIMAL -> this.angleSecondDecimalCheck(c)
                InputCheck.Cursor.CURSOR_ANGLE_SECOND_SIGNAL -> {
                    this.result.code = ERROR_CODE_FINISH_INPUT
                    this.result.message = ERROR_MESSAGE_FINISH_INPUT
                }
            }
        }
        this.generateResultValueString()
        this.generateResultValue()
        this.result.value = Angle(this.value)
        this.result.valueString = this.valueString
        return this.result
    }

    /**
     * Back space check.
     */
    private fun backSpaceCheck() {
        when (cursor) {
            InputCheck.Cursor.CURSOR_START -> {
            }
            InputCheck.Cursor.CURSOR_ANGLE_DEGREE_INTEGER -> {
                this.degreeList.removeAt(this.degreeList.size - 1)
                if (degreeList.size == 0) {
                    cursor = InputCheck.Cursor.CURSOR_START
                }
            }
            InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL -> this.cursor = InputCheck.Cursor.CURSOR_ANGLE_DEGREE_INTEGER
            InputCheck.Cursor.CURSOR_ANGLE_MINUTS_INTEGER -> {
                this.minuteList.removeAt(this.minuteList.size - 1)
                if (minuteList.size == 0) {
                    cursor = InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL
                }
            }
            InputCheck.Cursor.CURSOR_ANGLE_MINUTS_SIGNAL -> this.cursor = InputCheck.Cursor.CURSOR_ANGLE_MINUTS_INTEGER
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_INTEGER -> {
                this.secondIntegerList.removeAt(this.secondIntegerList.size - 1)
                if (this.secondIntegerList.size == 0) {
                    this.cursor = InputCheck.Cursor.CURSOR_ANGLE_MINUTS_SIGNAL
                }
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECONDE_DOT -> this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECOND_INTEGER
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_DECIMAL -> {
                this.secondDecimalList.removeAt(this.secondDecimalList.size - 1)
                if (this.secondDecimalList.size == 0) {
                    this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECONDE_DOT
                }
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_SIGNAL -> this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECOND_DECIMAL
        }
    }

    /**
     * Generate result value.
     */
    private fun generateResultValue() {
        var degree = 0
        var minute = 0
        var secondInteger = 0
        var secondDecimal = 0
        when (cursor) {
            InputCheck.Cursor.CURSOR_START -> this.value = 0.0
            InputCheck.Cursor.CURSOR_ANGLE_DEGREE_INTEGER, InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL -> this.value = this.getIntegerFromList(this.degreeList).toDouble()
            InputCheck.Cursor.CURSOR_ANGLE_MINUTS_INTEGER, InputCheck.Cursor.CURSOR_ANGLE_MINUTS_SIGNAL -> {
                degree = this.getIntegerFromList(this.degreeList)
                minute = this.getIntegerFromList(this.minuteList)
                this.value = degree + minute / 60.0
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_INTEGER, InputCheck.Cursor.CURSOR_ANGLE_SECONDE_DOT -> {
                degree = this.getIntegerFromList(this.degreeList)
                minute = this.getIntegerFromList(this.minuteList)
                secondInteger = this.getIntegerFromList(this.secondIntegerList)
                this.value = degree.toDouble() + minute / 60.0 + secondInteger / 3600.0
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_DECIMAL, InputCheck.Cursor.CURSOR_ANGLE_SECOND_SIGNAL -> {
                degree = this.getIntegerFromList(this.degreeList)
                minute = this.getIntegerFromList(this.minuteList)
                secondInteger = this.getIntegerFromList(this.secondIntegerList)
                secondDecimal = this.getIntegerFromList(this.secondDecimalList)
                this.value = degree.toDouble() + minute / 60.0 + secondInteger / 3600.0 + ("0." + secondDecimal).toDouble() / 3600.0
            }
        }
    }

    /**
     * Generate result value string.
     */
    private fun generateResultValueString() {
        val builder = StringBuilder()
        when (this.cursor) {
            InputCheck.Cursor.CURSOR_START -> this.valueString = ""
            InputCheck.Cursor.CURSOR_ANGLE_DEGREE_INTEGER -> this.degreeList.forEach { builder.append(it) }
            InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL -> {
                this.degreeList.forEach { builder.append(it) }
                builder.append(ANGLE_DEGREE_SIGNAL)
            }
            InputCheck.Cursor.CURSOR_ANGLE_MINUTS_INTEGER -> {
                this.degreeList.forEach { builder.append(it) }
                builder.append(ANGLE_DEGREE_SIGNAL)
                this.minuteList.forEach { builder.append(it) }
            }
            InputCheck.Cursor.CURSOR_ANGLE_MINUTS_SIGNAL -> {
                this.degreeList.forEach { builder.append(it) }
                builder.append(ANGLE_DEGREE_SIGNAL)
                this.minuteList.forEach { builder.append(it) }
                builder.append(ANGLE_MINUTE_SIGNAL)
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_INTEGER -> {
                this.degreeList.forEach { builder.append(it) }
                builder.append(ANGLE_DEGREE_SIGNAL)
                this.minuteList.forEach { builder.append(it) }
                builder.append(ANGLE_MINUTE_SIGNAL)
                this.secondIntegerList.forEach { builder.append(it) }
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECONDE_DOT -> {
                this.degreeList.forEach { builder.append(it) }
                builder.append(ANGLE_DEGREE_SIGNAL)
                this.minuteList.forEach { builder.append(it) }
                builder.append(ANGLE_MINUTE_SIGNAL)
                this.secondIntegerList.forEach { builder.append(it) }
                builder.append(DOT)
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_DECIMAL -> {
                this.degreeList.forEach { builder.append(it) }
                builder.append(ANGLE_DEGREE_SIGNAL)
                this.minuteList.forEach { builder.append(it) }
                builder.append(ANGLE_MINUTE_SIGNAL)
                this.secondIntegerList.forEach { builder.append(it) }
                builder.append(DOT)
                this.secondDecimalList.forEach { builder.append(it) }
            }
            InputCheck.Cursor.CURSOR_ANGLE_SECOND_SIGNAL -> {
                this.degreeList.forEach { builder.append(it) }
                builder.append(ANGLE_DEGREE_SIGNAL)
                this.minuteList.forEach { builder.append(it) }
                builder.append(ANGLE_MINUTE_SIGNAL)
                this.secondIntegerList.forEach { builder.append(it) }
                builder.append(DOT)
                this.secondDecimalList.forEach { builder.append(it) }
                builder.append(ANGLE_SECOND_SIGNAL)
            }
        }
        this.valueString = builder.toString()
    }

    /**
     * Angle second decimal check.

     * @param c the c
     */
    private fun angleSecondDecimalCheck(c: Char) {
        when (c) {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                if (this.secondDecimalList.size == this.secondAllowDigits) {
                    this.result.code = ERROR_CODE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS
                    this.result.message = ERROR_MESSAGE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS
                }
                this.secondDecimalList.add(c)
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECOND_DECIMAL
            }
            '″' -> {
                if (this.secondDecimalList.size == 0) {
                    this.secondDecimalList.add('0')
                }
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECOND_SIGNAL
            }
        }
    }

    /**
     * Angle second integer check.

     * @param c the c
     */
    private fun angleSecondIntegerCheck(c: Char) {
        when (c) {
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                if (!this.checkTempSecondInteger(c)) {
                    this.result.code = ERROR_CODE_ANGLE_SECOND_INTEGER_BIGGER_60
                    this.result.message = ERROR_MESSGAE_ANGLE_SECOND_INTEGER_BIGGER_60
                    return
                }
                this.secondIntegerList.add(c)
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECOND_INTEGER
            }
            '0' -> {
                if (!this.checkTempSecondInteger(c)) {
                    this.result.code = ERROR_CODE_ANGLE_SECOND_INTEGER_BIGGER_60
                    this.result.message = ERROR_MESSGAE_ANGLE_SECOND_INTEGER_BIGGER_60
                    return
                }
                this.secondIntegerList.add(c)
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECONDE_DOT
            }
            '.' -> {
                if (this.secondIntegerList.size == 0) {
                    this.secondIntegerList.add('0')
                }
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECONDE_DOT
            }
            '″' -> {
                if (this.secondIntegerList.size == 0) {
                    this.secondIntegerList.add('0')
                }
                this.secondDecimalList.add('0')
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_SECOND_SIGNAL
            }
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER
            }
        }
    }

    /**
     * Angle minute integer check.

     * @param c the c
     */
    private fun angleMinuteIntegerCheck(c: Char) {
        when (c) {
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                if (!this.checkTempMinuteInteger(c)) {
                    this.result.code = ERROR_CODE_ANGLE_MINUTE_BIGGER_60
                    this.result.message = ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60
                    return
                }
                this.minuteList.add(c)
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_MINUTS_INTEGER
            }
            '0' -> {
                if (!this.checkTempMinuteInteger(c)) {
                    this.result.code = ERROR_CODE_ANGLE_MINUTE_BIGGER_60
                    this.result.message = ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60
                    return
                }
                this.minuteList.add(c)
                //                this.minuteList.add('′');
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_MINUTS_SIGNAL
            }
            '′' -> {
                if (this.minuteList.size == 0) {
                    this.minuteList.add('0')
                }
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_MINUTS_SIGNAL
            }
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER
            }
        }
    }


    /**
     * Angle degree integer check.

     * @param c the c
     */
    private fun angleDegreeIntegerCheck(c: Char) {
        when (c) {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                if (!this.checkTempDegreeValue(c)) {
                    this.result.code = ERROR_CODE_BIGGER
                    this.result.message = ERROR_MESSAGE_BIGGER
                    return
                }
                this.degreeList.add(c)
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_DEGREE_INTEGER
            }
            '°' ->
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER
            }
        }
    }

    /**
     * First char check.

     * @param c the c
     */
    private fun firstCharCheck(c: Char) {
        when (c) {
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                if (c.toInt() > checkLimitMax.degree) {
                    this.result.code = ERROR_CODE_BIGGER
                    this.result.message = ERROR_MESSAGE_BIGGER
                    return
                }
                this.degreeList.add(c)
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_DEGREE_INTEGER
            }
            '0' -> {
                this.degreeList.add(c)
                //                this.degreeList.add('°');
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL
            }
            '°' -> {
                this.degreeList.add('0')
                //                this.degreeList.add('°');
                this.cursor = InputCheck.Cursor.CURSOR_ANGLE_DEGREE_SIGNAL
            }
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER
            }
        }
    }

    /**
     * Check temp degree value boolean.

     * @param c the c
     * *
     * @return the boolean
     */
    private fun checkTempDegreeValue(c: Char): Boolean {
        val builder = StringBuilder(degreeList.size + 1)
        this.degreeList.forEach { builder.append(it) }
        builder.append(c)
        val tempString = builder.toString()
        val degree = tempString.toInt()
        return degree >= this.checkLimitMin.degree && degree < this.checkLimitMax.degree
    }

    /**
     * Check temp minute integer boolean.

     * @param c the c
     * *
     * @return the boolean
     */
    private fun checkTempMinuteInteger(c: Char): Boolean {
        val builder = StringBuilder(this.minuteList.size + 1)
        this.minuteList.forEach { builder.append(it) }
        builder.append(c)
        val minute = builder.toString().toInt()
        return minute < 60
    }

    /**
     * Check temp second integer boolean.

     * @param c the c
     * *
     * @return the boolean
     */
    private fun checkTempSecondInteger(c: Char): Boolean {
        val builder = StringBuilder(this.secondIntegerList.size + 1)
        this.secondIntegerList.forEach { builder.append(it) }
        builder.append(c)
        val second = builder.toString().toInt()
        return second < 60
    }

    /**
     * Gets integerform list.

     * @param list the list
     * *
     * @return the integerform list
     */
    private fun getIntegerFromList(list: List<Char>): Int {
        val builder = StringBuilder(list.size)
        list.forEach { builder.append(it) }
        val tempString = builder.toString()
        return tempString.toInt()
    }

    override fun clear() {
        this.cursor = InputCheck.Cursor.CURSOR_START
        this.degreeList.clear()
        this.minuteList.clear()
        this.secondIntegerList.clear()
        this.secondDecimalList.clear()
        this.value = 0.0
        this.valueString = ""
    }
}