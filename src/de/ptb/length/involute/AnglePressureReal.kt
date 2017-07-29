package de.ptb.length.involute

/**
 * @description
 * @create 2017-07-21 下午1:37
 * @email spartajet.guo@gmail.com
 */
class AnglePressureReal(fixed: Boolean, lengthAllowedDotBefore: Int, lengthAllowedDotAfter: Int, valueLimitMax: Double, valueLimitMin: Double, unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit), IAnglePressure {
    override fun calculateValue(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic) {
        var alphaT = Double.MAX_VALUE
        if (!(anglePressureNormal.isCalculationSucceed() && angleHelix.isCalculationSucceed() && diameterReference.calculationSucceed && diameterBase.calculationSucceed && teethNumber.calculationSucceed && moduleTransverse.calculationSucceed && moduleBasic.calculationSucceed && moduleNormal.calculationSucceed)) {
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
            if (!this.calculation_08) {
                Para.onceMore = true
                this.calculation_08 = true
            }
            return
        }
        if (anglePressureNormal.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT01(anglePressureNormal.resultValue(), angleHelix.resultValue())
            this.refreshValue(alphaT)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (diameterBase.known && diameterReference.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT02(diameterBase.result_Value, diameterReference.result_Value)
            this.refreshValue(alphaT)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (diameterBase.known && teethNumber.known && moduleTransverse.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT03(diameterBase.result_Value, teethNumber.resultValue, moduleTransverse.result_Value)
            this.refreshValue(alphaT)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (diameterBase.known && angleHelix.isKnown() && teethNumber.known && moduleNormal.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT04(diameterBase.result_Value, angleHelix.resultValue(), teethNumber.resultValue, moduleTransverse.result_Value)
            this.refreshValue(alphaT)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (moduleBasic.known && angleHelix.isKnown() && moduleNormal.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT05(moduleBasic.result_Value, angleHelix.resultValue(), moduleNormal.result_Value)
            this.refreshValue(alphaT)
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
        }
        if (moduleBasic.known && moduleTransverse.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT06(moduleBasic.result_Value, moduleTransverse.result_Value)
            this.refreshValue(alphaT)
            this.first = true
            if (!this.calculation_06) {
                Para.onceMore = true
                this.calculation_06 = true
            }
        }
        if (moduleBasic.known && teethNumber.known && diameterReference.known && this.calculationSucceed) {
            this.calculateCount++
            alphaT = this.alphaT07(moduleBasic.result_Value, teethNumber.resultValue, diameterReference.result_Value)
            this.refreshValue(alphaT)
            this.first = true
            if (!this.calculation_07) {
                Para.onceMore = true
                this.calculation_07 = true
            }
        }
    }

    override fun calculateContradiction(anglePressureNormal: IAngle, angleHelix: IAngle, diameterReference: DiameterReference, diameterBase: DiameterBase, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, moduleBasic: ModuleBasic) {
        if (this.result_Value == Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed() && diameterReference.fixed && diameterBase.fixed && teethNumber.fixed && moduleTransverse.fixed && moduleNormal.fixed && moduleBasic.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && diameterReference.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && teethNumber.fixed && moduleTransverse.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && angleHelix.isFixed() && teethNumber.fixed && moduleTransverse.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterBase.fixed && angleHelix.isFixed() && teethNumber.fixed && moduleTransverse.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && angleHelix.isFixed() && moduleNormal.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && moduleTransverse.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleBasic.fixed && teethNumber.fixed && diameterReference.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
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

    override fun calculateResult(result: Boolean) {
        this.calculationSucceed = result
    }
}