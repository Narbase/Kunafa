@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.viewcontroller.ViewController
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.removeClass

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class View(var parent: Container? = null) {
    var id: String? = null
        set(value) {
            field = value
            value?.let {
                element.id = it
            }
        }

    open val element: HTMLElement = document.createElement("div") as HTMLDivElement

    var viewController: ViewController? = null

    private var savedDisplayState: String? = "null"

    var isVisible: Boolean = true
        set(value) {
            field = value
            when (value) {
                true -> {
                    removeRuleSet(invisibleClass)
                }
                false -> {
                    addRuleSet(invisibleClass)
                }
            }
        }

    open var onClick: ((Event) -> Unit)? = null
        set(value) {
            field = value
            element.onclick = value
        }

    open fun configureElement() {
        addRuleSet(baseClass)
    }

    fun <V : View> V.visit(rules: (RuleSet.() -> Unit)?, setup: V.() -> Unit): V {
        this.viewController?.viewWillBeCreated(this)
        configureElement()
        this.setupStyleSheet(rules)
        this.addToParent()
        this.setup()
        this.viewController?.onViewCreated(this)
        return this
    }


    private fun setupStyleSheet(rules: (RuleSet.() -> Unit)?) {
        if (rules == null) return
        val ruleSet = classRuleSet(null, rules)
        addRuleSet(ruleSet)
    }

    fun style(rules: RuleSet.() -> Unit) = addRuleSet(classRuleSet(null, rules))

    fun addRuleSet(ruleSet: RuleSet) {
        val selector = ruleSet.selector
        if (selector is ClassSelector) {
            val className = selector.name
            this.element.addClass(className)
        }
    }

    fun removeRuleSet(ruleSet: RuleSet) {
        val selector = ruleSet.selector
        if (selector is ClassSelector) {
            val className = selector.name
            this.element.removeClass(className)
        }
    }

    internal open fun addToParent() {
        val validParent = parent ?: throw ParentNotFoundException()
        validParent.addChild(this)
    }

    companion object {
        val baseClass = classRuleSet {
            boxSizing = "border-box"
            margin = "0px"
            padding = "0px"
            flexShrink = "0"
        }
        val invisibleClass = classRuleSet {
            display = "none !important"
        }
    }


}

class ParentNotFoundException : Exception()