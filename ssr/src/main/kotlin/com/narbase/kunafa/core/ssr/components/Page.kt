package com.narbase.kunafa.core.ssr.components

import com.narbase.kunafa.core.components.PageInterface
import com.narbase.kunafa.core.css.ClassNameGenerator
import com.narbase.kunafa.core.css.RuleSet

class Page : PageInterface, View() {

    override var id: String? = null

    override var useRtl: Boolean = false

    override val styleSheetBuilder = PageStyleSheetBuilder(this)
    override val classNameGenerator = ClassNameGenerator()
    val namedStyles = mutableMapOf<String, RuleSet>()


    override fun build(): String {
        val childrenStringBuilder = buildString { children.forEach { append(it.build()) } }

        val builder = StringBuilder()
        builder.apply {
            append("<!DOCTYPE html>")
            append("""<html lang="en">""")
            append(getHead())
            append("<body>")

            append(childrenStringBuilder)

            append("</body>")
            append("</html>")
        }
        return builder.toString()
    }

    private fun getHead() = buildString {
        append("<head>")
        append("""<meta charset="UTF-8">""")
        append("""<meta content="width=device-width, initial-scale=1" name="viewport">""")
        append(getStyles())
        append("</head>")
    }

    private fun getStyles() = buildString {
        append("<style>")
        namedStyles.map {
            append(it.value.toString())
        }
        append("</style>")
    }


    override val linearLayoutClass: RuleSet
        get() = TODO("Not yet implemented")

    override val verticalLayoutClass: RuleSet
        get() = TODO("Not yet implemented")
    override val horizontalLayoutClass: RuleSet
        get() = TODO("Not yet implemented")


//    DSL

    fun View.style(shouldHash: Boolean = true, rules: RuleSet.() -> Unit) = style(this@Page, shouldHash, rules)

    fun View.style(name: String, rules: RuleSet.() -> Unit) = style(this@Page, name, rules)
}