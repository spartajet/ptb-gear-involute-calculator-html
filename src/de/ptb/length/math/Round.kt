package de.ptb.length.math

/**
 * @description
 * @create 2017-07-21 上午9:22
 * @email spartajet.guo@gmail.com
 */
fun Double.format(digits: Int): String = this.asDynamic().toFixed(digits)

fun Float.format(digits: Int): String = this.asDynamic().toFixed(digits)