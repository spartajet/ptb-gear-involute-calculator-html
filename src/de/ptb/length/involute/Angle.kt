package de.ptb.length.involute

/**
 * The type Angle.

 * @description
 * *
 * @create 2017 -05-09 下午2:12
 * *
 * @email spartajet.guo @gmail.com
 *
 */


class Angle(var degree: Int = 0, var minute: Int = 0, var second: Double = 0.0) : Comparable<Angle> {


    /**
     * The Angle string.
     */
    private var angleString: String? = null

    /**
     * Instantiates a new Angle.
     */
    init {
        if (second >= 60.0) {
            this.minute = minute + 1
            this.second = second - 60.0
        } else {
            this.second = second
        }
        if (this.minute >= 60) {
            this.degree = degree + 1
            this.minute -= 60
        } else {
            this.minute = minute
            this.degree = degree
        }
    }

    constructor(value: Double) : this() {
        this.degree = value.toInt()
        this.minute = ((value - degree) * 60).toInt()
        this.second = ((value - degree) * 60 - minute) * 60
    }

    fun toReal(): Double {
        return this.degree.toDouble() + this.minute / 60.0 + this.second / 3600.0
    }

    constructor(angle: Angle) : this(angle.degree, angle.minute, angle.second)

    /**
     * Compare to int.

     * @param angle the angle
     * *
     * @return the int
     */
    override fun compareTo(angle: Angle): Int {
        if (this.degree == angle.degree && this.minute == angle.minute && this.second == angle.second) {
            return 0
        }
        if (this.degree > angle.degree) {
            return 1
        }
        if (this.degree == angle.degree && this.minute > angle.minute) {
            return 1
        }
        if (this.degree == angle.degree && this.minute == angle.minute && this.second > angle.second) {
            return 1
        }
        return -1
    }

    fun bigger(angle: Angle): Boolean {
        return this.compareTo(angle) == 1
    }

    fun smaller(angle: Angle): Boolean {
        return this.compareTo(angle) == -1
    }

    operator fun minus(angle: Angle): Angle {
        return Angle(this.toReal() - angle.toReal())
    }

    operator fun plus(angle: Angle): Angle {
        return Angle(this.toReal() + angle.toReal())
    }

    override fun equals(other: Any?): Boolean {
        if (other is Angle) {
            if (this.degree == other.degree && this.minute == other.minute && this.second - other.second < 0.00000000000001) {
                return true
            }
        }
        return false
    }

    /**
     * Clear.
     */
    fun clear() {
        this.degree = 0
        this.minute = 0
        this.second = 0.0
        this.angleString = ""
    }

    /**
     * To string string.

     * @return the string
     */
    override fun toString(): String {
        return this.degree.toString() + "°" + this.minute + "'" + this.second + "\""
    }

    override fun hashCode(): Int {
        var result = degree
        result = 31 * result + minute
        result = 31 * result + second.hashCode()
        return result
    }
}
val ANGLE_MAX_VALUE = Angle(Int.MAX_VALUE, 0, 0.0)
val ANGLE_MIN_VALUE = Angle(-Int.MAX_VALUE, 0, 0.0)
val ANGLE_ZERO_VALUE = Angle(0, 0, 0.0)