package net.avatarapps.dopa.dashboard.dashboard.view

import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color

class SalesmenRequestsView : ViewContent() {
    override fun DetachedView.contentDefinition() {
        verticalLayout {
            width = matchParent
            height = matchParent
            background = Color.white
            padding = 20.px

            textView {
                width = matchParent
                height = wrapContent
                text = "Hi, I am second view"
                textAlign = TextView.TextAlign.Center
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
            }

            textInput {
                width = matchParent
                height = wrapContent
                placeholder = "Fill me in, baby"
                textAlign = TextView.TextAlign.Left
                textSize = 24.px
                textColor = Color.rgb(100, 100, 100)
                onChange = { event ->
                    println("Printed: ${this@textInput.text}")
                }
            }
        }
    }
}