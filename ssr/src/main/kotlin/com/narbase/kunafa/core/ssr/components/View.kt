package com.narbase.kunafa.core.ssr.components

import com.narbase.kunafa.core.components.ViewInterface
import com.narbase.kunafa.core.css.ClassSelector
import com.narbase.kunafa.core.css.EmptySelector
import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.ssr.css.classRuleSet
import kotlin.properties.Delegates.observable

open class View(var parent: View? = null) : ViewInterface {
    private var isBuilt = false
    open val element: String = "div"
    override var id: String? by editableBeforeBuild(null)
    val attributes: MutableMap<String, String> by editableBeforeBuild(mutableMapOf())
    private val classes: MutableList<String> by editableBeforeBuild(mutableListOf())
    override val children: MutableSet<View> = mutableSetOf()

    internal open fun addToParent() {
        parent?.mount(this)
    }

    open fun mount(child: View) {
        child.parent = this
        children.add(child)
    }

    open fun build(): String {
        isBuilt = true
        val builder = StringBuilder()
        builder.apply {
            append("<$element")
            id?.let { append(""" id="$it" """) }
            if (classes.isNotEmpty()) {
                append(""" class="${classes.joinToString(" ")}" """)
            }

            attributes.forEach { (key, value) ->
                append(""" $key="$value" """)
            }
            append(">")

            configBegin(builder)

            children.forEach { append(it.build()) }

            append("</$element>")
        }
        return builder.toString()
    }

    open fun configBegin(builder: StringBuilder) {

    }

    private fun simpleStyle(page: Page, rules: RuleSet.() -> Unit): RuleSet {
        val ruleSet = page.classRuleSet(null, rules)
        addRuleSet(ruleSet)
        return ruleSet
    }

    fun style(page: Page, shouldHash: Boolean = true, rules: RuleSet.() -> Unit): RuleSet {
        if (shouldHash.not()) {
            return simpleStyle(page, rules)
        }
        val testRuleSet = RuleSet(page, EmptySelector()).apply { rules() }
        val hashCode = testRuleSet.hashCode().toString()
        val ruleSet = page.namedStyles.getOrElse(hashCode) {
            val newRuleSet = page.classRuleSet(null, rules)
            page.namedStyles[hashCode] = newRuleSet
            newRuleSet
        }
        addRuleSet(ruleSet)
        return ruleSet
    }

    fun style(page: Page, name: String, rules: RuleSet.() -> Unit): RuleSet {
        val ruleSet = page.namedStyles.getOrElse(name) {
            val newRuleSet = page.classRuleSet(name, rules)
            page.namedStyles[name] = newRuleSet
            newRuleSet
        }
        addRuleSet(ruleSet)
        return ruleSet
    }

    open fun addRuleSet(ruleSet: RuleSet) {
        val selector = ruleSet.selector
        if (selector is ClassSelector) {
            classes.add(selector.name)
        }
    }


    open fun removeChild(child: View) {
        if (children.contains(child).not()) {
            return
        }
        children.remove(child)
        child.parent = null
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun <T> editableBeforeBuild(initialValue: T) = observable(initialValue, { _, _, _ ->
        if (isBuilt) throw CannotEditViewException("View is already built")
    })
}


