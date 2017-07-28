package de.ptb.length.involute

import de.ptb.length.math.Radian2Grad
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-21 下午12:57
 * @email spartajet.guo@gmail.com
 */
class AngleLeadAngle(override var fixed: Boolean, override val unit: String, override var valueLimitMax: Angle, override var valueLimitMin: Angle, override val digitsAfterDotSecond: Int) : ParaAngle(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond), IAngleLead {

    override fun calculateContradiction(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle) {
        if (this.result_Value == ANGLE_MAX_VALUE) {
            this.angle_Contradiction = Angle(Double.MAX_VALUE)
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && angleHelix.isFixed() && moduleAxial.fixed && moduleTransverse.fixed && moduleNormal.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleNormal.fixed && moduleAxial.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleNormal.fixed && angleHelix.isFixed() && moduleTransverse.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else {
            this.angle_Contradiction = Angle()
        }
    }

    override fun calculateValue(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle) {
        var gamma = Double.MAX_VALUE
        if (!(moduleTransverse.calculationSucceed && moduleNormal.calculationSucceed && moduleAxial.calculationSucceed && angleHelix.isCalculationSucceed())) {
            this.calculateCount++
            this.result_Value = Angle(ANGLE_MAX_VALUE)
            this.resultValueMax = Angle(ANGLE_MAX_VALUE)
            this.resultValueMin = Angle(ANGLE_MAX_VALUE)
            this.calculationSucceed = false
            if (!calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
            return
        }
        if (angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            gamma = this.gamma01(angleHelix.resultValue())
            this.refreshValue(Angle(gamma))
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (moduleNormal.known && moduleAxial.known && this.calculationSucceed) {
            this.calculateCount++
            gamma = this.gamma02(moduleNormal.result_Value, moduleAxial.result_Value)
            this.refreshValue(Angle(gamma))
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleNormal.known && moduleAxial.known && angleHelix.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            this.calculateCount++
            gamma = this.gamma03(moduleNormal.result_Value, angleHelix.resultValue(), moduleTransverse.result_Value)
            this.refreshValue(Angle(gamma))
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
    }

    /**
     * Calculate gamma according to equation 4.3.2 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param beta beta  inputValue
     * *
     * @return gamma calculated
     */
    private fun gamma01(beta: Double): Double {
        if (beta < 0 || beta >= 90) {
//            logger.info("gamma01 calculate fail, return double max")
            this.calculationSucceed = false
            return Double.MAX_VALUE
        }
        val gamma = 90 - beta
//        logger.info("gamma01 calculate success return value: " + gamma)
        this.calculationSucceed = true
        return gamma
    }


    /**
     * Calculate gamma according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal normal module inputValue
     * *
     * @param moduleAxial  axis module inputValue
     * *
     * @return gamma calculated
     */
    private fun gamma02(moduleNormal: Double, moduleAxial: Double): Double {
        try {
            val temp = moduleNormal / moduleAxial
            val gamma = Math.acos(temp)
            val degree = Radian2Grad(gamma)
//            logger.info("gamma02 calculate success return value: " + degree)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
//            logger.info("gamma02 calculate fail, return double max")
            this.calculationSucceed = false
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate gamma according to equation 3 from ISO 21771:2007 If the calculation
     * is succeed gamma is equal the calculation, otherwise is set as
     * Const.NonSensD

     * @param moduleNormal     normal module inputValue
     * *
     * @param beta             beta inputValue
     * *
     * @param moduleTransverse transverse module inputValue
     * *
     * @return beta calculated
     */
    private fun gamma03(moduleNormal: Double, beta: Double, moduleTransverse: Double): Double {
        try {
            val temp = moduleNormal * tan(beta) / moduleTransverse
            val gamma = Math.acos(temp)
            val degree = Radian2Grad(gamma)
//            logger.info("gamma03 calculate success return value: " + degree)
            this.calculationSucceed = true
            return degree
        } catch (e: Exception) {
//            logger.info("gamma03 calculate fail, return double max")
            this.calculationSucceed = false
            return Double.MAX_VALUE
        }

    }
}