package de.ptb.length.check

/**
 * @description
 * *
 * @create 2017-04-20 上午9:00
 * *
 * @email spartajet.guo@gmail.com
 */
class InputCheckResultInt(code: Int = ERROR_CODE_NO_ERROR, message: String = ERROR_MESSAGE_NO_ERROR) : InputCheckResult(code, message, "") {
    var value: Int = 0
}