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
        moduleBase.fixed = baseDiameterFixBox.checked
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
}