package de.ptb.length.listener

import de.ptb.length.check.ERROR_CODE_BIGGER
import de.ptb.length.check.ERROR_CODE_SMALLER
import de.ptb.length.check.InputCheckResultReal
import de.ptb.length.involute.ParaReal
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent
import kotlin.dom.clear

/**
 * @description
 * @create 2017-07-25 上午8:43
 * @email spartajet.guo@gmail.com
 */
fun HTMLInputElement.addRealParaListener(para: ParaReal, InformationArea: HTMLDivElement) {
    this.addEventListener("keydown", { event ->
        val keyEvent: KeyboardEvent = event as KeyboardEvent
        val char = keyEvent.key
        println("key down ${keyEvent.key}")
        if ("0123456789.".contains(Regex(keyEvent.key))) {
            checkRealChar(para, char[0], keyEvent, InformationArea, this)
        } else if (keyEvent.code == "Backspace") {
            checkRealChar(para, 8.toChar(), keyEvent, InformationArea, this)
        } else {
            keyEvent.preventDefault()
        }
    })
    this.addEventListener("focus", {
        val string = this.value
        this.value = ""
        this.value = string
    })
    this.addEventListener("click", {
        val string = this.value
        this.value = ""
        this.value = string
    })
}

fun checkRealChar(paraReal: ParaReal, char: Char, event: KeyboardEvent, InformationArea: HTMLDivElement, htmlInputElement: HTMLInputElement) {
    val result = paraReal.addChar(char) as InputCheckResultReal
    if (result.code != 0) {
        event.preventDefault()
        var infoText = result.message
        if (result.code == ERROR_CODE_SMALLER) {
            infoText = infoText + " : " + paraReal.valueLimitMin
        }
        if (result.code == ERROR_CODE_BIGGER) {
            infoText = infoText + " : " + paraReal.valueLimitMax
        }
        InformationArea.textContent = infoText
    } else {
        event.preventDefault()
        htmlInputElement.value = result.valueString
        paraReal.inputValue = result.value
        paraReal.valueString = result.valueString
        InformationArea.clear()
    }
}