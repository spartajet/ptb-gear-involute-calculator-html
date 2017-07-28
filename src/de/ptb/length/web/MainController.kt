package de.ptb.length.web

import de.ptb.length.involute.*
import de.ptb.length.listener.addAngleParaListener
import de.ptb.length.listener.addIntParaListener
import de.ptb.length.listener.addRealParaListener
import org.w3c.dom.*
import kotlin.browser.document

/**
 * @description
 * @create 2017-07-19 下午4:04
 * @email spartajet.guo@gmail.com
 */
fun main(args: Array<String>) {

    //prevent contradiction input get focus
    document.getElementsByClassName("para-contradiction-field").asList().forEach { it ->
        val contradictionField = it as HTMLInputElement
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
    val axialModuleFixModule: HTMLInputElement = document.getElementById("axialModuleFixModule") as HTMLInputElement
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
    val teethNumber: TeethNumber = TeethNumber(false, "", 1000, 1, 4)
    // initial module
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
    // fixed button






    val button1 = document.getElementById("key_button_1") as HTMLButtonElement
    button1.onclick = {
        val active: Element? = document.activeElement
        if (active is HTMLInputElement) {
            val event = document.createEvent("KeyboardEvent")
//            event.initEvent("keydown", true, false)

        }
    }
}