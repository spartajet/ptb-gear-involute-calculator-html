package de.ptb.length.involute

import de.ptb.length.check.ICheckable
import de.ptb.length.math.Radian2Grad
import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math


/**
 * @description
 * *
 * @create 2017-06-15 上午10:24
 * *
 * @email spartajet.guo@gmail.com
 */
interface IAngleHelix : IAngle, ICheckable {
    fun calculateValue(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAngle, angleLead: IAngle, moduleBasic: ModuleBasic, anglePressureNormal: IAngle, teethNumber: TeethNumber)

    fun calculateContradiction(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAngle, angleLead: IAngle, moduleBasic: ModuleBasic, anglePressureNormal: IAngle, teethNumber: TeethNumber, moduleAxial: ModuleAxial)
    fun calculateResult(result: Boolean)
    /**
     * Calculate beta according to equation 2 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleTransverse transverse module inputValue
     * *
     * @param moduleNormal     normal module inputValue
     * *
     * @return beta calculated
     */
    fun beta01(moduleTransverse: Double, moduleNormal: Double): Double {

        var beta = Double.MAX_VALUE
        try {
            beta = Math.acos(moduleNormal / moduleTransverse)
            calculateResult(true)
            val degree = Radian2Grad(beta)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate beta according to equation 23 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param z                 numbers of teeth inputValue
     * *
     * @param moduleNormal      normal module inputValue
     * *
     * @param diameterReference reference diameter inputValue
     * *
     * @return beta calculated
     */
    fun beta02(z: Int, moduleNormal: Double, diameterReference: Double): Double {

        var beta = Double.MAX_VALUE
        try {
            beta = Math.acos(z * moduleNormal / diameterReference)
            val degree = Radian2Grad(beta)
            calculateResult(true)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate beta according to equation 4.3.2 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param gamma gamma  inputValue
     * *
     * @return gamma calculated
     */
    fun beta03(gamma: Double): Double {
        if (gamma <= 0 || gamma > 90) {
            calculateResult(false)
            return Double.MAX_VALUE
        }
        calculateResult(true)
        return 90 - gamma
    }

    /**
     * Calculate beta according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleTransverse transverse module inputValue
     * *
     * @param moduleNormal     normal module inputValue
     * *
     * @param gamma            gamma inputValue
     * *
     * @return beta calculated
     */
    fun beta04(moduleTransverse: Double, moduleNormal: Double, gamma: Double): Double {

        var beta = Double.MAX_VALUE
        try {
            beta = Math.atan(cos(gamma) * moduleTransverse / moduleNormal)
            val degree = Radian2Grad(beta)
            calculateResult(true)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate beta according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param alphaN normal pressure angle inputValue
     * *
     * @param alphaT pressure angle inputValue
     * *
     * @return beta calculated
     */
    fun beta05(alphaN: Double, alphaT: Double): Double {
        var beta = Double.MAX_VALUE
        try {
            beta = Math.acos(tan(alphaN) / tan(alphaT))
            val degree = Radian2Grad(beta)
            calculateResult(true)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate beta according to equation 2.3 from DIN 3960 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param moduleBasic  basic module inputValue
     * *
     * @param alphaN       normal pressure angle inputValue
     * *
     * @return beta calculated
     */
    fun beta06(moduleNormal: Double, moduleBasic: Double, alphaN: Double): Double {
        val beta: Double
        try {
            val temp = Math.sqrt(Math.pow(moduleNormal, 2.0) / Math.pow(moduleBasic, 2.0) - Math.pow(tan(alphaN), 2.0))
            beta = Math.acos(temp)
            val degree = Radian2Grad(beta)
            calculateResult(true)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate beta according to equation 2.5 from DIN 3960 If the calculation
     * is succeed beta is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param moduleBasic  basic module inputValue
     * *
     * @param alphaT       pressure angle
     * *
     * @return beta calculated
     */
    fun beta07(moduleNormal: Double, moduleBasic: Double, alphaT: Double): Double {
        try {
            val temp = moduleNormal * cos(alphaT) / moduleBasic
            val beta = Math.acos(temp)
            val degree = Radian2Grad(beta)
//            logger.info("beta07 calculate success return value: " + degree)
            calculateResult(true)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
//            logger.info("beta07 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}
