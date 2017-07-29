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
interface IAnglePressure : IAngle, ICheckable {
    fun calculateValue(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic)

    fun calculateContradiction(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic)
    fun calculateResult(result: Boolean)
    /**
     * Calculate pressure angle according to equation 14 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param alphaN normal pressure angle inputValue
     * *
     * @param beta   beta inputValue
     * *
     * @return pressure angle calculated
     */
     fun alphaT01(alphaN: Double, beta: Double): Double {
        try {
            val alphaT = Math.atan(tan(alphaN) / cos(beta))
            val degree = Radian2Grad(alphaT)
            calculateResult(true)
//            //logger.info("alphaT01 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
//            //logger.info("alphaT01 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate pressure angle according to equation 2 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param baseDiameter      base diameter inputValue
     * *
     * @param referenceDiameter reference diameter inputValue
     * *
     * @return pressure angle calculated
     */
     fun alphaT02(baseDiameter: Double, referenceDiameter: Double): Double {
        try {
            val temp = baseDiameter / referenceDiameter
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            calculateResult(true)
            //logger.info("alphaT02 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            //logger.info("alphaT02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate pressure angle according to equation 4.3.2 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param baseDiameter     base diameter inputValue
     * *
     * @param z                numbers of teeth inputValue
     * *
     * @param transverseModule transverse module inputValue
     * *
     * @return pressure angle calculated
     */
     fun alphaT03(baseDiameter: Double, z: Int, transverseModule: Double): Double {
        try {
            val temp = baseDiameter / (z * transverseModule)
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            calculateResult(true)
            //logger.info("alphaT03 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            //logger.info("alphaT03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate pressure angle according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param baseDiameter     base diameter inputValue
     * *
     * @param beta             beta inputValue
     * *
     * @param z                numbers of teeth inputValue
     * *
     * @param transverseModule transverse module inputValue
     * *
     * @return pressure angle calculated
     */
     fun alphaT04(baseDiameter: Double, beta: Double, z: Int, transverseModule: Double): Double {
        try {
            val temp = baseDiameter * cos(beta) / (z * transverseModule)
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            calculateResult(true)
            //logger.info("alphaT04 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            //logger.info("alphaT04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate pressure angle according to equation 2.5 from DIN 3960 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param basicModule  basic module inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @param normalModule normal module inputValue
     * *
     * @return pressure angle calculated
     */
     fun alphaT05(basicModule: Double, beta: Double, normalModule: Double): Double {
        try {
            val temp = basicModule * cos(beta) / normalModule
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            calculateResult(true)
            //logger.info("alphaT05 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            //logger.info("alphaT05 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate pressure angle according to equation 2.5 from DIN 3960 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param basicModule      basic module inputValue
     * *
     * @param transverseModule transverse module inputValue
     * *
     * @return pressure angle calculated
     */
     fun alphaT06(basicModule: Double, transverseModule: Double): Double {
        try {
            val temp = basicModule / transverseModule
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            calculateResult(true)
            //logger.info("alphaT06 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            //logger.info("alphaT06 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate pressure angle according to equation 2.5 from DIN 3960 If the calculation
     * is succeed pressure angle is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param basicModule       basic module inputValue
     * *
     * @param z                 numbers of teeth inputValue
     * *
     * @param referenceDiameter reference diameter inputValue
     * *
     * @return pressure angle calculated
     */
     fun alphaT07(basicModule: Double, z: Int, referenceDiameter: Double): Double {
        try {
            val temp = basicModule * z / referenceDiameter
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            calculateResult(true)
            //logger.info("alphaT07 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            calculateResult(false)
            //logger.info("alphaT07 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}
