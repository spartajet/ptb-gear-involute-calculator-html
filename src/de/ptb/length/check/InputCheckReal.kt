package de.ptb.length.check

/**
 * @description
 * @create 2017-07-20 下午2:20
 * @email spartajet.guo@gmail.com
 */
class InputCheckReal(val lengthAllowedDotBefore: Int, val lengthAllowedDotAfter: Int, val valueLimitMax: Double, val valueLimitMin: Double, val result: InputCheckResultReal = InputCheckResultReal()) : InputCheck(result) {
    /**
     * The Unit.
     */
    private val unit: String = "mm"
    /**
     * The Zero flag.
     */
    private var zeroFlag: Boolean = false
    /**
     * The Signal flag.
     * true: positive;
     * false: negative
     */
    private var signalFlag: Boolean = true
    /**
     * The Cursor.
     */
    private var cursor: Cursor = Cursor.CURSOR_START
    /**
     * The Integer list.
     */
    val integerList: MutableList<Char> = mutableListOf()
    /**
     * The Decimal list.
     */
    val decimalList: MutableList<Char> = mutableListOf()


    init {
        this.result.code = ERROR_CODE_NO_ERROR
        this.result.message = ERROR_MESSAGE_NO_ERROR
        this.result.value = 0.0
        this.result.valueString = "0"


    }

    override fun addChar(c: Char): InputCheckResult {
        // at first no error
        this.result.code = ERROR_CODE_NO_ERROR
        this.result.message = ERROR_MESSAGE_NO_ERROR
        if (c.toInt() == 8) {
            // if the character typed is backspace
            this.backSpaceCheck()
        } else {
            when (cursor) {
                Cursor.CURSOR_START -> this.firstCharCheck(c)
                Cursor.CURSOR_SIGNAL, Cursor.CURSOR_INTEGER -> this.integerPartCheck(c)
                Cursor.CURSOR_DOT, Cursor.CURSOR_DECIMAL -> this.decimalPartCheck(c)
            }
        }
        this.generateResultValue()
        this.generateResultValueString()
        return this.result
    }

    override fun clear() {
        this.result.code = ERROR_CODE_NO_ERROR
        this.result.message = ERROR_MESSAGE_NO_ERROR
        this.result.value = 0.0
        this.result.valueString = "0"
        this.zeroFlag = false
        this.signalFlag = true
        this.cursor = Cursor.CURSOR_START
        this.integerList.clear()
        this.decimalList.clear()
    }

