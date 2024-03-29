package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.annotations.ChildrenAccessConcurrencyRisk
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.px

class Page : PageInterface, View() {

    override var id: String? = null

    override var useRtl: Boolean = false
    // todo: setter should make body rtl

    override var ref: String? = null

    override val classNameGenerator = ClassNameGenerator()
    override val baseStyles by lazy { BaseStyles(this) }

    val cachedStyleSelectors = mutableMapOf<String, String>()
    val namedStyles = mutableMapOf<String, RuleSet>()
    val keyframes = mutableListOf<Keyframes>()

    @ChildrenAccessConcurrencyRisk
    override val children: Set<View>
        get() = if (isInsideHead) headChildren else bodyChildren


    private var isInsideHead = false

    private val bodyChildren: MutableSet<View> = mutableSetOf()
    private val headChildren: MutableSet<View> = mutableSetOf()

    fun prepare() {
        page = this

        stringRuleSet("body") {
            margin = 0.px
            padding = 0.px
        }
    }

    override fun build(): String {
        val childrenStringBuilder = buildString { bodyChildren.forEach { append(it.build()) } }

        val builder = StringBuilder()
        builder.apply {
            append("<!DOCTYPE html>")
            append("""<html lang="en">""")
            append(getHead())
            append("<body ${getMetaData()}>")

            append(childrenStringBuilder)

            append("</body>")
            append("</html>")
        }
        return builder.toString()
    }

    private fun getHead() = buildString {
        val headChildrenBuilder = buildString { headChildren.forEach { append(it.build()) } }
        append("<head>")
        append("""<meta charset="UTF-8">""")
        append("""<meta content="width=device-width, initial-scale=1" name="viewport">""")
        append(headChildrenBuilder)
        append(getStyles())
        append("</head>")
    }

    private fun getMetaData() = buildString {
        appendLine("data-class-name-generator-counter=${classNameGenerator.counter++}")
        appendLine("data-use-rtl=${useRtl}")
        ref?.let { appendLine("data-kssr-page-ref=$it") }
    }

    private fun getStyles() = buildString {
        append("<style>")
        namedStyles.forEach { namedStyle ->
            namedStyle.value.toRulesList().forEach {
                append(it.toString())
            }
        }
        keyframes.map {
            append(it.toString())
        }
        append("</style>")
    }


    override fun addRuleSetToDocument(ruleSet: RuleSet) {
        namedStyles[ruleSet.selector.toString()] = ruleSet
    }

    override fun addKeyframesToDocument(keyframes: Keyframes) {
        this.keyframes.add(keyframes)
    }

    fun insideHead(block: () -> Unit) {
        isInsideHead = true
        block()
        isInsideHead = false
    }

    fun View.script(block: Script.() -> Unit = {}) = Script(this).visit(page, block)

}