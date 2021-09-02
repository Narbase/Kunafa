package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.components.Page
import kotlinx.browser.document
import org.w3c.dom.Element
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleSheet

object SinglePageCssStyleSheetBuilder : CssStyleSheetBuilder(Page) {

    override fun addKeyframesToDocument(keyframes: Keyframes) {
        val sheetElement = getOrCreateKunafaSheet()
        val sheet = sheetElement.sheet as? CSSStyleSheet
        sheet?.insertRule(keyframes.toString(), sheet.cssRules.length)
    }

    override fun addRuleSetToDocument(ruleSet: RuleSet) {
        val sheetElement = getOrCreateKunafaSheet()
        val sheet = sheetElement.sheet as? CSSStyleSheet
        ruleSet.toRulesList().forEach {
            sheet?.insertRule(it.toString(), sheet.cssRules.length)
        }
    }

    private fun getOrCreateKunafaSheet(): HTMLStyleElement {
        val existingElement = kunafaStyleElement ?: document.getElementById("kunafa-styles")
        val element = existingElement ?: createNewStyleElement()
        return element as HTMLStyleElement
    }

    private fun createNewStyleElement(): Element {
        val element = document.createElement("style").apply {
            id = "kunafa-styles"
            document.head?.appendChild(this)
        }
        kunafaStyleElement = element
        return element
    }

    private var kunafaStyleElement: Element? = null
}