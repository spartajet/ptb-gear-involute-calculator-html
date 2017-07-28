package de.ptb.length.check


import de.ptb.length.involute.Angle

/**
 * @description
 * *
 * @create 2017-06-15 下午3:46
 * *
 * @email spartajet.guo@gmail.com
 */
class InputCheckResultAngle(code: Int = ERROR_CODE_NO_ERROR, message: String = ERROR_MESSAGE_NO_ERROR) : InputCheckResult(code, message, "") {
    var value: Angle = Angle()
}
