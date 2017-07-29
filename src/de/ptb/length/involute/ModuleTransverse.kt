package de.ptb.length.involute

import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-22 上午11:15
 * @email spartajet.guo@gmail.com
 */
class ModuleTransverse( fixed: Boolean,  lengthAllowedDotBefore: Int,  lengthAllowedDotAfter: Int,  valueLimitMax: Double,  valueLimitMin: Double,  unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit) {
    fun calculateValue(moduleNormal: ModuleNormal, angleHelix: IAngleHelix, diameterReference: DiameterReference, teethNumber: TeethNumber, angleLead: IAngleLead, anglePressure: IAnglePressure, diameterBase: DiameterBase, anglePressureNormal: IAnglePressureNormal, moduleBasic: ModuleBasic, moduleAxial: ModuleAxial) {
        var mt = Double.MAX_VALUE
        if (!(moduleNormal.calculationSucceed && angleHelix.isCalculationSucceed() && diameterReference.calculationSucceed && teethNumber.calculationSucceed && angleLead.isCalculationSucceed() && anglePressure.isCalculationSucceed() && diameterBase.calculationSucceed && anglePressureNormal.isCalculationSucceed() && moduleBasic.calculationSucceed && moduleAxial.calculationSucceed)) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
            if (!this.calculation_09) {
                Para.onceMore = true
                this.calculation_09 = true
            }
            return
        }
        if (moduleNormal.known && angleHelix.isKnown() && this.calculationSucceed) {
            mt = this.mt01(moduleNormal.result_Value, angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (diameterReference.known && teethNumber.known && this.calculationSucceed) {
            mt = this.mt02(diameterReference.result_Value, teethNumber.resultValue)
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleNormal.known && angleHelix.isKnown() && angleLead.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mt = this.mt03(moduleNormal.result_Value, angleHelix.resultValue(), angleLead.resultValue())
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (diameterBase.known && teethNumber.known && angleLead.isKnown() && this.calculationSucceed) {
            mt = this.mt04(diameterBase.result_Value, teethNumber.resultValue, anglePressure.resultValue())
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (moduleNormal.known && anglePressure.isKnown() && anglePressureNormal.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            mt = this.mt05(moduleNormal.result_Value, anglePressure.resultValue(), anglePressureNormal.resultValue(), angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
        }
        if (moduleBasic.known && anglePressure.isKnown() && this.calculationSucceed) {
            mt = this.mt06(moduleBasic.result_Value, anglePressure.resultValue())
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_06) {
                Para.onceMore = true
                this.calculation_06 = true
            }
        }
        if (moduleAxial.known && angleHelix.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mt = this.mt07(moduleAxial.result_Value, angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_07) {
                Para.onceMore = true
                this.calculation_07 = true
            }
        }
        if (diameterBase.known && anglePressure.isKnown() && teethNumber.known && this.calculationSucceed) {
            mt = this.mt08(diameterBase.result_Value, anglePressure.resultValue(), teethNumber.resultValue)
            this.calculateCount++
            this.refreshValue(mt)
            this.first = true
            if (!this.calculation_08) {
                Para.onceMore = true
                this.calculation_08 = true
            }
        }
    }

    /**
     * Calculate contradiction boolean.

     * @return the boolean
     */
    override fun calculateContradiction(): Boolean {
        return false
    }

    fun calculateContradiction(moduleNormal: ModuleNormal, angleHelix: IAngleHelix, diameterReference: DiameterReference, teethNumber: TeethNumber, angleLead: IAngleLead, anglePressure: IAnglePressure, diameterBase: DiameterBase, anglePressureNormal: IAnglePressureNormal, moduleBasic: ModuleBasic, moduleAxial: ModuleAxial) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && moduleNormal.fixed && angleHelix.isFixed() && diameterReference.fixed && teethNumber.fixed && angleLead.isFixed() && anglePressure.isFixed() && diameterBase.fixed && anglePressureNormal.isFixed() && moduleAxial.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterReference.fixed && teethNumber.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && angleHelix.isFixed() && angleLead.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && teethNumber.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && anglePressure.isFixed() && moduleNormal.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleAxial.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }

    /**
     * Calculate transverse module according to equation 2 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @return transverse module
     */
    private fun mt01(moduleNormal: Double, beta: Double): Double {
        try {
            val moduleTransverse = moduleNormal / cos(beta)
            this.calculationSucceed = true
            //logger.info("mt01 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt01 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate transverse module according to equation 2.4 from DIN 3960
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterReference reference diameter inputValue
     * *
     * @param z                 numbers of teeth inputValue
     * *
     * @return transverse module
     */

    private fun mt02(diameterReference: Double, z: Int): Double {
        try {
            val moduleTransverse = diameterReference / z
            this.calculationSucceed = true
            //logger.info("mt02 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate transverse module according to equation 3 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @param gamma        gamma inputValue
     * *
     * @return transverse module
     */
    private fun mt03(moduleNormal: Double, beta: Double, gamma: Double): Double {
        try {
            val moduleTransverse = moduleNormal * tan(beta) / cos(gamma)
            this.calculationSucceed = true
            //logger.info("mt03 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate transverse module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterBase base diameter inputValue
     * *
     * @param z            numbers of teeth inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @return transverse module
     */
    private fun mt04(diameterBase: Double, z: Int, alphaT: Double): Double {
        try {
            val moduleTransverse = diameterBase / (z * cos(alphaT))
            this.calculationSucceed = true
            //logger.info("mt04 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate transverse module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @param alphaN       normal pressure angle inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @return transverse module
     */
    private fun mt05(moduleNormal: Double, alphaT: Double, alphaN: Double, beta: Double): Double {
        try {
            val moduleTransverse = moduleNormal / (cos(alphaT) * Math.sqrt(Math.pow(tan(alphaN), 2.0) + Math.pow(cos(beta), 2.0)))
            this.calculationSucceed = true
            //logger.info("mt05 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt05 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate transverse module according to equation 2.5 from DIN 3960
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleBasic basic module inputValue
     * *
     * @param alphaT      pressure angle inputValue
     * *
     * @return transverse module
     */
    private fun mt06(moduleBasic: Double, alphaT: Double): Double {
        try {
            val moduleTransverse = moduleBasic / cos(alphaT)
            this.calculationSucceed = true
            //logger.info("mt06 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt06 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate transverse module according to equation 3 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleAxial axial module inputValue
     * *
     * @param beta        beta inputValue
     * *
     * @return transverse module
     */
    private fun mt07(moduleAxial: Double, beta: Double): Double {
        try {
            val moduleTransverse = moduleAxial * tan(beta)
            this.calculationSucceed = true
            //logger.info("mt07 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt07 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate transverse module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed transverse module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterBase base diameter inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @param z            number of teeth inputValue
     * *
     * @return transverse module
     */
    private fun mt08(diameterBase: Double, alphaT: Double, z: Int): Double {
        try {
            val moduleTransverse = diameterBase * Math.sqrt(Math.pow(tan(alphaT), 2.0) + 1) / z
            this.calculationSucceed = true
            //logger.info("mt08 calculate success return value: " + moduleTransverse)
            return moduleTransverse
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mt08 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}