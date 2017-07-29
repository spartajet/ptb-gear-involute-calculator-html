package de.ptb.length.involute

/**
 * @description
 * @create 2017-07-21 下午1:05
 * @email spartajet.guo@gmail.com
 */
class AngleLeadReal( fixed: Boolean,  lengthAllowedDotBefore: Int,  lengthAllowedDotAfter: Int,  valueLimitMax: Double,  valueLimitMin: Double,  unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit), IAngleLead {


    override fun calculateValue(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle) {
        var gamma = Double.MAX_VALUE
        if (!(moduleTransverse.calculationSucceed && moduleNormal.calculationSucceed && moduleAxial.calculationSucceed && angleHelix.isCalculationSucceed())) {
            this.calculateCount++
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
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
            this.refreshValue(gamma)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (moduleNormal.known && moduleAxial.known && this.calculationSucceed) {
            this.calculateCount++
            gamma = this.gamma02(moduleNormal.result_Value, moduleAxial.result_Value)
            this.refreshValue(gamma)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (moduleNormal.known && moduleAxial.known && angleHelix.isKnown() && this.calculationSucceed && angleHelix.resultValue() > 0) {
            this.calculateCount++
            gamma = this.gamma03(moduleNormal.result_Value, angleHelix.resultValue(), moduleTransverse.result_Value)
            this.refreshValue(gamma)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
    }

    override fun calculateContradiction(moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleAxial: ModuleAxial, angleHelix: IAngle) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && angleHelix.isFixed() && moduleAxial.fixed && moduleTransverse.fixed && moduleNormal.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && moduleAxial.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleNormal.fixed && angleHelix.isFixed() && moduleTransverse.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }


    override fun setFixed(value: Boolean) {
        this.fixed = value
    }

    override fun isCalculationSucceed(): Boolean = this.calculationSucceed

    override fun isKnown(): Boolean = this.known

    override fun resultValue(): Double = this.result_Value

    override fun isFixed(): Boolean = this.fixed

    override fun getRoundValueString(): String = this.round_Value.toString()
    override fun getRoundContradictionString(): String = this.round_Contradiction.toString()

    override fun getValueLimitMin(): Double = this.getValueLimitMin()

    override fun getValueLimitMax(): Double = this.getValueLimitMax()

    override fun setInputValue(value: Angle) {
        this.inputValue = value.toReal()
    }

    override fun setValueString(valueString: String) {
        this.valueString = valueString
    }

    override fun getInputValueString() = this.inputValue.toString()
    override fun calculateResult(result: Boolean) {
        this.calculationSucceed=result
    }
}