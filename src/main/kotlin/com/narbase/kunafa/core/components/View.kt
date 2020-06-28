@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.removeClass

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
open class View(var parent: View? = null) : LifecycleOwner {
    var id: String?
        get() = element.id
        set(value) {
            value?.let {
                element.id = it
            }
        }

    override val isViewMounted: Boolean
        get() = element.parentElement != null && parent?.isViewMounted == true

    open val element: HTMLElement = document.createElement("div") as HTMLDivElement

    private val lifecycleObserversList = mutableListOf<LifecycleObserver>()

    internal fun postOnViewCreated() {
        lifecycleObserversList.forEach { it.onViewCreated(this) }
    }

    internal fun postViewWillMount() {
        lifecycleObserversList.forEach { it.viewWillMount(this) }
        children.forEach { it.postViewWillMount() }
    }

    internal fun postOnViewMounted() {
        if (parent?.isViewMounted != true) return
        lifecycleObserversList.forEach { it.onViewMounted(this) }
        children.forEach { it.postOnViewMounted() }
    }

    internal fun postViewWillBeRemoved() {
        lifecycleObserversList.forEach { it.viewWillBeRemoved(this) }
        children.forEach { it.postViewWillBeRemoved() }
    }

    internal fun postOnViewRemoved() {
        children.forEach { it.postOnViewRemoved() }
        lifecycleObserversList.forEach { it.onViewRemoved(this) }
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

    open var onClick: ((MouseEvent) -> dynamic)?
        get() = element.onclick
        set(value) {
            element.onclick = value
        }

    open fun configureElement() {
        addRuleSet(baseClass)
    }

    private fun simpleStyle(rules: RuleSet.() -> Unit): RuleSet {
        val ruleSet = classRuleSet(null, rules)
        addRuleSet(ruleSet)
        return ruleSet
    }

    fun style(shouldHash: Boolean = true, rules: RuleSet.() -> Unit): RuleSet {
        if (shouldHash.not()) {
            return simpleStyle(rules)
        }
        val testRuleSet = RuleSet(EmptySelector()).apply { rules() }
        val hashCode = testRuleSet.hashCode().toString()
        val ruleSet = namedStyles.getOrElse(hashCode) {
            val newRuleSet = classRuleSet(null, rules)
            namedStyles[hashCode] = newRuleSet
            newRuleSet
        }
        addRuleSet(ruleSet)
        return ruleSet
    }

    fun style(name: String, rules: RuleSet.() -> Unit): RuleSet {
        val ruleSet = namedStyles.getOrElse(name) {
            val newRuleSet = classRuleSet(name, rules)
            namedStyles[name] = newRuleSet
            newRuleSet
        }
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
            margin = 0.px
            padding = 0.px
            flexShrink = "0"
        }
        val invisibleClass = classRuleSet {
            display = "none !important"
        }
    }

    open val path: String?
        get() = parent?.path


    val children: MutableSet<View> = mutableSetOf()


    internal open fun addToParent() {
        parent?.mount(this)
    }

    open fun mount(child: View) {
        if (child.element.parentElement == element) return
        child.postViewWillMount()
        element.append(child.element)
        child.parent = this
        children.add(child)
        child.postOnViewMounted()
    }

    open fun mountAfter(child: View, referenceNode: View) {
        if (child.element.parentElement == element) return
        child.postViewWillMount()
        element.insertBefore(child.element, referenceNode.element.nextSibling)
        child.parent = this
        children.add(child)
        child.postOnViewMounted()
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
