package de.ptb.length.listener

import de.ptb.length.check.ERROR_CODE_BIGGER
import de.ptb.length.check.ERROR_CODE_SMALLER
import de.ptb.length.involute.IAngle
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent
import kotlin.dom.clear

/**
 * @description
 * @create 2017-07-25 上午8:36
 * @email spartajet.guo@gmail.com
 */
class HTMLInputAngleKeyListner(val element: HTMLInputElement, var iAngle: IAngle, val InformationArea: HTMLDivElement) {
    fun addAngleParaListener() {
        element.addEventListener("keydown", { event ->
            val keyEvent: KeyboardEvent = event as KeyboardEvent
            val char = keyEvent.key
            println("key down ${keyEvent.key}")
            if ("0123456789.°′″".contains(Regex(keyEvent.key))) {
                checkAngleChar(char[0], keyEvent)
            } else if (keyEvent.code == "Backspace") {
                checkAngleChar(8.toChar(), keyEvent)
            } else {
                keyEvent.preventDefault()
            }
        })
    }

    fun checkAngleChar(char: Char, event: KeyboardEvent) {
        val result = iAngle.addChar(char)
        if (result.code != 0) {
            event.preventDefault()
            var infoText = result.message
            if (result.code == ERROR_CODE_SMALLER) {
                infoText = infoText + " : " + iAngle.getValueLimitMin()
            }
            if (result.code == ERROR_CODE_BIGGER) {
                infoText = infoText + " : " + iAngle.getValueLimitMax()
            }
            InformationArea.textContent = infoText
        } else {
            event.preventDefault()
//            htmlInputElement.clear()
            element.value = result.valueString
            iAngle.setValueString(result.valueString)
            InformationArea.clear()
        }

    }
}