package de.ptb.length.involute

import de.ptb.length.check.ICheckable
import de.ptb.length.math.Radian2Grad
import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math


/**
 * @description
 * *
 * @create 2017-06-15 上午10:25
 * *
 * @email spartajet.guo@gmail.com
 */
interface IAnglePressureNormal : IAngle, ICheckable {
    fun calculateValue(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse)

    fun calculateContradiction(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse)

    fun calculateResult(result: Boolean)

    /**
     * Calculate normal pressure angle according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param beta   beta value
     * *
     * @param alphaT pressure angle value
     * *
     * @return normal pressure angle calculated
     */
     fun alphaN01(beta: Double, alphaT: Double): Double {
        try {
            val alphaN = Math.atan(cos(beta) * tan(alphaT))
            val degree = Radian2Grad(alphaN)
            //logger.info("alphaN01 calculate success return value: " + degree)
            this.calculateResult(true)
            return degree
        } catch (e: Exception) {
            //logger.info("alphaN01 calculate fail, return double max")
            this.calculateResult(false)
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal pressure angle according to equation 19 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param z            numbers of teeth value
     * *
     * @param moduleNormal module normal value
     * *
     * @param diameterBase base diameter value
     * *
     * @param beta         beta value
     * *
     * @return normal pressure angle calculated
     */
     fun alphaN02(z: Int, moduleNormal: Double, diameterBase: Double, beta: Double): Double {
        try {
            val alphaN = Math.atan(Math.sqrt(Math.pow(z * moduleNormal / diameterBase, 2.0) - Math.pow(cos(beta), 2.0)))
            val degree = Radian2Grad(alphaN)
            this.calculateResult(true)
            //logger.info("alphaN02 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculateResult(false)
            //logger.info("alphaN02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal pressure angle according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal normal module value
     * *
     * @param moduleBasic  basic module value
     * *
     * @param beta         beta value
     * *
     * @return normal pressure angle calculated
     */
     fun alphaN03(moduleNormal: Double, moduleBasic: Double, beta: Double): Double {
        try {
            val alphaN = Math.atan(Math.sqrt(Math.pow(moduleNormal, 2.0) / Math.pow(moduleBasic, 2.0) - Math.pow(cos(beta), 2.0)))
            val degree = Radian2Grad(alphaN)
            this.calculateResult(true)
            //logger.info("alphaN03 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculateResult(false)
            //logger.info("alphaN03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal pressure angle calculated according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed normal pressure angle calculated is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal     normal module value
     * *
     * @param moduleTransverse transverse module value
     * *
     * @param alfaT            pressure angle value
     * *
     * @return normal pressure angle calculated
     */
     fun alphaN04(moduleNormal: Double, moduleTransverse: Double, alfaT: Double): Double {
        try {
            val alphaN = Math.atan(moduleNormal / moduleTransverse * tan(alfaT))
            val degree = Radian2Grad(alphaN)
            this.calculateResult(true)
            ////logger.info("alphaN04 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculateResult(false)
            //logger.info("alphaN04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}
