package de.ptb.length.involute

import de.ptb.length.math.cos
import de.ptb.length.math.tan
import kotlin.js.Math

/**
 * @description
 * @create 2017-07-21 上午10:27
 * @email spartajet.guo@gmail.com
 */
class TeethNumber(fixed: Boolean, unit: String, valueLimitMax: Int, valueLimitMin: Int, digitsLimitMax: Int) : ParaInt(fixed, unit, valueLimitMax, valueLimitMin, digitsLimitMax) {
    override fun calculateValue() = false

    override fun calculateContradiction() = false

    override fun getInputCheck() = this.inputCheck

    /**
     * Calculate inputValue.

     * @param diameterReference       the diameter reference
     * *
     * @param moduleTransverse        the module transverse
     * *
     * @param angleHelix          the angle helix real
     * *
     * @param moduleNormal            the module normal
     * *
     * @param diameterBase            the diameter base
     * *
     * @param anglePressure       the angle pressure real
     * *
     * @param angleLead           the angle lead real
     * *
     * @param moduleBasic             the module basic
     * *
     * @param anglePressureNormal the angle pressure normal real
     */
    fun calculateValue(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, angleHelix: IAngleHelix, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAnglePressure, angleLead: IAngleLead, moduleBasic: ModuleBasic, anglePressureNormal: IAnglePressureNormal) {
        var z = Int.MAX_VALUE
        if (!(diameterReference.calculationSucceed && moduleTransverse.calculationSucceed && angleHelix.isCalculationSucceed() && moduleNormal.calculationSucceed && diameterBase.calculationSucceed && anglePressure.isCalculationSucceed() && angleLead.isCalculationSucceed() && moduleBasic.calculationSucceed && anglePressureNormal.isCalculationSucceed())) {
            this.calculateCount++
            this.resultValue = Int.MAX_VALUE
            this.resultValueMax = Int.MAX_VALUE
            this.resultValueMin = Int.MIN_VALUE
            if (!this.calculation_07) {
                Para.onceMore = true
                this.calculationSucceed = false
            }
            return
        }
        if (diameterReference.known && moduleTransverse.known && this.calculationSucceed) {
            this.calculateCount++
            z = this.z01(diameterReference.result_Value, moduleTransverse.result_Value)
            this.refreshValue(z)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (diameterReference.known && angleHelix.isKnown() && moduleNormal.known && this.calculationSucceed) {
            this.calculateCount++
            z = this.z02(diameterReference.result_Value, angleHelix.resultValue(), moduleNormal.result_Value)
            this.refreshValue(z)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (diameterBase.known && moduleTransverse.known && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            z = this.z03(diameterBase.result_Value, moduleTransverse.result_Value, anglePressure.resultValue())
            this.refreshValue(z)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (diameterBase.known && anglePressureNormal.isKnown() && angleHelix.isKnown() && moduleNormal.known && this.calculationSucceed) {
            this.calculateCount++
            z = this.z04(diameterBase.result_Value, anglePressureNormal.resultValue(), angleHelix.resultValue(), moduleNormal.result_Value)
            this.refreshValue(z)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
        if (diameterBase.known && anglePressure.isKnown() && moduleTransverse.known && this.calculationSucceed) {
            this.calculateCount++
            z = this.z05(diameterBase.result_Value, anglePressure.resultValue(), moduleTransverse.result_Value)
            this.refreshValue(z)
            this.first = true
            if (!this.calculation_05) {
                Para.onceMore = true
                this.calculation_05 = true
            }
        }
        if (diameterBase.known && moduleBasic.known && this.calculationSucceed) {
            this.calculateCount++
            z = z06(diameterBase.result_Value, moduleBasic.result_Value)
            this.refreshValue(z)
            this.first = true
            if (!this.calculation_06) {
                Para.onceMore = true
                this.calculation_06 = true
            }
        }

    }

    /**
     * Calculate contradiction.

     * @param diameterReference       the diameter reference
     * *
     * @param moduleTransverse        the module transverse
     * *
     * @param angleHelix          the angle helix real
     * *
     * @param moduleNormal            the module normal
     * *
     * @param diameterBase            the diameter base
     * *
     * @param anglePressure       the angle pressure real
     * *
     * @param angleLead           the angle lead real
     * *
     * @param moduleBasic             the module basic
     * *
     * @param anglePressureNormal the angle pressure normal real
     */
    fun calculateContradiction(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, angleHelix: IAngleHelix, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAnglePressure, angleLead: IAngleLead, moduleBasic: ModuleBasic, anglePressureNormal: IAnglePressureNormal) {
        if (this.resultValue == Int.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.resultValue = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && diameterReference.fixed && moduleTransverse.fixed && angleHelix.isFixed() && moduleNormal.fixed && diameterBase.fixed && anglePressure.isFixed() && anglePressureNormal.isFixed() && moduleBasic.fixed) {
            this.contradiction = (this.resultValueMax - this.resultValueMin).toDouble()
        } else if (this.fixed && diameterReference.fixed && moduleTransverse.fixed) {
            this.contradiction = (this.resultValueMax - this.resultValueMin).toDouble()
        } else if (this.fixed && diameterReference.fixed && angleHelix.isFixed() && moduleNormal.fixed) {
            this.contradiction = (this.resultValueMax - this.resultValueMin).toDouble()
        } else if (this.fixed && diameterBase.fixed && moduleTransverse.fixed && anglePressure.isFixed()) {
            this.contradiction = (this.resultValueMax - this.resultValueMin).toDouble()
        } else if (this.fixed && diameterBase.fixed && anglePressureNormal.isFixed() && angleHelix.isFixed() && moduleNormal.fixed) {
            this.contradiction = (this.resultValueMax - this.resultValueMin).toDouble()
        } else if (this.fixed && diameterBase.fixed && anglePressure.isFixed() && moduleTransverse.fixed) {
            this.contradiction = (this.resultValueMax - this.resultValueMin).toDouble()
        } else if (this.fixed && diameterBase.fixed && moduleBasic.fixed) {
            this.contradiction = (this.resultValueMax - this.resultValueMin).toDouble()
        } else {
            this.contradiction = 0.0
        }

    }


    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI

     * @param diameterReference reference diameter inputValue
     * *
     * @param moduleTransverse  transverse module inputValue
     * *
     * *
     * @return numbers of teeth
     */
    private fun z01(diameterReference: Double, moduleTransverse: Double): Int {
        try {
            val teethNumber = Math.round(diameterReference / moduleTransverse)
            this.calculationSucceed = true
            return teethNumber
        } catch (e: Exception) {
            this.calculationSucceed = false
            return Int.MAX_VALUE
        }

    }

    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI

     * @param diameterReference reference diameter inputValue
     * *
     * @param beta              beta inputValue
     * *
     * @param moduleNormal      normal module inputValue
     * *
     * *
     * @return numbers of teeth
     */
    private fun z02(diameterReference: Double, beta: Double, moduleNormal: Double): Int {
        try {
            val teethNumber = Math.round(diameterReference * cos(beta) / moduleNormal)
            this.calculationSucceed = true
            return teethNumber
        } catch (e: Exception) {
            this.calculationSucceed = false
            return Int.MAX_VALUE
        }

    }

    /**
     * Calculate numbers of teeth according to equation 19 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI

     * @param diameterBase     reference diameter inputValue
     * *
     * @param moduleTransverse normal transverse inputValue
     * *
     * @param alphaT           pressure angle inputValue
     * *
     * *
     * @return numbers of teeth
     */
    private fun z03(diameterBase: Double, moduleTransverse: Double, alphaT: Double): Int {
        try {
            val teethNumber = Math.round(diameterBase / (moduleTransverse * cos(alphaT)))
            this.calculationSucceed = true
            return teethNumber
        } catch (e: Exception) {
            this.calculationSucceed = false
            return Int.MAX_VALUE
        }

    }

    /**
     * Calculate numbers of teeth according to equation 19 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI

     * @param diameterBase reference diameter inputValue
     * *
     * @param alphaN       pressure angle inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @param moduleNormal module normal inputValue
     * *
     * *
     * @return numbers of teeth
     */
    private fun z04(diameterBase: Double, alphaN: Double, beta: Double, moduleNormal: Double): Int {
        try {
            val teethNumber = Math.round(diameterBase / moduleNormal * Math.sqrt(Math.pow(tan(alphaN), 2.0) + Math.pow(cos(beta), 2.0))).toInt()
            this.calculationSucceed = true
            return teethNumber
        } catch (e: Exception) {
            this.calculationSucceed = false
            return Int.MAX_VALUE
        }

    }

    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI

     * @param diameterBase     reference diameter inputValue
     * *
     * @param alphaT           pressure angle inputValue
     * *
     * @param moduleTransverse transverse module inputValue
     * *
     * *
     * @return numbers of teeth
     */
    private fun z05(diameterBase: Double, alphaT: Double, moduleTransverse: Double): Int {
        try {
            val teethNumber = Math.round(diameterBase / moduleTransverse * Math.sqrt(Math.pow(tan(alphaT), 2.0) + 1))
            this.calculationSucceed = true
            return teethNumber
        } catch (e: Exception) {
            this.calculationSucceed = false
            return Int.MAX_VALUE
        }

    }

    /**
     * Calculate numbers of teeth according to equation 1 from ISO 21771:2007
     * If the calculation is succeed numbers of teeth is equal the calculation, otherwise is set as Const.NonSensI

     * @param diameterBase reference diameter inputValue
     * *
     * @param moduleBasic  basic module inputValue
     * *
     * *
     * @return numbers of teeth
     */
    private fun z06(diameterBase: Double, moduleBasic: Double): Int {
        try {
            val teethNumber = Math.round(diameterBase / moduleBasic)
            this.calculationSucceed = true
            return teethNumber
        } catch (e: Exception) {
            this.calculationSucceed = false
            return Int.MAX_VALUE
        }

    }

}