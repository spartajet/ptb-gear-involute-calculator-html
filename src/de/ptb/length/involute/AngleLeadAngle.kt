package de.ptb.length.involute

/**
 * @description
 * @create 2017-07-21 下午12:57
 * @email spartajet.guo@gmail.com
 */
class AngleLeadAngle(fixed: Boolean, unit: String, valueLimitMax: Angle, valueLimitMin: Angle, digitsAfterDotSecond: Int) : ParaAngle(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond), IAngleLead {

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

    override fun calculateResult(result: Boolean) {
        this.calculationSucceed = result
    }
}