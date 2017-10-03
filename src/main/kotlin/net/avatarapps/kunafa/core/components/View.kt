package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.Dimension
import net.avatarapps.kunafa.core.dimensions.Point
import net.avatarapps.kunafa.core.dimensions.px
import net.avatarapps.kunafa.core.drawable.Color
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document
import kotlin.properties.Delegates
import kotlin.properties.Delegates.observable

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class View(var parent: Container?) {
    var id: String? = null
    open val element = document.createElement("div") as HTMLDivElement

    protected fun updateElementDimensions(){
        updateElementWidth()
        updateElementHeight()
    }

    protected fun updateElementWidth() {
        element.style.width = "${width.pixels}px"
    }

    protected fun updateElementHeight() {
        println("Height of $id: ${height.pixels}")
        element.style.height = "${height.pixels}px"
    }

    open var width: Dimension = Point()
        set(value) {
            field = value
            updateElementWidth()
        }

    open var height: Dimension = Point()
        set(value) {
            field = value
            updateElementHeight()
        }

    var background: Color by observable(Color()) { _, _, _ ->
        element.style.backgroundColor = "rgba(${background.red},${background.green},${background.blue},${background.alpha})"
    }

    fun setMargin(margin: Dimension) {
        marginTop = margin
        marginStart = margin
        marginEnd = margin
        marginBottom = margin
    }

    var marginTop: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.marginTop = "${newValue.pixels}px"
    }

    var marginStart: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.marginLeft = "${newValue.pixels}px"
    }

    var marginEnd: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.marginRight = "${newValue.pixels}px"
    }

    var marginBottom: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.marginBottom = "${newValue.pixels}px"
    }

    fun setPadding(padding: Dimension) {
        paddingTop = padding
        paddingStart = padding
        paddingEnd = padding
        paddingBottom = padding
    }

    var paddingTop: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.paddingTop = "${newValue.pixels}px"
    }

    var paddingStart: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.paddingLeft = "${newValue.pixels}px"
    }

    var paddingEnd: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.paddingRight = "${newValue.pixels}px"
    }

    var paddingBottom: Dimension by observable(Point() as Dimension) { _, _, newValue ->
        element.style.paddingBottom = "${newValue.pixels}px"
    }

    var isScrollableHorizontally by Delegates.observable(false) { _, _, newValue ->
        element.style.overflowX = if (newValue) "scroll" else "hidden"
    }
    var isScrollableVertically by Delegates.observable(false) { _, _, newValue ->
        element.style.overflowY = if (newValue) "scroll" else "hidden"
    }

    init {
        setMargin(0.px)
        setPadding(0.px)
        isScrollableVertically = false
        isScrollableHorizontally = false
    }

//    fun addToParent(): View {
//        println("Adding $id to ${parent?.id}")
//        parent?.add(this)
//        return this
//    }

    open fun render(): dynamic {
        return element
    }

}