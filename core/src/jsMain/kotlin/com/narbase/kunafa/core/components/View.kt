@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.Page.namedStyles
import com.narbase.kunafa.core.css.ClassSelector
import com.narbase.kunafa.core.css.EmptySelector
import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.css.classRuleSet
import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import kotlinx.browser.document
import kotlinx.dom.addClass
import kotlinx.dom.clear
import kotlinx.dom.removeClass
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual open class View(
        var parent: View? = null,
        open val element: HTMLElement = document.createElement("div") as HTMLElement
) : ViewInterface, LifecycleOwner {

    override var id: String?
        get() = element.id
        set(value) {
            value?.let {
                element.id = it
            }
        }

    override val isViewMounted: Boolean
        get() = element.parentElement != null && parent?.isViewMounted == true

//    open val element: HTMLElement = document.createElement("div") as HTMLDivElement

    private val lifecycleObserversList = mutableListOf<LifecycleObserver>()

    internal fun postOnViewCreated() {
        lifecycleObserversList.forEach { it.onViewCreated(this) }
    }

    internal fun postViewWillMount() {
        lifecycleObserversList.forEach { it.viewWillMount(this) }
        childrenCopy().forEach { it.postViewWillMount() }
    }

    internal fun postOnViewMounted() {
        if (parent?.isViewMounted != true) return
        lifecycleObserversList.forEach { it.onViewMounted(this) }
        childrenCopy().forEach { it.postOnViewMounted() }
    }

    internal fun postViewWillBeRemoved() {
        lifecycleObserversList.forEach { it.viewWillBeRemoved(this) }
        childrenCopy().forEach { it.postViewWillBeRemoved() }
    }

    internal fun postOnViewRemoved() {
        childrenCopy().forEach { it.postOnViewRemoved() }
        lifecycleObserversList.forEach { it.onViewRemoved(this) }
    }

    override fun bind(lifecycleObserver: LifecycleObserver) {
        if (lifecycleObserversList.contains(lifecycleObserver)) {
            return
        }
        lifecycleObserversList.add(lifecycleObserver)
    }

    override fun unbind(lifecycleObserver: LifecycleObserver) {
        lifecycleObserversList.remove(lifecycleObserver)
    }

    var isVisible: Boolean = true
        set(value) {
            field = value
            when (value) {
                true -> {
                    removeRuleSet(Page.baseStyles.invisibleClass)
                }
                false -> {
                    addRuleSet(Page.baseStyles.invisibleClass)
                }
            }
        }

    open var onClick: ((MouseEvent) -> dynamic)?
        get() = element.onclick
        set(value) {
            element.onclick = value
        }

    fun skipBaseClass() {
        shouldSkipBaseClass = true
    }

    private var shouldSkipBaseClass = false

    open fun configureElement() {
        if (shouldSkipBaseClass.not()) {
            addRuleSet(Page.baseStyles.baseClass)
        }
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
        val testRuleSet = RuleSet(Page, EmptySelector()).apply { rules() }
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

    open val path: String?
        get() = parent?.path


    private val _children: MutableSet<View> = mutableSetOf()
    override val children: Set<View>
        get() = _children

    private fun childrenCopy(): Set<View> = mutableSetOf<View>().apply { addAll(children) }
    private fun lifecycleObserversCopy(): Set<LifecycleObserver> = mutableSetOf<LifecycleObserver>().apply { addAll(lifecycleObserversList) }


    internal open fun addToParent() {
        parent?.mount(this)
    }

    open fun mount(child: View) {
        if (child.element.parentElement == element) return
        child.postViewWillMount()
        element.append(child.element)
        child.parent = this
        _children.add(child)
        child.postOnViewMounted()
    }

    open fun mountAfter(child: View, referenceNode: View) {
        if (child.element.parentElement == element) return
        child.postViewWillMount()
        element.insertBefore(child.element, referenceNode.element.nextSibling)
        child.parent = this
        _children.add(child)
        child.postOnViewMounted()
    }

    open fun mountBefore(child: View, referenceNode: View) {
        if (child.element.parentElement == element) return
        child.postViewWillMount()
        element.insertBefore(child.element, referenceNode.element)
        child.parent = this
        _children.add(child)
        child.postOnViewMounted()
    }

    open fun removeChild(child: View) {
        if (_children.contains(child).not()) {
            return
        }
        child.postViewWillBeRemoved()
        _children.remove(child)
        if (element.contains(child.element)) {
            element.removeChild(child.element)
        }
        child.parent = null
        child.postOnViewRemoved()
    }

    open fun clearAllChildren() {
        val childrenCopy = childrenCopy()
        childrenCopy.forEach { child ->
            child.postViewWillBeRemoved()
        }
        element.clear()
        childrenCopy.forEach { child ->
            child.postOnViewRemoved()
        }
        _children.clear()
    }

    open fun clearAllLifecycleObservers() {
        val lifecycleObserversCopy = lifecycleObserversCopy()
        lifecycleObserversCopy.forEach { lifecycleObserver ->
            unbind(lifecycleObserver)
        }
    }


    fun <V : Component> mount(component: V): V = component.apply { addToParent(this@View) }

    fun <V : Component> mountAfter(component: V, referenceView: View): V = component.apply { addToParentAfter(this@View, referenceView) }

    fun <V : Component> unMount(component: V): V = component.apply { removeFromParent(this@View) }
}
