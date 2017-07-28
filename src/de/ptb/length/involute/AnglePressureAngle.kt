package de.ptb.length.involute

import de.ptb.length.math.Radian2Grad
import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-21 下午1:27
 * @email spartajet.guo@gmail.com
 */
class AnglePressureAngle(override var fixed: Boolean, override val unit: String, override var valueLimitMax: Angle, override var valueLimitMin: Angle, override val digitsAfterDotSecond: Int) : ParaAngle(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond), IAnglePressure {
    
    override fun calculateValue(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic) {
        var alphaT = Double.MAX_VALUE
        if (!(anglePressureNormal.isCalculationSucceed() && angleHelix.isCalculationSucceed() && diameterReference.calculationSucceed && diameterBase.calculationSucceed && teethNumber.calculationSucceed && moduleTransverse.calculationSucceed && moduleBasic.calculationSucceed && moduleNormal.calculationSucceed)) {
            this.calculationSucceed = false
            this.result_Value = Angle(ANGLE_MAX_VALUE)
            this.resultValueMax = Angle(ANGLE_MAX_VALUE)
            this.resultValueMin = Angle(ANGLE_MAX_VALUE)
            if (!this.calculation_08) {
                Para.onceMore = true
                this.calculation_08 = true
            }
            return
        }
        if (anglePressureNormal.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT01(anglePressureNormal.resultValue(), angleHelix.resultValue())
            this.refreshValue(Angle(alphaT))
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (diameterBase.known && diameterReference.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT02(diameterBase.result_Value, diameterReference.result_Value)
            this.refreshValue(Angle(alphaT))
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (diameterBase.known && teethNumber.known && moduleTransverse.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT03(diameterBase.result_Value, teethNumber.resultValue, moduleTransverse.result_Value)
            this.refreshValue(Angle(alphaT))
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (diameterBase.known && angleHelix.isKnown() && teethNumber.known && moduleNormal.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT04(diameterBase.result_Value, angleHelix.resultValue(), teethNumber.resultValue, moduleTransverse.result_Value)
            this.refreshValue(Angle(alphaT))
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (moduleBasic.known && angleHelix.isKnown() && moduleNormal.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT05(moduleBasic.result_Value, angleHelix.resultValue(), moduleNormal.result_Value)
            this.refreshValue(Angle(alphaT))
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
        }
        if (moduleBasic.known && moduleTransverse.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT06(moduleBasic.result_Value, moduleTransverse.result_Value)
            this.refreshValue(Angle(alphaT))
            this.first = true
            if (!this.calculation_06) {
                Para.onceMore = true
                this.calculation_06 = true
            }
        }
        if (moduleBasic.known && teethNumber.known && diameterReference.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT07(moduleBasic.result_Value, teethNumber.resultValue, diameterReference.result_Value)
            this.refreshValue(Angle(alphaT))
            this.first = true
            if (!this.calculation_07) {
                Para.onceMore = true
                this.calculation_07 = true
            }
        }
    }

    override fun calculateContradiction(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic) {
        if (this.result_Value == ANGLE_MAX_VALUE) {
            this.angle_Contradiction = Angle(ANGLE_MAX_VALUE)
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed() && diameterReference.fixed && diameterBase.fixed && teethNumber.fixed && moduleTransverse.fixed && moduleNormal.fixed && moduleBasic.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && diameterBase.fixed && diameterReference.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && diameterBase.fixed && teethNumber.fixed && moduleTransverse.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && diameterBase.fixed && angleHelix.isFixed() && teethNumber.fixed && moduleTransverse.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && diameterBase.fixed && angleHelix.isFixed() && teethNumber.fixed && moduleTransverse.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleBasic.fixed && angleHelix.isFixed() && moduleNormal.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleBasic.fixed && moduleTransverse.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleBasic.fixed && teethNumber.fixed && diameterReference.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else {
            this.angle_Contradiction = Angle()
        }
    }


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
    private fun alphaT01(alphaN: Double, beta: Double): Double {
        try {
            val alphaT = Math.atan(tan(alphaN) / cos(beta))
            val degree = Radian2Grad(alphaT)
            this.calculationSucceed = true
//            //logger.info("alphaT01 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaT02(baseDiameter: Double, referenceDiameter: Double): Double {
        try {
            val temp = baseDiameter / referenceDiameter
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            this.calculationSucceed = true
            //logger.info("alphaT02 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaT03(baseDiameter: Double, z: Int, transverseModule: Double): Double {
        try {
            val temp = baseDiameter / (z * transverseModule)
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            this.calculationSucceed = true
            //logger.info("alphaT03 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaT04(baseDiameter: Double, beta: Double, z: Int, transverseModule: Double): Double {
        try {
            val temp = baseDiameter * cos(beta) / (z * transverseModule)
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            this.calculationSucceed = true
            //logger.info("alphaT04 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaT05(basicModule: Double, beta: Double, normalModule: Double): Double {
        try {
            val temp = basicModule * cos(beta) / normalModule
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            this.calculationSucceed = true
            //logger.info("alphaT05 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaT06(basicModule: Double, transverseModule: Double): Double {
        try {
            val temp = basicModule / transverseModule
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            this.calculationSucceed = true
            //logger.info("alphaT06 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaT07(basicModule: Double, z: Int, referenceDiameter: Double): Double {
        try {
            val temp = basicModule * z / referenceDiameter
            val alphaT = Math.acos(temp)
            val degree = Radian2Grad(alphaT)
            this.calculationSucceed = true
            //logger.info("alphaT07 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("alphaT07 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

}