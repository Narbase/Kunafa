@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.lifecycle.LifecycleEvent
import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
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

    internal fun postViewWillMount() {
        lastLifecycleEvent = LifecycleEvent.ViewWillMount
        lifecycleObserversList.forEach { it.viewWillMount(this) }
    }

    internal fun postOnViewMounted() {
        lastLifecycleEvent = LifecycleEvent.ViewMounted
        lifecycleObserversList.forEach { it.onViewMounted(this) }
    }

    private fun postViewWillBeRemoved() {
        children.forEach { it.postViewWillBeRemoved() }
        lastLifecycleEvent = LifecycleEvent.ViewWillBeRemoved
    }

    private fun postOnViewRemoved() {
        children.forEach { it.postOnViewRemoved() }
        lastLifecycleEvent = LifecycleEvent.ViewRemoved
    }

    override fun bind(lifecycleObserver: LifecycleObserver) {
        if (lifecycleObserversList.contains(lifecycleObserver)) {
            return
        }
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


//    private fun setupStyleSheet(rules: (RuleSet.() -> Unit)?) {
//        if (rules == null) return
//        val ruleSet = classRuleSet(null, rules)
//        addRuleSet(ruleSet)
//    }

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

    fun addChild(child: View) {
        child.postViewWillMount()
        mountChild(child)
        child.postOnViewMounted()
    }

    fun addChildAfter(child: View, referenceNode: View) {
        child.postViewWillMount()
        mountChildAfter(child, referenceNode)
        child.postOnViewMounted()
    }

    internal open fun addToParent() {
        parent?.mountChild(this)
    }

    protected open fun mountChild(child: View) {
        element.append(child.element)
        child.parent = this
        children.add(child)
    }

    protected open fun mountChildAfter(child: View, referenceNode: View) {
        element.insertBefore(child.element, referenceNode.element.nextSibling)
        child.parent = this
        children.add(child)
    }

    open fun removeChild(child: View) {
        if (children.contains(child).not()) {
            return
        }
        child.postViewWillBeRemoved()
        children.remove(child)
        if (element.contains(child.element)) {
            element.removeChild(child.element)
        }
        child.parent = null
        child.postOnViewRemoved()
    }

    open fun clearAllChildren() {
        children.forEach { child ->
            child.postViewWillBeRemoved()
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


    fun <V : Component> mount(component: V): V = component.apply { addToParent(this@View) }

    fun <V : Component> mountAfter(component: V, referenceView: View): V = component.apply { addToParentAfter(this@View, referenceView) }

    fun <V : Component> unMount(component: V): V = component.apply { removeFromParent(this@View) }
}
