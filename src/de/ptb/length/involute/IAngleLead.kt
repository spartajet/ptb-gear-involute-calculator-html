package de.ptb.length.involute


import de.ptb.length.check.ICheckable
import de.ptb.length.math.Radian2Grad
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * *
 * @create 2017-06-15 上午10:24
 * *
 * @email spartajet.guo@gmail.com
 */
interface IAngleLead : IAngle, ICheckable {
    fun calculateContradiction(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle)

    fun calculateValue(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle)
    fun calculateResult(result: Boolean)
    /**
     * Calculate gamma according to equation 4.3.2 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param beta beta  inputValue
     * *
     * @return gamma calculated
     */
    fun gamma01(beta: Double): Double {
        if (beta < 0 || beta >= 90) {
//            logger.info("gamma01 calculate fail, return double max")
            calculateResult(false)
            return Double.MAX_VALUE
        }
        val gamma = 90 - beta
//        logger.info("gamma01 calculate success return value: " + gamma)
        calculateResult(true)
        return gamma
    }


    /**
     * Calculate gamma according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param moduleAxial  axis module inputValue
     * *
     * @return gamma calculated
     */
    fun gamma02(moduleNormal: Double, moduleAxial: Double): Double {
        try {
            val temp = moduleNormal / moduleAxial
            val gamma = Math.acos(temp)
            val degree = Radian2Grad(gamma)
//            logger.info("gamma02 calculate success return value: " + degree)
            calculateResult(true)
            return degree
        } catch (e: Exception) {
//            logger.info("gamma02 calculate fail, return double max")
            calculateResult(false)
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate gamma according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal     normal module inputValue
     * *
     * @param beta             beta inputValue
     * *
     * @param moduleTransverse transverse module inputValue
     * *
     * @return beta calculated
     */
    fun gamma03(moduleNormal: Double, beta: Double, moduleTransverse: Double): Double {
        try {
            val temp = moduleNormal * tan(beta) / moduleTransverse
            val gamma = Math.acos(temp)
            val degree = Radian2Grad(gamma)
//            logger.info("gamma03 calculate success return value: " + degree)
            calculateResult(true)
            return degree
        } catch (e: Exception) {
//            logger.info("gamma03 calculate fail, return double max")
            calculateResult(false)
            return Double.MAX_VALUE
        }

    }
}
