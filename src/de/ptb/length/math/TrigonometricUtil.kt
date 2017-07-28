package de.ptb.length.math

import kotlin.js.Math

/**
 * The type Trigonometric util.

 * @description
 * *
 * @create 2017 -05-19 上午8:46
 * *
 * @email spartajet.guo @gmail.com
 */
//object TrigonometricUtil {
/**
 * Sin double.

 * @param grad the grad
 * *
 * *
 * @return the double
 */
fun sin(grad: Double): Double {
    return Math.sin(grad2Radian(grad))
}

/**
 * Cos double.

 * @param grad the grad
 * *
 * *
 * @return the double
 */
fun cos(grad: Double): Double {
    return Math.cos(grad2Radian(grad))
}

/**
 * Tan double.

 * @param grad the grad
 * *
 * *
 * @return the double
 */
fun tan(grad: Double): Double {
    return Math.tan(grad2Radian(grad))
}

/**
 * Radian 2 grad double.

 * @param radian the radian
 * *
 * *
 * @return the double
 */
fun Radian2Grad(radian: Double) = 180 * radian / Math.PI


/**
 * Grad 2 radian double.

 * @param grad the grad
 * *
 * *
 * @return the double
 */
fun grad2Radian(grad: Double) = Math.PI * grad / 180.0
//}
