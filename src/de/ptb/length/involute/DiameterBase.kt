package de.ptb.length.involute

import de.ptb.length.math.cos

/**
 * @description
 * @create 2017-07-21 下午2:18
 * @email spartajet.guo@gmail.com
 */
class DiameterBase( fixed: Boolean,  lengthAllowedDotBefore: Int,  lengthAllowedDotAfter: Int,  valueLimitMax: Double,  valueLimitMin: Double,  unit: String) : ParaReal(fixed, lengthAllowedDotBefore, lengthAllowedDotAfter, valueLimitMax, valueLimitMin, unit) {

    /**
     * Calculate value.

     * @param diameterReference the diameter reference
     * *
     * @param anglePressure     the angle pressure real
     * *
     * @param teethNumber       the teeth number
     * *
     * @param moduleTransverse  the module transverse
     * *
     * @param moduleNormal      the module normal
     * *
     * @param angleHelix        the angle helix real
     * *
     * @param moduleBasic       the module basic
     */
    fun calculateValue(diameterReference: DiameterReference, anglePressure: IAnglePressure, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, angleHelix: IAngleHelix, moduleBasic: ModuleBasic) {
        var db = Double.MAX_VALUE
        if (!(diameterReference.calculationSucceed && anglePressure.isCalculationSucceed() && teethNumber.calculationSucceed && moduleTransverse.calculationSucceed && moduleNormal.calculationSucceed && angleHelix.isCalculationSucceed() && moduleBasic.calculationSucceed)) {
            this.calculateCount++
            this.calculationSucceed = false
            this.result_Value = Double.MAX_VALUE
            this.resultValueMax = Double.MAX_VALUE
            this.resultValueMin = -Double.MAX_VALUE
            if (!this.calculation_05) {
                Para.onceMore = true
                calculation_05 = true
            }
            return
        }
        if (diameterReference.known && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            db = this.db01(diameterReference.result_Value, anglePressure.resultValue())
            this.refreshValue(db)
            this.first = true
            if (!this.calculation_01) {
                Para.onceMore = true
                this.calculation_01 = true
            }
        }
        if (teethNumber.known && moduleTransverse.known && anglePressure.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            db = this.db02(teethNumber.resultValue, moduleTransverse.result_Value, anglePressure.resultValue())
            this.refreshValue(db)
            this.first = true
            if (!this.calculation_02) {
                Para.onceMore = true
                this.calculation_02 = true
            }
        }
        if (teethNumber.known && moduleNormal.known && anglePressure.isKnown() && angleHelix.isKnown() && this.calculationSucceed) {
            this.calculateCount++
            db = this.db03(teethNumber.resultValue, moduleNormal.result_Value, anglePressure.resultValue(), angleHelix.resultValue())
            this.refreshValue(db)
            this.first = true
            if (!this.calculation_03) {
                Para.onceMore = true
                this.calculation_03 = true
            }
        }
        if (teethNumber.known && moduleBasic.known && this.calculationSucceed) {
            this.calculateCount++
            db = this.db04(teethNumber.resultValue, moduleBasic.result_Value)
            this.refreshValue(db)
            this.first = true
            if (!this.calculation_04) {
                Para.onceMore = true
                this.calculation_04 = true
            }
        }
    }

    /**
     * Calculate contradiction.

     * @param diameterReference the diameter reference
     * *
     * @param anglePressure     the angle pressure real
     * *
     * @param teethNumber       the teeth number
     * *
     * @param moduleTransverse  the module transverse
     * *
     * @param moduleNormal      the module normal
     * *
     * @param angleHelix        the angle helix real
     * *
     * @param moduleBasic       the module basic
     */
    fun calculateContradiction(diameterReference: DiameterReference, anglePressure: IAnglePressure, teethNumber: TeethNumber, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, angleHelix: IAngleHelix, moduleBasic: ModuleBasic) {
        if (this.result_Value === Double.MAX_VALUE) {
            this.contradiction = Double.MAX_VALUE
            return
        }
        if (this.calculateCount == 0) {
            this.result_Value = this.inputValue
            this.resultValueMax = this.inputValue
            this.resultValueMin = this.inputValue
        }
        if (this.fixed && diameterReference.fixed && anglePressure.isFixed() && teethNumber.fixed && moduleTransverse.fixed && moduleNormal.fixed && angleHelix.isFixed() && moduleBasic.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && diameterReference.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && teethNumber.fixed && moduleTransverse.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && teethNumber.fixed && moduleNormal.fixed && anglePressure.isFixed() && angleHelix.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && moduleTransverse.fixed && teethNumber.fixed && anglePressure.isFixed()) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else if (this.fixed && teethNumber.fixed && moduleBasic.fixed) {
            this.contradiction = this.resultValueMax - this.resultValueMin
        } else {
            this.contradiction = 0.0
        }
    }

    /**
     * Calculate base diameter according to equation 13 from ISO 21771:2007
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param diameterReference reference diameter inputValue
     * *
     * @param alphaT            pressure angle inputValue
     * *
     * @return base diameter
     */
    private fun db01(diameterReference: Double, alphaT: Double): Double {
        try {
            val diameterBase = diameterReference * cos(alphaT)
            this.calculationSucceed = true
//            logger.info("db01 calculate success return value: " + diameterBase)
            return diameterBase
        } catch (e: Exception) {
            this.calculationSucceed = false
//            logger.info("db01 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }

    /**
     * Calculate base diameter according to equation 19 from ISO 21771:2007
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param z                numbers of teeth inputValue
     * *
     * @param moduleTransverse transverse module inputValue
     * *
     * @param alphaT           pressure angle inputValue
     * *
     * @return base diameter
     */
    private fun db02(z: Int, moduleTransverse: Double, alphaT: Double): Double {
        try {
            val diameterBase = z.toDouble() * moduleTransverse * cos(alphaT)
            this.calculationSucceed = true
//            logger.info("db02 calculate success return value: " + diameterBase)
            return diameterBase
        } catch (e: Exception) {
            this.calculationSucceed = false
//            logger.info("db02 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate base diameter according to equation 19 from ISO 21771:2007
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param z            numbers of teeth inputValue
     * *
     * @param moduleNormal normal module inputValue
     * *
     * @param alphaT       pressure angle inputValue
     * *
     * @param beta         beta inputValue
     * *
     * @return base diameter
     */
    private fun db03(z: Int, moduleNormal: Double, alphaT: Double, beta: Double): Double {
        try {
            val diameterBase = z.toDouble() * moduleNormal * (cos(alphaT) / cos(beta))
            this.calculationSucceed = true
//            logger.info("db03 calculate success return value: " + diameterBase)
            return diameterBase
        } catch (e: Exception) {
            this.calculationSucceed = false
//            logger.info("db03 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }


    /**
     * Calculate base diameter according to equation 2.5 from DIN 3960
     * If the calculation is succeed base diameter is equal the calculation, otherwise is set as Const.NonSensD

     * @param z           numbers of teeth inputValue
     * *
     * @param moduleBasic basic module inputValue
     * *
     * @return base diameter
     */
    private fun db04(z: Int, moduleBasic: Double): Double {
        try {
            val diameterBase = z * moduleBasic
            this.calculationSucceed = true
//            logger.info("db04 calculate success return value: " + diameterBase)
            return diameterBase
        } catch (e: Exception) {
            this.calculationSucceed = false
//            logger.info("db04 calculate fail, return double max")
            return Double.MAX_VALUE
        }

    }
}