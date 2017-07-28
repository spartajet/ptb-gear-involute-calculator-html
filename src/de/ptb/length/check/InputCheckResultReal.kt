package de.ptb.length.check

/**
 * @description
 * @create 2017-07-20 下午1:05
 * @email spartajet.guo@gmail.com
 */
class InputCheckResultReal(code: Int = ERROR_CODE_NO_ERROR, message: String = ERROR_MESSAGE_NO_ERROR) : InputCheckResult(code, message, "") {
    var value: Double = 0.0
}