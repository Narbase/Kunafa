package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.annotations.ChildrenAccessConcurrencyRisk
import com.narbase.kunafa.core.css.ClassSelector
import com.narbase.kunafa.core.css.EmptySelector
import com.narbase.kunafa.core.css.RuleSet
import com.narbase.kunafa.core.css.classRuleSet
import kotlin.properties.Delegates.observable

actual open class View(var parent: View? = null) : ViewInterface {

    lateinit var page: Page

    internal var isBuilt = false
    open val element: String = "div"
    override var id: String? by editableBeforeBuild(null)
    val attributes: MutableMap<String, Any> by editableBeforeBuild(mutableMapOf())
    private val classes: MutableList<String> by editableBeforeBuild(mutableListOf())

    private val _children: MutableSet<View> = mutableSetOf()

    @ChildrenAccessConcurrencyRisk
    override val children: Set<View>
        get() = _children

    private fun childrenCopy(): Set<View> = mutableSetOf<View>().apply { addAll(_children) }

    var text: String = ""
    var textBuilder: TextBuilder? = null

    open var ref: String? = null
        set(value) {
            if (value != null) {
                attributes["data-kssr-ref"] = value
            } else {
                attributes.remove("data-kssr-ref")
            }
            field = value
        }

    fun text(block: TextBuilder.() -> Unit) {
        if (textBuilder == null) textBuilder = TextBuilder()
        textBuilder?.block()
    }


    internal open fun addToParent() {
        parent?.mount(this)
    }

    open fun mount(child: View) {
        child.parent = this
        _children.add(child)
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

                when (value) {
                    true -> {
                        append(""" $key """)
                    }
                    false -> {
                    }
                    else -> append(""" $key="$value" """)
                }
            }
            append(">")

            configBegin(builder)

            childrenCopy().forEach { append(it.build()) }

            append("</$element>")
        }
        return builder.toString()
    }

    open fun configureElement(page: Page) {
        this.page = page
        addRuleSet(page.baseStyles.baseClass)
    }

    open fun configBegin(builder: StringBuilder) {
        builder.append(text)
        textBuilder?.let { builder.append(it.build()) }
    }

    private fun simpleStyle(rules: RuleSet.() -> Unit): RuleSet {
        val ruleSet = page.classRuleSet(null, rules)
        addRuleSet(ruleSet)
        return ruleSet
    }

    fun style(shouldHash: Boolean = true, rules: RuleSet.() -> Unit): RuleSet {
        if (shouldHash.not()) {
            return simpleStyle(rules)
        }
        val testRuleSet = RuleSet(page, EmptySelector()).apply { rules() }
        val hashCode = testRuleSet.hashCode().toString()

        val selectorName = page.cachedStyleSelectors[hashCode]

        val ruleSet = if (selectorName == null) {
            val newRuleSet = page.classRuleSet(null, rules)
            page.cachedStyleSelectors[hashCode] = newRuleSet.selector.toString()
            newRuleSet
        } else {
            page.namedStyles[selectorName]
                    ?: throw RuntimeException("RuleSet hash is in cachedStyleSelectors but selector is not in namedStyles")
        }
        addRuleSet(ruleSet)
        return ruleSet
    }

    fun style(name: String, rules: RuleSet.() -> Unit): RuleSet {
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
        if (_children.contains(child).not()) {
            return
        }
        _children.remove(child)
        child.parent = null
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun <T> editableBeforeBuild(initialValue: T) = observable(initialValue, { _, _, _ ->
        if (isBuilt) throw CannotEditViewException("View is already built")
    })
}


