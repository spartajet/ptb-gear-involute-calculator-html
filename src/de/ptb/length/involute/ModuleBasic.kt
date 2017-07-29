package de.ptb.length.involute

import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-22 上午10:56
 * @email spartajet.guo@gmail.com
 */
class ModuleBasic( fixed: Boolean,  lengthAllowedDotBefore: Int,  lengthAllowedDotAfter: Int,  valueLimitMax: Double,  valueLimitMin: Double,  unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit) {

    /**
     * Calculate value.

     * @param moduleNormal            the module normal
     * *
     * @param anglePressureNormal the angle pressure normal real
     * *
     * @param angleHelix          the angle helix real
     * *
     * @param diameterBase            the diameter base
     * *
     * @param teethNumber             the teeth number
     * *
     * @param anglePressure       the angle pressure real
     * *
     * @param moduleTransverse        the module transverse
     * *
     * @param diameterReference       the diameter reference
     */
    fun calculateValue(moduleNormal: ModuleNormal, anglePressureNormal: IAnglePressureNormal, angleHelix: IAngleHelix, diameterBase: DiameterBase, teethNumber: TeethNumber, anglePressure: IAnglePressure, moduleTransverse: ModuleTransverse, diameterReference: DiameterReference) {
        var mb = Double.MAX_VALUE
        if (!(moduleNormal.calculationSucceed && anglePressureNormal.isCalculationSucceed() && angleHelix.isCalculationSucceed() && diameterBase.calculationSucceed && teethNumber.calculationSucceed && anglePressure.isCalculationSucceed() && moduleTransverse.calculationSucceed && diameterReference.calculationSucceed)) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
            if (!this.calculation_06) {
                Para.onceMore = true
                this.calculation_06 = true
            }
            return
        }
        if (moduleNormal.known && anglePressureNormal.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            mb = this.mb01(moduleNormal.result_Value, anglePressureNormal.resultValue(), angleHelix.resultValue())
            this.refreshValue(mb)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (diameterBase.known && teethNumber.known && this.calculationSucceed) {
            mb = this.mb02(diameterBase.result_Value, teethNumber.resultValue)
            this.calculateCount++
            this.refreshValue(mb)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleNormal.known && anglePressure.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            mb = this.mb03(moduleNormal.result_Value, anglePressure.resultValue(), angleHelix.resultValue())
            this.calculateCount++
            this.refreshValue(mb)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (moduleTransverse.known && anglePressure.isKnown() && this.calculationSucceed) {
            mb = this.mb04(moduleTransverse.result_Value, anglePressure.resultValue())
            this.calculateCount++
            this.refreshValue(mb)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (diameterReference.known && anglePressure.isKnown() && teethNumber.known && this.calculationSucceed) {
            mb = this.mb05(diameterReference.result_Value, anglePressure.resultValue(), teethNumber.resultValue)
            this.calculateCount++
            this.refreshValue(mb)
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
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

    fun calculateContradiction(moduleNormal: ModuleNormal, anglePressureNormal: IAnglePressureNormal, angleHelix: IAngleHelix, diameterBase: DiameterBase, teethNumber: TeethNumber, anglePressure: IAnglePressure, moduleTransverse: ModuleTransverse, diameterReference: DiameterReference) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && moduleNormal.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed() && teethNumber.fixed && anglePressure.isFixed() && moduleTransverse.fixed && diameterReference.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && anglePressure.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && teethNumber.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && anglePressure.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleTransverse.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterReference.fixed && anglePressure.isFixed() && teethNumber.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }

    /**
     * Calculate basic module  according to equation 2.3 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param alphaN       normal pressure angle inputValue
     * *
     * @param beta         beta inputValue
     * *
     * *
     * @return basic module
     */
    private fun mb01(moduleNormal: Double, alphaN: Double, beta: Double): Double {
        try {
            val moduleBasic = moduleNormal / Math.sqrt(Math.pow(tan(alphaN), 2.0) + Math.pow(cos(beta), 2.0))
            this.calculationSucceed = true
            //logger.info("mb01 calculate success return value: " + moduleBasic)
            return moduleBasic
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mb01 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterBase base diameter inputValue
     * *
     * @param z            numbers of teeth inputValue
     * *
     * *
     * @return basic module
     */
    private fun mb02(diameterBase: Double, z: Int): Double {
        try {
            val moduleBasic = diameterBase / z
            this.calculationSucceed = true
            //logger.info("mb02 calculate success return value: " + moduleBasic)
            return moduleBasic
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mb02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @param beta         beta inputValue
     * *
     * *
     * @return basic module
     */
    private fun mb03(moduleNormal: Double, alphaT: Double, beta: Double): Double {
        try {
            val moduleBasic = moduleNormal * (cos(alphaT) / cos(beta))
            this.calculationSucceed = true
            //logger.info("mb03 calculate success return value: " + moduleBasic)
            return moduleBasic
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mb03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleTransverse transverse module inputValue
     * *
     * @param alphaT           pressure angle inputValue
     * *
     * *
     * @return basic module
     */
    private fun mb04(moduleTransverse: Double, alphaT: Double): Double {
        try {
            val moduleBasic = moduleTransverse * cos(alphaT)
            this.calculationSucceed = true
            //logger.info("mb04 calculate success return value: " + moduleBasic)
            return moduleBasic
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mb04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate basic module  according to equation 2.5 from DIN 3960
     * If the calculation is succeed basic module is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterReference reference diameter inputValue
     * *
     * @param alphaT            pressure angle inputValue
     * *
     * @param z                 numbers of teeth inputValue
     * *
     * *
     * @return basic module
     */
    private fun mb05(diameterReference: Double, alphaT: Double, z: Int): Double {
        try {
            val moduleBasic = diameterReference * cos(alphaT) / z
            this.calculationSucceed = true
            //logger.info("mb05 calculate success return value: " + moduleBasic)
            return moduleBasic
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mb05 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}