    /**
     * First char check.

     * @param c the c
     */
    private fun firstCharCheck(c: Char) {
        when (c) {
            '0' -> {
                this.zeroFlag = true
                this.integerList.add('0')
                this.cursor = Cursor.CURSOR_INTEGER
            }
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                this.integerList.add(c)
                this.cursor = Cursor.CURSOR_INTEGER
            }
            '.' -> {
                this.integerList.add('0')
                this.cursor = Cursor.CURSOR_DOT
            }
            '+' -> {
            }
            '-' -> {
                this.signalFlag = false // the number is negative
                this.cursor = Cursor.CURSOR_SIGNAL
            }
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER

            }
        }
    }

    /**
     * Integer part check.

     * @param c the c
     */
    private fun integerPartCheck(c: Char) {
        when (c) {
            '.' -> {
                this.cursor = Cursor.CURSOR_DOT
                this.zeroFlag = false
            }
            '+' -> this.signalFlag = true
            '-' -> this.signalFlag = false
            '0' -> {
                if (this.integerList[0] == '0') {
                    this.result.code = ERROR_CODE_ZERO_REAL_ERROR
                    this.result.message = ERROR_MESSAGE_ZERO_REAL_ERROR
                    return
                }
                if (this.checkTempNumberForInteger(c)) {
                    this.integerList.add(c)
                    this.cursor = Cursor.CURSOR_INTEGER
                }
            }
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                if (this.integerList[0] == '0') {
                    this.integerList.removeAt(0)
                }
                if (this.checkTempNumberForInteger(c)) {
                    this.integerList.add(c)
                    this.cursor = Cursor.CURSOR_INTEGER
                }
            }
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER + c
            }
        }
    }

    /**
     * Check temp number for integer boolean.

     * @param c the c
     * *
     * @return the boolean
     */
    private fun checkTempNumberForInteger(c: Char): Boolean {
        if (this.integerList.size >= this.lengthAllowedDotBefore) {
            this.result.code = ERROR_CODE_MORE_INTEGER_DIGITS
            this.result.message = ERROR_MESSAGE_MORE_INTEGER_DIGITS
            return false
        }
        val builder = StringBuilder()
        if (!this.signalFlag) {
            builder.append('-')
        }
        this.integerList.forEach { builder.append(it) }
        builder.append(c)
        val tempValue = builder.toString().toInt()
        if (tempValue > this.valueLimitMax) {
            this.result.code = ERROR_CODE_BIGGER
            this.result.message = ERROR_MESSAGE_BIGGER + this.valueLimitMax.toString()
            return false
        } else if (tempValue < this.valueLimitMin) {
            this.result.code = ERROR_CODE_SMALLER
            this.result.message = ERROR_MESSAGE_SMALLER + this.valueLimitMin
            return false
        }
        return true
    }

    /**
     * Decimal part check.

     * @param c the c
     */
    private fun decimalPartCheck(c: Char) {
        when (c) {
            '.' -> {
                // two or more two . in a real is not allowed
                this.result.code = ERROR_CODE_ILLEGAL_DOT
                this.result.message = ERROR_MESSAGE_ILLEGAL_DOT
            }
            '+' -> this.signalFlag = true
            '-' -> this.signalFlag = false
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> if (this.checkTempNumberForDecimal(c)) {
                this.decimalList.add(c)
                this.cursor = Cursor.CURSOR_DECIMAL
            }
            else -> {
                this.result.code = ERROR_CODE_ILLEGAL_CHARACTER
                this.result.message = ERROR_MESSAGE_ILLEGAL_CHARACTER + c
            }
        }

    }

    private fun checkTempNumberForDecimal(c: Char): Boolean {
        if (this.decimalList.size >= this.lengthAllowedDotAfter) {
            this.result.code = ERROR_CODE_MORE_DECIMAL_DIGITS
            this.result.message = ERROR_MESSAGE_MORE_DECIMAL_DIGITS + this.lengthAllowedDotAfter
            return false
        }
        val builder = StringBuilder()
        if (!this.signalFlag) {
            builder.append('-')
        }
        this.integerList.forEach { builder.append(it) }
        builder.append('.')
        this.decimalList.forEach { builder.append(it) }
        val tempValue = builder.toString().toDouble()
        if (tempValue > this.valueLimitMax) {
            this.result.code = ERROR_CODE_BIGGER
            this.result.message = ERROR_MESSAGE_BIGGER + this.valueLimitMax
        } else if (tempValue < this.valueLimitMin) {
            this.result.code = ERROR_CODE_SMALLER
            this.result.message = ERROR_MESSAGE_SMALLER + this.valueLimitMin
        }
        return true
    }

    /**
     * Back space check.
     */
    private fun backSpaceCheck() {
        when (cursor) {
            Cursor.CURSOR_START -> {
            }
            Cursor.CURSOR_SIGNAL -> {
                this.signalFlag = true
                this.cursor = Cursor.CURSOR_START
            }
            Cursor.CURSOR_INTEGER -> {
                this.integerList.removeAt(this.integerList.size - 1)
                if (this.integerList.size == 0) {
                    if (this.signalFlag) {
                        this.cursor = Cursor.CURSOR_START
                    } else {
                        this.cursor = Cursor.CURSOR_SIGNAL
                    }
                }
            }
            Cursor.CURSOR_DOT -> this.cursor = Cursor.CURSOR_INTEGER
            Cursor.CURSOR_DECIMAL -> {
                this.decimalList.removeAt(this.decimalList.size - 1)
                if (this.decimalList.size == 0) {
                    this.cursor = Cursor.CURSOR_DOT
                }
            }
        }
    }

    /**
     * Generate result.
     */
    private fun generateResultValue() {
        val builder = StringBuilder()
        when (this.cursor) {
            Cursor.CURSOR_START, Cursor.CURSOR_SIGNAL -> {
                this.result.value = 0.0
                return
            }
            Cursor.CURSOR_INTEGER, Cursor.CURSOR_DOT -> {
                if (!this.signalFlag) {
                    builder.append('-')
                }
                this.integerList.forEach { builder.append(it) }
            }
            Cursor.CURSOR_DECIMAL -> {
                if (!this.signalFlag) {
                    builder.append('-')
                }
                this.integerList.forEach { builder.append(it) }
                builder.append('.')
                this.decimalList.forEach { builder.append(it) }
            }
        }
        this.result.value = builder.toString().toDouble()
    }

    private fun generateResultValueString() {
        val builder = StringBuilder()
        val tempList: MutableList<Char> = mutableListOf()
        when (this.cursor) {
            Cursor.CURSOR_START -> {
                this.result.valueString = ""
                return
            }
            Cursor.CURSOR_SIGNAL -> {
                this.result.valueString = "-"
                return
            }
            Cursor.CURSOR_INTEGER -> {
                for (i in this.integerList.indices.reversed()) {
                    val index = this.integerList.size - 1 - i
                    if (index > 0 && index % 3 == 0) {
                        tempList.add(0, ',')
                    }
                    tempList.add(0, this.integerList[i])
                }
                if (!this.signalFlag) {
                    tempList.add(0, '-')
                }
            }
            Cursor.CURSOR_DOT -> {
                for (i in this.integerList.indices.reversed()) {
                    val index = this.integerList.size - 1 - i
                    if (index > 0 && index % 3 == 0) {
                        tempList.add(0, ',')
                    }
                    tempList.add(0, this.integerList[i])
                }
                if (!this.signalFlag) {
                    tempList.add(0, '-')
                }
                builder.append('.')
            }
            Cursor.CURSOR_DECIMAL -> {
                for (i in this.integerList.indices.reversed()) {
                    val index = this.integerList.size - 1 - i
                    if (index > 0 && index % 3 == 0) {
                        tempList.add(0, ',')
                    }
                    tempList.add(0, this.integerList[i])
                }
                if (!this.signalFlag) {
                    tempList.add(0, '-')
                }
                builder.append('.')
                this.decimalList.forEach { tempList.add(it) }
            }
        }
        tempList.forEach { builder.append(it) }
        this.result.valueString = builder.toString()
    }
}