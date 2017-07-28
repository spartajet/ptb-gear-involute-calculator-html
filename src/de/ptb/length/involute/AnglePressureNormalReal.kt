package de.ptb.length.involute

import de.ptb.length.math.Radian2Grad
import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-21 下午2:05
 * @email spartajet.guo@gmail.com
 */
class AnglePressureNormalReal(override var fixed: Boolean, override val lengthAllowedDotBefore: Int, override val lengthAllowedDotAfter: Int, override val valueLimitMax: Double, override val valueLimitMin: Double, override val unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit), IAnglePressureNormal {
    override fun calculateValue(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse) {
        var alphaN = Double.MAX_VALUE
        if (!(angleHelix.isCalculationSucceed() && anglePressure.isCalculationSucceed() && teethNumber.calculationSucceed && moduleNormal.calculationSucceed && diameterBase.calculationSucceed && moduleBasic.calculationSucceed && moduleTransverse.calculationSucceed)) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = -Double.MAX_VALUE
            this.resultValueMin = Double.MAX_VALUE
            if (!calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
            return
        }
        if (angleHelix.isKnown() && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN01(angleHelix.resultValue(), anglePressure.resultValue())
            this.refreshValue(alphaN)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (teethNumber.known && moduleNormal.known && diameterBase.known && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN02(teethNumber.resultValue, moduleNormal.result_Value, diameterBase.result_Value, angleHelix.resultValue())
            this.refreshValue(alphaN)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleNormal.known && moduleBasic.known && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN03(moduleNormal.result_Value, moduleBasic.result_Value, angleHelix.resultValue())
            this.refreshValue(alphaN)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (moduleNormal.known && moduleTransverse.known && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN04(moduleNormal.result_Value, moduleTransverse.result_Value, anglePressure.resultValue())
            this.refreshValue(alphaN)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
    }

    override fun calculateContradiction(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && angleHelix.isFixed() && anglePressure.isFixed() && teethNumber.fixed && moduleNormal.fixed && diameterBase.fixed && moduleBasic.fixed && moduleTransverse.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && angleHelix.isFixed() && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (teethNumber.fixed && moduleNormal.fixed && diameterBase.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (moduleNormal.fixed && moduleBasic.fixed && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && moduleTransverse.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }


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
    private fun alphaN01(beta: Double, alphaT: Double): Double {
        try {
            val alphaN = Math.atan(cos(beta) * tan(alphaT))
            val degree = Radian2Grad(alphaN)
            //logger.info("alphaN01 calculate success return value: " + degree)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
            //logger.info("alphaN01 calculate fail, return double max")
            this.calculationSucceed = false
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
    private fun alphaN02(z: Int, moduleNormal: Double, diameterBase: Double, beta: Double): Double {
        try {
            val alphaN = Math.atan(Math.sqrt(Math.pow(z * moduleNormal / diameterBase, 2.0) - Math.pow(cos(beta), 2.0)))
            val degree = Radian2Grad(alphaN)
            this.calculationSucceed = true
            //logger.info("alphaN02 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaN03(moduleNormal: Double, moduleBasic: Double, beta: Double): Double {
        try {
            val alphaN = Math.atan(Math.sqrt(Math.pow(moduleNormal, 2.0) / Math.pow(moduleBasic, 2.0) - Math.pow(cos(beta), 2.0)))
            val degree = Radian2Grad(alphaN)
            this.calculationSucceed = true
            //logger.info("alphaN03 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun alphaN04(moduleNormal: Double, moduleTransverse: Double, alfaT: Double): Double {
        try {
            val alphaN = Math.atan(moduleNormal / moduleTransverse * tan(alfaT))
            val degree = Radian2Grad(alphaN)
            this.calculationSucceed = true
            ////logger.info("alphaN04 calculate success return value: " + degree)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
            //logger.info("alphaN04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    override fun setFixed(value: Boolean) {
        this.fixed = value
    }

    override fun isCalculationSucceed() = this.calculationSucceed

    override fun isKnown() = this.known

    override fun resultValue() = this.result_Value
    override fun isFixed() = this.fixed

    override fun getRoundValueString() = this.round_Value.toString()

    override fun getRoundContradictionString() = this.round_Contradiction.toString()

    override fun getValueLimitMin() = this.valueLimitMin
    override fun getValueLimitMax() = this.valueLimitMax

    override fun setInputValue(value: Angle) {
        this.inputValue = value.toReal()
    }

    override fun setValueString(valueString: String) {
        this.valueString = valueString
    }

    override fun getInputValueString() = this.inputValue.toString()
}