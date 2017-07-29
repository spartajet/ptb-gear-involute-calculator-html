package de.ptb.length.involute

/**
 * @description
 * @create 2017-07-21 上午11:11
 * @email spartajet.guo@gmail.com
 */
class AngleHelixAngle(fixed: Boolean, unit: String, valueLimitMax: Angle, valueLimitMin: Angle, digitsAfterDotSecond: Int) : ParaAngle(fixed, unit, valueLimitMax, valueLimitMin, digitsAfterDotSecond), IAngleHelix {

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
            this.angle_Contradiction = this.resultValueMax - this.resultValueMin
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

    override fun calculateResult(result: Boolean) {
        this.calculationSucceed = result
    }
}