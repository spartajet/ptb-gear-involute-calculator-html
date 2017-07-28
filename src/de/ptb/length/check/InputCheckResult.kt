package de.ptb.length.check

/**
 * @description
 * @create 2017-07-20 下午12:38
 * @email spartajet.guo@gmail.com
 */
/**
 * The constant ERROR_CODE_NO_ERROR.
 */
val ERROR_CODE_NO_ERROR = 0
/**
 * The constant ERROR_MESSAGE_NO_ERROR.
 */
val ERROR_MESSAGE_NO_ERROR = "success"
/**
 * The constant ERROR_CODE_BIGGER.
 */
val ERROR_CODE_BIGGER = 2
/**
 * The constant ERROR_MESSAGE_BIGGER.
 */
val ERROR_MESSAGE_BIGGER = "bigger than the max limit: "
/**
 * The constant ERROR_CODE_SMALLER.
 */
val ERROR_CODE_SMALLER = 3
/**
 * The constant ERROR_MESSAGE_SMALLER.
 */
val ERROR_MESSAGE_SMALLER = "smaller than the min limit: "
/**
 * The constant ERROR_CODE_MORE_INTEGER_DIGITS.
 */
val ERROR_CODE_MORE_INTEGER_DIGITS = 4
/**
 * The constant ERROR_MESSAGE_MORE_INTEGER_DIGITS.
 */
val ERROR_MESSAGE_MORE_INTEGER_DIGITS = "more digits before dot: "
/**
 * The constant ERROR_CODE_MORE_DECIMAL_DIGITS.
 */
val ERROR_CODE_MORE_DECIMAL_DIGITS = 5
/**
 * The constant ERROR_MESSAGE_MORE_DECIMAL_DIGITS.
 */
val ERROR_MESSAGE_MORE_DECIMAL_DIGITS = "more digits after dot: "

/**
 * The constant ERROR_CODE_ILLEGAL_DOT.
 */
val ERROR_CODE_ILLEGAL_DOT = 6
/**
 * The constant ERROR_MESSAGE_ILLEGAL_DOT.
 */
val ERROR_MESSAGE_ILLEGAL_DOT = "Illegal dot here"
/**
 * The constant ERROR_CODE_ZERO_REAL_ERROR.
 */
val ERROR_CODE_ZERO_REAL_ERROR = 7
/**
 * The constant ERROR_MESSAGE_ZERO_REAL_ERROR.
 */
val ERROR_MESSAGE_ZERO_REAL_ERROR = "more than one 0 in a real number"
/**
 * The constant ERROR_CODE_ILLEGAL_SIGNAL_POSITION.
 */
val ERROR_CODE_ILLEGAL_SIGNAL_POSITION = 8
/**
 * The constant ERROR_MESSAGE_ILLEGAL_SIGNAL_POSITION.
 */
val ERROR_MESSAGE_ILLEGAL_SIGNAL_POSITION = "Illegal signal position here"

/**
 * The constant ERROR_CODE_ZERO_INT_PREFIX_ERROR.
 */
val ERROR_CODE_ZERO_INT_PREFIX_ERROR = 9
/**
 * The constant ERROR_MESSAGE_ZERO_INT_PREFIX_ERROR.
 */
val ERROR_MESSAGE_ZERO_INT_PREFIX_ERROR = "Illegal 0 at the beginning of a integer number"

/**
 * The constant ERROR_CODE_ILLEGAL_CHARACTER.
 */
val ERROR_CODE_ILLEGAL_CHARACTER = 10
/**
 * The constant ERROR_MESSAGE_ILLEGAL_CHARACTER.
 */
val ERROR_MESSAGE_ILLEGAL_CHARACTER = "Illegal character here: "

/**
 * The constant ERROR_CODE_FINISH_INPUT.
 */
val ERROR_CODE_FINISH_INPUT = 11
/**
 * The constant ERROR_MESSAGE_FINISH_INPUT.
 */
val ERROR_MESSAGE_FINISH_INPUT = "input is finished, you can not input any character more"
/**
 * The constant ERROR_CODE_ANGLE_MINUTE_BIGGER_60.
 */
val ERROR_CODE_ANGLE_MINUTE_BIGGER_60 = 12
/**
 * The constant ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60.
 */
val ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60 = "minute part is bigger than 60"
/**
 * The constant ERROR_CODE_ANGLE_MINUTE_BIGGER_60.
 */
val ERROR_CODE_ANGLE_SECOND_INTEGER_BIGGER_60 = 13
/**
 * The constant ERROR_MESSAGE_ANGLE_MINUTE_BIGGER_60.
 */
val ERROR_MESSGAE_ANGLE_SECOND_INTEGER_BIGGER_60 = "minute part is bigger than 60"
/**
 * The constant ERROR_CODE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS.
 */
val ERROR_CODE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS = 14
/**
 * The constant ERROR_MESSAGE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS.
 */
val ERROR_MESSAGE_MORE_ANGLE_SECOND_DECIMAL_MORE_DIGITS = "more angle second decimal digits"

abstract class InputCheckResult(var code: Int, var message: String, var valueString: String)