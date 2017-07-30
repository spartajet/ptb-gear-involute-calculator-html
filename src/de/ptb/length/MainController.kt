package de.ptb.length

import de.ptb.length.involute.*
import de.ptb.length.listener.addAngleParaListener
import de.ptb.length.listener.addIntParaListener
import de.ptb.length.listener.addRealParaListener
import org.w3c.dom.*
import kotlin.browser.document
import kotlin.browser.window

/**
 * @description
 * @create 2017-07-19 下午4:04
 * @email spartajet.guo@gmail.com
 */
fun main(args: Array<String>) {
    //prevent contradiction input get focus
    val contradictionArray: HTMLCollection = document.getElementsByClassName("para-contradiction-field")
    for (i: Int in 0..contradictionArray.length - 1) {
        val contradictionField = contradictionArray[i] as HTMLInputElement
        contradictionField.onfocus = {
            contradictionField.blur()
        }
    }
    // get element
    val teethNumberValueInput: HTMLInputElement = document.getElementById("teethNumberValueInput") as HTMLInputElement
    val teethNumberFixBox: HTMLInputElement = document.getElementById("teethNumberFixBox") as HTMLInputElement
    val teethNumberContradictionInput: HTMLInputElement = document.getElementById("teethNumberContradictionInput") as HTMLInputElement
    val normalModuleValueInput: HTMLInputElement = document.getElementById("normalModuleValueInput") as HTMLInputElement
    val normalModuleFixBox: HTMLInputElement = document.getElementById("normalModuleFixBox") as HTMLInputElement
    val normalModuleContradictionInput: HTMLInputElement = document.getElementById("normalModuleContradictionInput") as HTMLInputElement
    val transverseModuleValueInput: HTMLInputElement = document.getElementById("transverseModuleValueInput") as HTMLInputElement
    val transverseModuleFixBox: HTMLInputElement = document.getElementById("transverseModuleFixBox") as HTMLInputElement
    val transverseModuleContradictionInput: HTMLInputElement = document.getElementById("transverseModuleContradictionInput") as HTMLInputElement
    val axialModuleValueInput: HTMLInputElement = document.getElementById("axialModuleValueInput") as HTMLInputElement
    val axialModuleFixBox: HTMLInputElement = document.getElementById("axialModuleFixBox") as HTMLInputElement
    val axialModuleContradictionInput: HTMLInputElement = document.getElementById("axialModuleContradictionInput") as HTMLInputElement
    val baseModuleValueInput: HTMLInputElement = document.getElementById("baseModuleValueInput") as HTMLInputElement
    val baseModuleFixBox: HTMLInputElement = document.getElementById("baseModuleFixBox") as HTMLInputElement
    val baseModuleContradictionInput: HTMLInputElement = document.getElementById("baseModuleContradictionInput") as HTMLInputElement
    val normalPressureAngleValueInput: HTMLInputElement = document.getElementById("normalPressureAngleValueInput") as HTMLInputElement
    val normalPressureAngleSwitchButton: HTMLButtonElement = document.getElementById("normalPressureAngleSwitchButton") as HTMLButtonElement
    val normalPressureAngleContradictionInput: HTMLInputElement = document.getElementById("normalPressureAngleContradictionInput") as HTMLInputElement
    val normalPressureAngleFixBox: HTMLInputElement = document.getElementById("normalPressureAngleFixBox") as HTMLInputElement
    val pressureAngleValueInput: HTMLInputElement = document.getElementById("pressureAngleValueInput") as HTMLInputElement
    val pressureAngleSwitchButton: HTMLButtonElement = document.getElementById("pressureAngleSwitchButton") as HTMLButtonElement
    val pressureAngleFixBox: HTMLInputElement = document.getElementById("pressureAngleFixBox") as HTMLInputElement
    val pressureAngleContradictionInput: HTMLInputElement = document.getElementById("pressureAngleContradictionInput") as HTMLInputElement
    val helixAngleValueInput: HTMLInputElement = document.getElementById("helixAngleValueInput") as HTMLInputElement
    val helixAngleSwitchButton: HTMLButtonElement = document.getElementById("helixAngleSwitchButton") as HTMLButtonElement
    val helixAngleFixBox: HTMLInputElement = document.getElementById("helixAngleFixBox") as HTMLInputElement
    val helixAngleContradictionInput: HTMLInputElement = document.getElementById("helixAngleContradictionInput") as HTMLInputElement
    val leadAngleValueInput: HTMLInputElement = document.getElementById("leadAngleValueInput") as HTMLInputElement
    val leadAngleSwitchButton: HTMLButtonElement = document.getElementById("leadAngleSwitchButton") as HTMLButtonElement
    val leadAngleFixBox: HTMLInputElement = document.getElementById("leadAngleFixBox") as HTMLInputElement
    val leadAngleContradictionInput: HTMLInputElement = document.getElementById("leadAngleContradictionInput") as HTMLInputElement
    val referenceDiameterValueInput: HTMLInputElement = document.getElementById("referenceDiameterValueInput") as HTMLInputElement
    val referenceDiameterFixBox: HTMLInputElement = document.getElementById("referenceDiameterFixBox") as HTMLInputElement
    val referenceDiameterContradictionInput: HTMLInputElement = document.getElementById("referenceDiameterContradictionInput") as HTMLInputElement
    val baseDiameterValueInput: HTMLInputElement = document.getElementById("baseDiameterValueInput") as HTMLInputElement
    val baseDiameterFixBox: HTMLInputElement = document.getElementById("baseDiameterFixBox") as HTMLInputElement
    val baseDiameterContradictionInput: HTMLInputElement = document.getElementById("baseDiameterContradictionInput") as HTMLInputElement
    val InformationArea: HTMLDivElement = document.getElementById("informationArea") as HTMLDivElement
    val clearBtn: HTMLButtonElement = document.getElementById("clearBtn") as HTMLButtonElement
    val calculateBtn: HTMLButtonElement = document.getElementById("calculateBtn") as HTMLButtonElement
    val quitBtn: HTMLButtonElement = document.getElementById("quitBtn") as HTMLButtonElement


    // initial module
    val teethNumber: TeethNumber = TeethNumber(false, "", 1000, 1, 4)
    val moduleNormal = ModuleNormal(false, 2, 4, 100.0, 0.001, "")
    val moduleTransverse = ModuleTransverse(false, 2, 4, 100.0, 0.001, "")
    val moduleAxial = ModuleAxial(false, 4, 4, 1000.0, 0.001, "")
    val moduleBase = ModuleBasic(false, 2, 4, 100.0, 0.001, "")
    val anglePressureNormalReal: AnglePressureNormalReal = AnglePressureNormalReal(false, 2, 4, 45.0, 0.001, "")
    val anglePressureNormalAngle = AnglePressureNormalAngle(false, "", Angle(45.0), Angle(0.0), 3)
    val anglePressureNormal = anglePressureNormalReal
    val anglePressureReal: AnglePressureReal = AnglePressureReal(false, 2, 4, 45.0, 0.001, "")
    val anglePressureAngle = AnglePressureAngle(false, "", Angle(45.0), Angle(0.0), 3)
    val anglePressure = anglePressureReal
    val angleHelixReal: AngleHelixReal = AngleHelixReal(false, 2, 4, 91.0, 0.0, "")
    val angleHelixAngle = AngleHelixAngle(false, "", Angle(91.0), Angle(0.0), 3)
    val angleHelix = angleHelixReal
    val angleLeadReal = AngleLeadReal(false, 2, 4, 91.0, 0.0, "")
    val angleLeadAngle = AngleLeadAngle(false, "", Angle(91.0), Angle(0.0), 3)
    val angleLead = angleLeadReal
    val diameterReference = DiameterReference(false, 4, 5, 10000.0, 0.001, "")
    val diameterBase = DiameterBase(false, 5, 4, 10000.0, 0.001, "")

    //binding input listener
    teethNumberValueInput.addIntParaListener(teethNumber, InformationArea)
    normalModuleValueInput.addRealParaListener(moduleNormal, InformationArea)
    transverseModuleValueInput.addRealParaListener(moduleTransverse, InformationArea)
    axialModuleValueInput.addRealParaListener(moduleAxial, InformationArea)
    baseModuleValueInput.addRealParaListener(moduleBase, InformationArea)
    normalPressureAngleValueInput.addAngleParaListener(anglePressureNormal, InformationArea)
    pressureAngleValueInput.addAngleParaListener(anglePressure, InformationArea)
    helixAngleValueInput.addAngleParaListener(angleHelix, InformationArea)
    leadAngleValueInput.addAngleParaListener(angleLead, InformationArea)
    referenceDiameterValueInput.addRealParaListener(diameterReference, InformationArea)
    baseDiameterValueInput.addRealParaListener(diameterBase, InformationArea)

    // switch button

    //fix button
    teethNumberFixBox.onclick = {
        teethNumber.fixed = teethNumberFixBox.checked
        println("teeth number fixed: ${teethNumber.fixed}")
    }
    normalModuleFixBox.onclick = {
        moduleNormal.fixed = normalModuleFixBox.checked
        println("normal module fixed: ${normalModuleFixBox.checked}")
    }
    transverseModuleFixBox.onclick = {
        moduleTransverse.fixed = transverseModuleFixBox.checked
        println("transverse module fixed: ${transverseModuleFixBox.checked}")
    }
    axialModuleFixBox.onclick = {
        moduleAxial.fixed = axialModuleFixBox.checked
        println("axial module fixed: ${axialModuleFixBox.checked}")
    }
    baseModuleFixBox.onclick = {
        moduleBase.fixed = baseModuleFixBox.checked
        println("base module fixed: ${baseModuleFixBox.checked}")
    }
    normalPressureAngleFixBox.onclick = {
        anglePressureNormal.setFixed(normalPressureAngleFixBox.checked)
        println("normal pressure angle fixed : ${normalPressureAngleFixBox.checked}")
    }
    pressureAngleFixBox.onclick = {
        anglePressure.setFixed(pressureAngleFixBox.checked)
        println("pressure angle fixed : ${pressureAngleFixBox.checked}")
    }
    helixAngleFixBox.onclick = {
        angleHelix.setFixed(helixAngleFixBox.checked)
        println("helix angle fixed: ${helixAngleFixBox.checked}")
    }
    leadAngleFixBox.onclick = {
        angleLead.setFixed(leadAngleFixBox.checked)
        println("lead angle fixed: ${leadAngleFixBox.checked}")
    }
    referenceDiameterFixBox.onclick = {
        diameterReference.fixed = referenceDiameterFixBox.checked
        println("reference diameter fixed: ${referenceDiameterFixBox.checked}")
    }
    baseDiameterFixBox.onclick = {
        diameterBase.fixed = baseDiameterFixBox.checked
        println("base diameter fixed: ${baseDiameterFixBox.checked}")
    }
    // fixed button

    // control buttons
    clearBtn.onclick = {
        teethNumber.clear()
        moduleNormal.clear()
        moduleTransverse.clear()
        moduleAxial.clear()
        moduleBase.clear()
        anglePressureNormal.clear()
        anglePressure.clear()
        angleHelix.clear()
        angleLead.clear()
        diameterReference.clear()
        diameterBase.clear()
        teethNumberValueInput.value = ""
        normalModuleValueInput.value = ""
        transverseModuleValueInput.value = ""
        axialModuleValueInput.value = ""
        baseModuleValueInput.value = ""
        normalPressureAngleValueInput.value = ""
        pressureAngleValueInput.value = ""
        helixAngleValueInput.value = ""
        leadAngleValueInput.value = ""
        referenceDiameterValueInput.value = ""
        baseDiameterValueInput.value = ""
        teethNumberFixBox.checked = false
        normalModuleFixBox.checked = false
        transverseModuleFixBox.checked = false
        axialModuleFixBox.checked = false
        baseModuleFixBox.checked = false
        normalPressureAngleFixBox.checked = false
        pressureAngleFixBox.checked = false
        helixAngleFixBox.checked = false
        leadAngleFixBox.checked = false
        referenceDiameterFixBox.checked = false
        baseDiameterFixBox.checked = false
        teethNumberContradictionInput.value = ""
        normalModuleContradictionInput.value = ""
        transverseModuleContradictionInput.value = ""
        axialModuleContradictionInput.value = ""
        baseModuleContradictionInput.value = ""
        normalPressureAngleContradictionInput.value = ""
        pressureAngleContradictionInput.value = ""
        helixAngleContradictionInput.value = ""
        leadAngleContradictionInput.value = ""
        referenceDiameterContradictionInput.value = ""
        baseDiameterContradictionInput.value = ""
        println("calculator clear!")
    }
    quitBtn.onclick = {
        window.close()
    }
    calculateBtn.onclick = {
        teethNumber.refresh()
        moduleNormal.refresh()
        moduleTransverse.refresh()
        moduleAxial.refresh()
        moduleBase.refresh()
        anglePressureNormal.refresh()
        anglePressure.refresh()
        angleHelix.refresh()
        angleLead.refresh()
        diameterReference.refresh()
        diameterBase.refresh()
        var calculateCount: Int = 0
        var deadloop = false
        loop@ for (i in 0..1) {
            Para.onceMore = true
            while (Para.onceMore) {
                calculateCount++
                if (calculateCount >= 100) {
                    InformationArea.textContent = "more loop than 100 times"
                    deadloop = true
                    break@loop
                }
                Para.onceMore = false
                moduleNormal.calculateValue(moduleTransverse, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial)
                //////logger.info(" module normal calculated, parameters values are: " + moduleNormal.toString())

                moduleTransverse.calculateValue(moduleNormal, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial)
                //////logger.info("module transverse calculated, parameters values are: " + moduleTransverse.toString())

                angleHelix.calculateValue(diameterReference, moduleTransverse, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal, teethNumber)
                //////logger.info("angle helix calculated, parameters values are: " + angleHelix.toString())

                diameterReference.calculateValue(moduleTransverse, angleHelix, moduleNormal, teethNumber, diameterBase, anglePressure, anglePressureNormal, moduleBase)
                //////logger.info("diameter reference calculated, parameters values are: " + diameterReference.toString())

                teethNumber.calculateValue(diameterReference, moduleTransverse, angleHelix, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal)
                //////logger.info("teeth number calculated, parameters values are: " + teethNumber.toString())

                angleLead.calculateValue(moduleTransverse, moduleNormal, moduleAxial, angleHelix)
                //////logger.info("angle lead calculated, parameters values are: " + angleLead.toString())

                moduleAxial.calculateValue(moduleTransverse, moduleNormal, angleHelix, angleLead)
                //////logger.info("module axial calculated, parameters values are: " + moduleAxial.toString())

                moduleBase.calculateValue(moduleNormal, anglePressureNormal, angleHelix, diameterBase, teethNumber, anglePressure, moduleTransverse, diameterReference)
                //////logger.info("module base calculated, parameters values are: " + moduleBase.toString())

                anglePressureNormal.calculateValue(angleHelix, anglePressure, teethNumber, moduleNormal, diameterBase, moduleBase, moduleTransverse)
                //////logger.info("angle pressure normal calculated, parameters values are: " + anglePressureNormal.toString())

                anglePressure.calculateValue(anglePressureNormal, angleHelix, diameterReference, diameterBase, teethNumber, moduleTransverse, moduleNormal, moduleBase)
                //////logger.info("angle pressure calculated, parameters values are: " + anglePressure.toString())

                diameterBase.calculateValue(diameterReference, anglePressure, teethNumber, moduleTransverse, moduleNormal, angleHelix, moduleBase)
                //////logger.info("diameter base calculated, parameters values are: " + diameterBase.toString())
            }
        }
        if (deadloop) {
            teethNumberContradictionInput.value = "no solution"
            normalModuleContradictionInput.value = "no solution"
            transverseModuleContradictionInput.value = "no solution"
            axialModuleContradictionInput.value = "no solution"
            baseModuleContradictionInput.value = "no solution"
            normalPressureAngleContradictionInput.value = "no solution"
            pressureAngleContradictionInput.value = "no solution"
            helixAngleContradictionInput.value = "no solution"
            leadAngleContradictionInput.value = "no solution"
            referenceDiameterContradictionInput.value = "no solution"
            baseDiameterContradictionInput.value = "no solution"
        } else {
            //calculate contradiction
            moduleNormal.calculateContradiction(moduleTransverse, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial)
            ////logger.info("module normal contradiction calculated, parameters value: " + moduleNormal.toString())
            moduleTransverse.calculateContradiction(moduleNormal, angleHelix, diameterReference, teethNumber, angleLead, anglePressure, diameterBase, anglePressureNormal, moduleBase, moduleAxial)
            ////logger.info("module transverse contradiction calculated, parameters value: " + moduleTransverse.toString())
            angleHelix.calculateContradiction(diameterReference, moduleTransverse, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal, teethNumber, moduleAxial)
            ////logger.info("angle helix contradiction calculated, parameters value: " + angleHelix.toString())
            diameterReference.calculateContradiction(moduleTransverse, angleHelix, moduleNormal, teethNumber, diameterBase, anglePressure, anglePressureNormal, moduleBase)
            ////logger.info("diameter reference contradiction calculated, parameters value: " + diameterReference.toString())
            teethNumber.calculateContradiction(diameterReference, moduleTransverse, angleHelix, moduleNormal, diameterBase, anglePressure, angleLead, moduleBase, anglePressureNormal)
            ////logger.info("teeth number contradiction calculated, parameters value: " + teethNumber.toString())
            angleLead.calculateContradiction(moduleTransverse, moduleNormal, moduleAxial, angleHelix)
            ////logger.info("angle lead contradiction calculated, parameters value: " + angleLead.toString())
            moduleAxial.calculateContradiction(moduleTransverse, moduleNormal, angleHelix, angleLead)
            ////logger.info("module axial contradiction calculated, parameters value: " + moduleAxial.toString())
            moduleBase.calculateContradiction(moduleNormal, anglePressureNormal, angleHelix, diameterBase, teethNumber, anglePressure, moduleTransverse, diameterReference)
            ////logger.info("module base contradiction calculated, parameters value: " + moduleBase.toString())
            anglePressureNormal.calculateContradiction(angleHelix, anglePressure, teethNumber, moduleNormal, diameterBase, moduleBase, moduleTransverse)
            ////logger.info("angle pressure normal contradiction calculated, parameters value: " + anglePressureNormal.toString())
            anglePressure.calculateContradiction(anglePressureNormal, angleHelix, diameterReference, diameterBase, teethNumber, moduleTransverse, moduleNormal, moduleBase)
            ////logger.info("angle pressure contradiction calculated, parameters value: " + anglePressure.toString())
            diameterBase.calculateContradiction(diameterReference, anglePressure, teethNumber, moduleTransverse, moduleNormal, angleHelix, moduleBase)
            ////logger.info("diameter base contradiction calculated, parameters value: " + diameterBase.toString())
            helixAngleValueInput.value = if (angleHelix.roundValue()) angleHelix.getRoundValueString() else ""
            leadAngleValueInput.value = if (angleLead.roundValue()) angleLead.getRoundValueString() else ""
            normalPressureAngleValueInput.value = if (anglePressureNormal.roundValue()) anglePressureNormal.getRoundValueString() else ""
            pressureAngleValueInput.value = if (anglePressure.roundValue()) anglePressure.getRoundValueString() else ""
            baseDiameterValueInput.value = if (diameterBase.roundValue()) diameterBase.round_Value.toString() else ""
            axialModuleValueInput.value = if (moduleAxial.roundValue()) moduleAxial.round_Value.toString() else ""
            baseModuleValueInput.value = if (moduleBase.roundValue()) moduleBase.round_Value.toString() else ""
            normalModuleValueInput.value = if (moduleNormal.roundValue()) moduleNormal.round_Value.toString() else ""
            transverseModuleValueInput.value = if (moduleTransverse.roundValue()) moduleTransverse.round_Value.toString() else ""
            teethNumberValueInput.value = if (teethNumber.roundValue()) teethNumber.round_Value.toString() else ""

            helixAngleContradictionInput.value = if (angleHelix.roundContradiction()) angleHelix.getRoundContradictionString() else ""
            leadAngleContradictionInput.value = if (angleLead.roundContradiction()) angleLead.getRoundContradictionString() else ""
            normalPressureAngleContradictionInput.value = if (anglePressureNormal.roundContradiction()) anglePressureNormal.getRoundContradictionString() else ""
            pressureAngleContradictionInput.value = if (anglePressure.roundContradiction()) anglePressure.getRoundContradictionString() else ""
            baseDiameterContradictionInput.value = if (diameterBase.roundContradiction()) diameterBase.round_Contradiction.toString() else ""
            referenceDiameterContradictionInput.value = if (diameterReference.roundContradiction()) diameterReference.round_Contradiction.toString() else ""
            axialModuleContradictionInput.value = if (moduleAxial.roundContradiction()) moduleAxial.round_Contradiction.toString() else ""
            baseModuleContradictionInput.value = if (moduleBase.roundContradiction()) moduleBase.round_Contradiction.toString() else ""
            normalModuleContradictionInput.value=if (moduleNormal.roundContradiction()) moduleNormal.round_Contradiction.toString() else ""
            transverseModuleContradictionInput.value=if (moduleTransverse.roundContradiction()) moduleTransverse.round_Contradiction.toString() else ""
            teethNumberContradictionInput.value=if (teethNumber.roundContradiction()) teethNumber.round_Contradiction.toString() else ""
        }
    }
}