package com.narbase.kunafa.core.ssr.components

import com.narbase.kunafa.core.components.PageInterface

class Page : PageInterface, View() {

    override var id: String? = null

    override var useRtl: Boolean = false

    override fun build(): String {

        val builder = StringBuilder()
        builder.apply {
            append("<!DOCTYPE html>")
            append("""<html lang="en">""")
            append(getHead())
            append("<body>")

            children.forEach { append(it.build()) }

            append("</body>")
            append("</html>")
        }
        return builder.toString()
    }

    private fun getHead() = """<head>
                        <meta charset="UTF-8">
                        <meta content="width=device-width, initial-scale=1" name="viewport">
                    </head>"""
}