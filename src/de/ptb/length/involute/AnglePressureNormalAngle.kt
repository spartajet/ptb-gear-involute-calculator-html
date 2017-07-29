package de.ptb.length.involute

/**
 * @description
 * @create 2017-07-21 下午1:57
 * @email spartajet.guo@gmail.com
 */
class AnglePressureNormalAngle(fixed: Boolean, unit: String, valueLimitMax: Angle, valueLimitMin: Angle, digitsAfterDotSecond: Int) : ParaAngle(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond), IAnglePressureNormal {
    override fun calculateValue(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse) {
        var alphaN = Double.MAX_VALUE
        if (!(angleHelix.isCalculationSucceed() && anglePressure.isCalculationSucceed() && teethNumber.calculationSucceed && moduleNormal.calculationSucceed && diameterBase.calculationSucceed && moduleBasic.calculationSucceed && moduleTransverse.calculationSucceed)) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Angle(ANGLE_MAX_VALUE)
            this.resultValueMax = Angle(ANGLE_MAX_VALUE)
            this.resultValueMin = Angle(ANGLE_MAX_VALUE)
            if (!calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
            return
        }
        if (angleHelix.isKnown() && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN01(angleHelix.resultValue(), anglePressure.resultValue())
            this.refreshValue(Angle(alphaN))
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (teethNumber.known && moduleNormal.known && diameterBase.known && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN02(teethNumber.resultValue, moduleNormal.result_Value, diameterBase.result_Value, angleHelix.resultValue())
            this.refreshValue(Angle(alphaN))
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleNormal.known && moduleBasic.known && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN03(moduleNormal.result_Value, moduleBasic.result_Value, angleHelix.resultValue())
            this.refreshValue(Angle(alphaN))
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (moduleNormal.known && moduleTransverse.known && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaN = alphaN04(moduleNormal.result_Value, moduleTransverse.result_Value, anglePressure.resultValue())
            this.refreshValue(Angle(alphaN))
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
    }

    override fun calculateContradiction(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse) {
        if (this.result_Value == ANGLE_MAX_VALUE) {
            this.angle_Contradiction = Angle(ANGLE_MAX_VALUE)
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && angleHelix.isFixed() && anglePressure.isFixed() && teethNumber.fixed && moduleNormal.fixed && diameterBase.fixed && moduleBasic.fixed && moduleTransverse.fixed) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && angleHelix.isFixed() && anglePressure.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (teethNumber.fixed && moduleNormal.fixed && diameterBase.fixed && angleHelix.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (moduleNormal.fixed && moduleBasic.fixed && angleHelix.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else if (this.fixed && moduleNormal.fixed && moduleTransverse.fixed && anglePressure.isFixed()) {
            this.angle_Contradiction = this.resultValueMax.minus(this.resultValueMin)
        } else {
            this.angle_Contradiction = Angle()
        }
    }

    override fun calculateResult(result: Boolean) {
        this.calculationSucceed = result
    }
}