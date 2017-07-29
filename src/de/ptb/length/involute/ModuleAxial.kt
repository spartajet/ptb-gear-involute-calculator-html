package de.ptb.length.involute

import de.ptb.length.math.cos
import de.ptb.length.math.sin
import de.ptb.length.math.tan

/**
 * @description
 * @create 2017-07-22 上午10:52
 * @email spartajet.guo@gmail.com
 */
class ModuleAxial( fixed: Boolean,  lengthAllowedDotBefore: Int,  lengthAllowedDotAfter: Int,  valueLimitMax: Double,  valueLimitMin: Double,  unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit) {
    
    
    fun calculateValue(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, angleHelix: IAngleHelix, angleLead: IAngleLead) {
        var mx = Double.MAX_VALUE
        if (!(moduleTransverse.calculationSucceed && moduleNormal.calculationSucceed && angleHelix.isCalculationSucceed() && angleLead.isCalculationSucceed())) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (moduleNormal.known && angleHelix.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {

            this.calculateCount++
            mx = this.mx01(moduleNormal.result_Value, angleHelix.resultValue())
            this.refreshValue(mx)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (moduleNormal.known && angleLead.isKnown() && this.calculationSucceed && angleLead.resultValue() < 90) {
            this.calculateCount++
            mx = this.mx02(moduleNormal.result_Value, angleLead.resultValue())
            this.refreshValue(mx)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleTransverse.known && angleHelix.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            this.calculateCount++
            mx = this.mx03(moduleTransverse.result_Value, angleHelix.resultValue())
            this.refreshValue(mx)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
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

    fun calculateContradiction(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, angleHelix: IAngleHelix, angleLead: IAngleLead) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && moduleTransverse.fixed && moduleNormal.fixed && angleHelix.isFixed() && angleLead.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && angleLead.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleTransverse.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }

    /**
     * Calculate axial module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed axial module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @return axial module
     */
    private fun mx01(moduleNormal: Double, beta: Double): Double {
        try {
            val moduleAxial = moduleNormal / sin(beta)
            this.calculationSucceed = true
            //logger.info("mx01 calculate success return value: " + moduleAxial)
            return moduleAxial
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mx01 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate axial module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed axial module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param gamma        gamma inputValue
     * *
     * @return axial module
     */

    private fun mx02(moduleNormal: Double, gamma: Double): Double {
        try {
            val moduleAxial = moduleNormal / cos(gamma)
            this.calculationSucceed = true
            //logger.info("mx02 calculate success return value: " + moduleAxial)
            return moduleAxial
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mx02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate axial module  according to equation 3 from ISO 21771:2007
     * If the calculation is succeed axial module is equal the calculation, otherwise is set as Const.NonSensD

     * @param moduleTransverse transverse module inputValue
     * *
     * @param beta             beta inputValue
     * *
     * @return axial module
     */
    private fun mx03(moduleTransverse: Double, beta: Double): Double {
        try {
            val moduleAxial = moduleTransverse / tan(beta)
            this.calculationSucceed = true
            //logger.info("mx03 calculate success return value: " + moduleAxial)
            return moduleAxial
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("mx03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}