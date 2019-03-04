@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.viewcontroller.LifecycleEvent
import com.narbase.kunafa.core.viewcontroller.LifecycleObserver
import com.narbase.kunafa.core.viewcontroller.LifecycleOwner
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
open class View(var parent: View? = null) : LifecycleOwner {
    var id: String? = null
        set(value) {
            field = value
            value?.let {
                element.id = it
            }
        }

    open val element: HTMLElement = document.createElement("div") as HTMLDivElement

    private val lifecycleObserversList = mutableListOf<LifecycleObserver>()
    override var lastLifecycleEvent: LifecycleEvent? = null

    private fun postViewWillBeCreated() {
        lastLifecycleEvent = LifecycleEvent.ViewWillBeCreated
        lifecycleObserversList.forEach { it.viewWillBeCreated(this) }
    }

    private fun postOnViewCreated() {
        lastLifecycleEvent = LifecycleEvent.ViewCreated
        lifecycleObserversList.forEach { it.onViewCreated(this) }
    }

    private fun postViewWillBeRemoved() {
        lastLifecycleEvent = LifecycleEvent.ViewWillBeRemoved
        lifecycleObserversList.forEach { it.viewWillBeRemoved(this) }
    }

    private fun postOnViewRemoved() {
        lastLifecycleEvent = LifecycleEvent.ViewRemoved
        lifecycleObserversList.forEach { it.onViewRemoved(this) }
    }

    override fun bind(lifecycleObserver: LifecycleObserver) {
        lifecycleObserversList.add(lifecycleObserver)
    }

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
        postViewWillBeCreated()
        configureElement()
        this.setupStyleSheet(rules)
        this.addToParent()
        this.setup()
        postOnViewCreated()
        return this
    }


    private fun setupStyleSheet(rules: (RuleSet.() -> Unit)?) {
        if (rules == null) return
        val ruleSet = classRuleSet(null, rules)
        addRuleSet(ruleSet)
    }

    fun style(rules: RuleSet.() -> Unit): RuleSet {
        val ruleSet = classRuleSet(null, rules)
        addRuleSet(ruleSet)
        return ruleSet
    }

    open fun addRuleSet(ruleSet: RuleSet) {
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

    open val path: String?
        get() = parent?.path


    val children: ArrayList<View> = arrayListOf()

    open fun addChild(child: View) {
        addToElement(child)
        child.parent = this
        children.add(child)
    }

    private fun addToElement(child: View) {
        element.append(child.element)
    }

    open fun removeChild(child: View) {
        child.postViewWillBeRemoved()
        children.remove(child)
        element.removeChild(child.element)
        child.parent = null
        child.postOnViewRemoved()
    }

    open fun clearAllChildren() {
        children.forEach { child ->
            child.postViewWillBeRemoved()
            children.remove(child)
        }
        while (element.firstChild != null) {
            element.firstChild?.let {
                element.removeChild(it)
            }
        }
        children.forEach { child ->
            child.postOnViewRemoved()
            children.remove(child)
        }
    }
}

class ParentNotFoundException : Exception()


