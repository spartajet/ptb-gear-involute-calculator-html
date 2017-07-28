package de.ptb.length.involute

import de.ptb.length.math.cos
import de.ptb.length.math.sin
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-22 上午11:00
 * @email spartajet.guo@gmail.com
 */
class ModuleNormal(override var fixed: Boolean, override val lengthAllowedDotBefore: Int, override val lengthAllowedDotAfter: Int, override val valueLimitMax: Double, override val valueLimitMin: Double, override val unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit) {
    fun calculateValue(moduleTransverse: ModuleTransverse, angleHelix: IAngleHelix, diameterReference: DiameterReference, teethNumber: TeethNumber, angleLead: IAngleLead, anglePressure: IAnglePressure, diameterBase: DiameterBase, anglePressureNormal: IAnglePressureNormal, moduleBasic: ModuleBasic, moduleAxial: ModuleAxial) {
        var mn = Double.MAX_VALUE
        if (!(moduleTransverse.calculationSucceed && angleHelix.isCalculationSucceed() && diameterReference.calculationSucceed && teethNumber.calculationSucceed && angleLead.isCalculationSucceed() && anglePressure.isCalculationSucceed() && diameterBase.calculationSucceed && anglePressureNormal.isCalculationSucceed() && moduleBasic.calculationSucceed && moduleAxial.calculationSucceed)) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
            if (!this.calculation_11) {
                Para.onceMore = true
                this.calculation_11 = true
            }
            return
        }
        if (moduleTransverse.known && angleHelix.isKnown() && this.calculationSucceed) {
            mn = this.mn01(moduleTransverse.result_Value, angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (diameterReference.known && angleHelix.isKnown() && teethNumber.known && this.calculationSucceed) {
            mn = this.mn02(diameterReference.result_Value, angleHelix.resultValue(), teethNumber.resultValue.toDouble())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleTransverse.known && angleLead.isKnown() && angleHelix.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mn = this.mn03(moduleTransverse.result_Value, angleLead.resultValue(), angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (diameterBase.known && angleHelix.isKnown() && teethNumber.known && anglePressure.isKnown() && this.calculationSucceed) {
            mn = this.mn04(diameterBase.result_Value, angleHelix.resultValue(), teethNumber.resultValue, anglePressure.resultValue())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (diameterBase.known && anglePressure.isKnown() && angleHelix.isKnown() && teethNumber.known && this.calculationSucceed) {
            mn = this.mn05(diameterBase.result_Value, anglePressure.resultValue(), angleHelix.resultValue(), teethNumber.resultValue)
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
        }
        if (moduleBasic.known && anglePressure.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            mn = this.mn06(moduleBasic.result_Value, anglePressure.resultValue(), angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_06) {
                Para.onceMore = true
                this.calculation_06 = true
            }
        }
        if (moduleAxial.known && angleLead.isKnown() && this.calculationSucceed) {
            mn = this.mn07(moduleAxial.result_Value, angleLead.resultValue())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_07) {
                Para.onceMore = true
                this.calculation_07 = true
            }
        }
        if (moduleAxial.known && angleHelix.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            mn = this.mn08(moduleAxial.result_Value, angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_08) {
                Para.onceMore = true
                this.calculation_08 = true
            }
        }
        if (diameterBase.known && angleHelix.isKnown() && anglePressureNormal.isKnown() && teethNumber.known && this.calculationSucceed) {
            mn = this.mn09(diameterBase.result_Value, angleHelix.resultValue(), anglePressure.resultValue(), teethNumber.resultValue)
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_09) {
                Para.onceMore = true
                this.calculation_09 = true
            }
        }
        if (moduleBasic.known && angleHelix.isKnown() && angleLead.isKnown() && this.calculationSucceed) {
            mn = this.mn10(moduleBasic.result_Value, angleHelix.resultValue(), anglePressure.resultValue())
            this.calculateCount++
            this.refreshValue(mn)
            this.first = true
            if (!this.calculation_10) {
                Para.onceMore = true
                this.calculation_10 = true
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

    fun calculateContradiction(moduleTransverse: ModuleTransverse, angleHelix: IAngleHelix, diameterReference: DiameterReference, teethNumber: TeethNumber, angleLead: IAngleLead, anglePressure: IAnglePressure, diameterBase: DiameterBase, anglePressureNormal: IAnglePressureNormal, moduleBasic: ModuleBasic, moduleAxial: ModuleAxial) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && moduleTransverse.fixed && angleHelix.isFixed() && diameterReference.fixed && teethNumber.fixed && angleLead.isFixed() && anglePressure.isFixed() && diameterBase.fixed && anglePressureNormal.isFixed() && moduleBasic.fixed && moduleAxial.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleTransverse.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterReference.fixed && angleHelix.isFixed() && teethNumber.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleTransverse.fixed && angleLead.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && angleHelix.isFixed() && teethNumber.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed() && teethNumber.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleAxial.fixed && angleLead.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleAxial.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && angleHelix.isFixed() && anglePressure.isFixed() && teethNumber.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && angleHelix.isFixed() && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }

    /**
     * Calculate normal module according to equation 2 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleTransverse transverse module inputValue
     * *
     * @param beta             beta inputValue
     * *
     * @return normal module
     */
    private fun mn01(moduleTransverse: Double, beta: Double): Double {
        try {
            val moduleNormal = moduleTransverse * cos(beta)
            this.calculationSucceed = true
            //logger.info("mn01 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn01 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module according to equation 1 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterReference reference diameter inputValue
     * *
     * @param beta              beta inputValue
     * *
     * @param z                 numbers of teeth inputValue
     * *
     * @return normal module
     */
    private fun mn02(diameterReference: Double, beta: Double, z: Double): Double {
        try {
            val moduleNormal = diameterReference * cos(beta) / z
            this.calculationSucceed = true
            //logger.info("mn02 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate normal module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleTransverse transverse module inputValue
     * *
     * @param gamma            gamma inputValue
     * *
     * @param beta             beta inputValue
     * *
     * @return normal module
     */
    private fun mn03(moduleTransverse: Double, gamma: Double, beta: Double): Double {
        try {
            val moduleNormal = cos(gamma) / tan(beta) * moduleTransverse
            this.calculationSucceed = true
            //logger.info("mn03 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module  according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterBase base diameter inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @param z            numbers of teeth inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @return normal module
     */
    private fun mn04(diameterBase: Double, beta: Double, z: Int, alphaT: Double): Double {
        try {
            val moduleNormal = diameterBase * cos(beta) / (z * cos(alphaT))
            this.calculationSucceed = true
            //logger.info("mn04 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module  according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterBase base diameter inputValue
     * *
     * @param alphaN       normal pressure angle inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @param z            numbers of teeth inputValue
     * *
     * @return normal module
     */
    private fun mn05(diameterBase: Double, alphaN: Double, beta: Double, z: Int): Double {
        try {
            val moduleNormal = diameterBase * Math.sqrt(Math.pow(tan(alphaN), 2.0) + Math.pow(cos(beta), 2.0)) / z
            this.calculationSucceed = true
            //logger.info("mn05 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn05 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleBasic basic module inputValue
     * *
     * @param alphaN      normal pressure angle inputValue
     * *
     * @param beta        beta inputValue
     * *
     * @return normal module
     */
    private fun mn06(moduleBasic: Double, alphaN: Double, beta: Double): Double {
        try {
            val moduleNormal = moduleBasic * Math.sqrt(Math.pow(tan(alphaN), 2.0) + Math.pow(cos(beta), 2.0))
            this.calculationSucceed = true
            //logger.info("mn06 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn06 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleAxial axial module inputValue
     * *
     * @param gamma       gamma inputValue
     * *
     * @return normal module
     */
    private fun mn07(moduleAxial: Double, gamma: Double): Double {
        try {
            val moduleNormal = moduleAxial * cos(gamma)
            this.calculationSucceed = true
            //logger.info("mn07 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn07 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module according to equation 3 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleAxial axial module inputValue
     * *
     * @param beta        beta inputValue
     * *
     * @return normal module
     */
    private fun mn08(moduleAxial: Double, beta: Double): Double {
        try {
            val moduleNormal = moduleAxial * sin(beta)
            this.calculationSucceed = true
            //logger.info("mn08 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn08 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module  according to equation 19 from ISO 21771:2007
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterBase base diameter inputValue
     * *
     * @param beta         beta angle inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @param z            numbers of teeth inputValue
     * *
     * @return normal module
     */
    private fun mn09(diameterBase: Double, beta: Double, alphaT: Double, z: Int): Double {
        try {
            val moduleNormal = diameterBase * cos(beta) * Math.sqrt(Math.pow(tan(alphaT), 2.0) + 1) / z
            this.calculationSucceed = true
            //logger.info("mn09 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn09 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate normal module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed normal module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleBasic basic module inputValue
     * *
     * @param beta        beta inputValue
     * *
     * @param alphaT      pressure angle inputValue
     * *
     * @return normal module
     */
    private fun mn10(moduleBasic: Double, beta: Double, alphaT: Double): Double {
        try {
            val moduleNormal = moduleBasic * cos(beta) / cos(alphaT)
            this.calculationSucceed = true
            //logger.info("mn10 calculate success return value: " + moduleNormal)
            return moduleNormal
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mn10 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}