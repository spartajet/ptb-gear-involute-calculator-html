package de.ptb.length.involute

import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-21 下午2:23
 * @email spartajet.guo@gmail.com
 */
class DiameterReference(override var fixed: Boolean, override val lengthAllowedDotBefore: Int, override val lengthAllowedDotAfter: Int, override val valueLimitMax: Double, override val valueLimitMin: Double, override val unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit) {
    fun calculateValue(moduleTransverse: ModuleTransverse, angleHelix: IAngleHelix, moduleNormal: ModuleNormal, teethNumber: TeethNumber, diameterBase: DiameterBase, anglePressure: IAnglePressure, anglePressureNormal: IAnglePressureNormal, moduleBasic: ModuleBasic) {
        var d = Double.MAX_VALUE
        if (!(moduleTransverse.calculationSucceed && angleHelix.isCalculationSucceed() && moduleNormal.calculationSucceed && teethNumber.calculationSucceed && diameterBase.calculationSucceed && anglePressure.isCalculationSucceed() && anglePressureNormal.isCalculationSucceed() && moduleBasic.calculationSucceed && this.calculationSucceed)) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
            return
        }
        if (moduleTransverse.known && teethNumber.known && this.calculationSucceed) {
            this.calculateCount++
            d = this.d01(moduleTransverse.result_Value, teethNumber.resultValue)
            this.refreshValue(d)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (teethNumber.known && moduleNormal.known && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            d = this.d02(teethNumber.resultValue, moduleNormal.result_Value, angleHelix.resultValue())
            this.refreshValue(d)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (diameterBase.known && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            d = this.d03(diameterBase.result_Value, anglePressure.resultValue())
            this.refreshValue(d)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (teethNumber.known && moduleNormal.known && anglePressure.isKnown() && anglePressureNormal.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            d = this.d04(teethNumber.resultValue, moduleNormal.result_Value, anglePressure.resultValue(), anglePressureNormal.resultValue(), angleHelix.resultValue())
            this.refreshValue(d)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (moduleBasic.known && teethNumber.known && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            d = this.d05(moduleBasic.result_Value, teethNumber.resultValue, anglePressure.resultValue())
            this.refreshValue(d)
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
        }
    }

    fun calculateContradiction(moduleTransverse: ModuleTransverse, angleHelix: IAngleHelix, moduleNormal: ModuleNormal, teethNumber: TeethNumber, diameterBase: DiameterBase, anglePressure: IAnglePressure, anglePressureNormal: IAnglePressureNormal, moduleBasic: ModuleBasic) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && moduleTransverse.fixed && angleHelix.isFixed() && moduleNormal.fixed && teethNumber.fixed && diameterBase.fixed && anglePressure.isFixed() && anglePressureNormal.isFixed() && moduleBasic.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleTransverse.fixed && teethNumber.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && teethNumber.fixed && moduleNormal.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && teethNumber.fixed && moduleNormal.fixed && anglePressure.isFixed() && anglePressureNormal.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && teethNumber.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }

    /**
     * Calculate reference diameter according to equation 1 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param transverseModule transverse normal inputValue
     * *
     * @param z                numbers of teeth inputValue
     * *
     * @return reference diameter
     */
    private fun d01(transverseModule: Double, z: Int): Double {
        try {
            val diameterReference = transverseModule * z
            this.calculationSucceed = true
            //logger.info("d01 calculate success return value: " + diameterReference)
            return diameterReference
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("d01 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate reference diameter according to equation 1 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param z            numbers of teeth inputValue
     * *
     * @param moduleNormal normal module inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @return reference diameter
     */
    private fun d02(z: Int, moduleNormal: Double, beta: Double): Double {
        try {
            val diameterReference = z * moduleNormal / cos(beta)
            this.calculationSucceed = true
            //logger.info("d02 calculate success return value: " + diameterReference)
            return diameterReference
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("d02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate reference diameter according to equation 13 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterBase base diameter inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @return reference diameter
     */
    private fun d03(diameterBase: Double, alphaT: Double): Double {
        try {
            val diameterReference = diameterBase / cos(alphaT)
            this.calculationSucceed = true
            //logger.info("d03 calculate success return value: " + diameterReference)
            return diameterReference
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("d03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate reference diameter according to equation 19 from ISO 21771:2007
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param z            numbers of teeth inputValue
     * *
     * @param moduleNormal normal module inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @param alphaN       normal pressure angle inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @return reference diameter
     */
    private fun d04(z: Int, moduleNormal: Double, alphaT: Double, alphaN: Double, beta: Double): Double {
        try {
            val diameterReference = z * moduleNormal / (cos(alphaT) * Math.sqrt(Math.pow(tan(alphaN), 2.0) + Math.pow(cos(beta), 2.0)))
            this.calculationSucceed = true
            //logger.info("d04 calculate success return value: " + diameterReference)
            return diameterReference
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("d04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate reference diameter according to equation 2.5 DIN 3960
     * If the calculation is succeed reference diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleBasic basic module inputValue
     * *
     * @param z           numbers of teeth inputValue
     * *
     * @param alphaT      pressure angle inputValue
     * *
     * @return reference diameter
     */
    private fun d05(moduleBasic: Double, z: Int, alphaT: Double): Double {
        try {
            val diameterReference = moduleBasic * z / cos(alphaT)
            this.calculationSucceed = true
            //logger.info("d05 calculate success return value: " + diameterReference)
            return diameterReference
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("d05 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}