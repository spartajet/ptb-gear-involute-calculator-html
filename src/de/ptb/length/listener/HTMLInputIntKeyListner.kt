package de.ptb.length.listener

import de.ptb.length.check.InputCheckResultInt
import de.ptb.length.involute.ParaInt
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent
import kotlin.dom.clear

/**
 * @description
 * @create 2017-07-25 上午8:48
 * @email spartajet.guo@gmail.com
 */
//class HTMLInputIntKeyListner {
    fun HTMLInputElement.addIntParaListener(para: ParaInt, InformationArea: HTMLDivElement) {
        this.addEventListener("keydown", { event ->
            val keyEvent: KeyboardEvent = event as KeyboardEvent
            val char = keyEvent.key
            println("key down ${keyEvent.key}")
            if ("0123456789".contains(Regex(keyEvent.key))) {
                checkIntChar(para, char[0], keyEvent, InformationArea, this)
            } else if (keyEvent.code == "Backspace") {
                checkIntChar(para, 8.toChar(), keyEvent, InformationArea, this)
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

    fun checkIntChar(para: ParaInt, char: Char, event: KeyboardEvent, InformationArea: HTMLDivElement, htmlInputElement: HTMLInputElement) {
        val result = para.addChar(char) as InputCheckResultInt
        if (result.code != 0) {
            event.preventDefault()
            val infoText = result.message
            htmlInputElement.textContent = result.valueString
            para.inputValue = result.value
            para.valueString = result.valueString
            InformationArea.textContent = infoText
        } else {
            event.preventDefault()
            htmlInputElement.clear()
            htmlInputElement.textContent = result.valueString
            para.inputValue = result.value
            para.valueString = result.valueString
            InformationArea.clear()
        }
    }
//}