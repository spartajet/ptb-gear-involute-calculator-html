package de.ptb.length.involute

import de.ptb.length.math.Radian2Grad
import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-21 上午11:11
 * @email spartajet.guo@gmail.com
 */
class AngleHelixAngle(override var fixed: Boolean, override val unit: String, override var valueLimitMax: Angle, override var valueLimitMin: Angle, override val digitsAfterDotSecond: Int) : ParaAngle(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond), IAngleHelix {

    override fun calculateValue() = false

    override fun calculateContradiction(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAngle, angleLead: IAngle, moduleBasic: ModuleBasic, anglePressureNormal: IAngle, teethNumber: TeethNumber, moduleAxial: ModuleAxial) {
        if (this.result_Value == ANGLE_MAX_VALUE) {
            this.angle_Contradiction = Angle(ANGLE_MAX_VALUE)
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && moduleTransverse.fixed && moduleNormal.fixed && teethNumber.fixed && angleLead.isFixed() && anglePressure.isFixed() && diameterBase.fixed && anglePressureNormal.isFixed() && moduleBasic.fixed && moduleAxial.fixed) {
            this.angle_Contradiction = this.resultValueMax-this.resultValueMin
        } else if (this.fixed && moduleTransverse.fixed && moduleNormal.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && teethNumber.fixed && moduleNormal.fixed && diameterReference.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && angleLead.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleTransverse.fixed && moduleNormal.fixed && angleLead.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && anglePressureNormal.isFixed() && anglePressure.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleNormal.fixed && moduleBasic.fixed && anglePressureNormal.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleNormal.fixed && moduleBasic.fixed && anglePressure.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else {
            this.angle_Contradiction = Angle()
        }

    }

    override fun calculateContradiction() = false


    override fun calculateValue(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAngle, angleLead: IAngle, moduleBasic: ModuleBasic, anglePressureNormal: IAngle, teethNumber: TeethNumber) {
        var beta = Double.MAX_VALUE
        if (!(diameterReference.calculationSucceed && moduleTransverse.calculationSucceed && moduleNormal.calculationSucceed && diameterBase.calculationSucceed && anglePressure.isCalculationSucceed() && angleLead.isCalculationSucceed() && moduleBasic.calculationSucceed && anglePressureNormal.isCalculationSucceed() && teethNumber.calculationSucceed)) {
            this.calculateCount++
            this.result_Value = Angle(ANGLE_MAX_VALUE)
            this.resultValueMax = Angle(ANGLE_MAX_VALUE)
            this.resultValueMin = Angle(ANGLE_MAX_VALUE)
            this.calculationSucceed = false
            if (this.calculation_08) {
                Para.onceMore = true
                this.calculation_08 = true
            }
            return
        }
        if (moduleNormal.known && moduleTransverse.known && this.calculationSucceed) {
            this.calculateCount++
            beta = this.beta01(moduleTransverse.result_Value, moduleNormal.result_Value)
            this.refreshValue(Angle(beta))
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (teethNumber.known && moduleNormal.known && diameterReference.known && this.calculationSucceed) {
            this.calculateCount++
            beta = beta02(teethNumber.resultValue, moduleNormal.result_Value, diameterReference.result_Value)
            this.refreshValue(Angle(beta))
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (angleLead.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            beta = beta03(angleLead.resultValue())
            this.refreshValue(Angle(beta))
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (moduleTransverse.known && moduleNormal.known && angleLead.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            beta = beta04(moduleTransverse.result_Value, moduleNormal.result_Value, angleLead.resultValue())
            this.refreshValue(Angle(beta))
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (anglePressureNormal.isKnown() && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            beta = beta05(anglePressureNormal.resultValue(), anglePressure.resultValue())
            this.refreshValue(Angle(beta))
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
        }
        if (moduleNormal.known && moduleBasic.known && anglePressureNormal.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            beta = beta06(moduleNormal.result_Value, moduleBasic.result_Value, anglePressureNormal.resultValue())
            this.refreshValue(Angle(beta))
            this.first = true
            if (!this.calculation_06) {
                Para.onceMore = true
                this.calculation_06 = true
            }
        }
        if (moduleNormal.known && moduleBasic.known && anglePressure.isKnown()) {
            this.calculateCount++
            beta = beta07(moduleNormal.result_Value, moduleBasic.result_Value, anglePressure.resultValue())
            this.refreshValue(Angle(beta))
            this.first = true
            if (!this.calculation_07) {
                Para.onceMore = true
                this.calculation_07 = true
            }
        }
    }


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
    private fun beta01(moduleTransverse: Double, moduleNormal: Double): Double {

        var beta = Double.MAX_VALUE
        try {
            beta = Math.acos(moduleNormal / moduleTransverse)
            this.calculationSucceed = true
            val degree = Radian2Grad(beta)
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun beta02(z: Int, moduleNormal: Double, diameterReference: Double): Double {

        var beta = Double.MAX_VALUE
        try {
            beta = Math.acos(z * moduleNormal / diameterReference)
            val degree = Radian2Grad(beta)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun beta03(gamma: Double): Double {
        if (gamma <= 0 || gamma > 90) {
            this.calculationSucceed = false
            return Double.MAX_VALUE
        }
        this.calculationSucceed = true
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
    private fun beta04(moduleTransverse: Double, moduleNormal: Double, gamma: Double): Double {

        var beta = Double.MAX_VALUE
        try {
            beta = Math.atan(cos(gamma) * moduleTransverse / moduleNormal)
            val degree = Radian2Grad(beta)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun beta05(alphaN: Double, alphaT: Double): Double {
        var beta = Double.MAX_VALUE
        try {
            beta = Math.acos(tan(alphaN) / tan(alphaT))
            val degree = Radian2Grad(beta)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun beta06(moduleNormal: Double, moduleBasic: Double, alphaN: Double): Double {
        val beta: Double
        try {
            val temp = Math.sqrt(Math.pow(moduleNormal, 2.0) / Math.pow(moduleBasic, 2.0) - Math.pow(tan(alphaN), 2.0))
            beta = Math.acos(temp)
            val degree = Radian2Grad(beta)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
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
    private fun beta07(moduleNormal: Double, moduleBasic: Double, alphaT: Double): Double {
        try {
            val temp = moduleNormal * cos(alphaT) / moduleBasic
            val beta = Math.acos(temp)
            val degree = Radian2Grad(beta)
//            logger.info("beta07 calculate success return value: " + degree)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
            this.calculationSucceed = false
//            logger.info("beta07 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}