package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.*
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

    fun updateElementDimensions() {
        updateElementWidth()
        updateElementHeight()
    }

    protected fun updateElementWidth() {
        if (contentWidth == null)
            throw DimensionNotCalculatedException("$id.width")

        contentWidth?.let {
            element.style.width = "${it.pixels}px"
        }
    }

    protected fun updateElementHeight() {
        if (contentHeight == null)
            throw DimensionNotCalculatedException("$id.height")

        contentHeight?.let {
            element.style.height = "${it.pixels}px"
        }
    }

    open var width: Dimension = Point()
        set(value) {
            field = value
            (value as? DependentDimension)?.type = DependentDimension.Type.width
        }

    open var height: Dimension = Point()
        set(value) {
            field = value
            (value as? DependentDimension)?.type = DependentDimension.Type.height
        }

    val contentWidth: Pixel?
        get() {
            (width as? IndependentDimension)?.let {
                return it - (paddingStart + paddingEnd)
            }
            (width as? DependentDimension)?.let {
                if (it.isCalculated)
                    it.calculatedDimension?.let {
                        return it - (paddingStart + paddingEnd)
                    }
            }
            return null
        }

    val contentHeight: Pixel?
        get() {
            (height as? IndependentDimension)?.let {
                return it - (paddingTop + paddingBottom)
            }
            (height as? DependentDimension)?.let {
                if (it.isCalculated)
                    it.calculatedDimension?.let {
                        return it - (paddingTop + paddingBottom)
                    }
            }
            return null
        }


    val extendedWidth: Pixel?
        get() {
            (width as? IndependentDimension)?.let {
                return it + (marginStart + marginEnd)
            }
            (width as? DependentDimension)?.let {
                if (it.isCalculated)
                    it.calculatedDimension?.let {
                        return it + (marginStart + marginEnd)
                    }
            }
            return null
        }

    val extendedHeight: Pixel?
        get() {
            (height as? IndependentDimension)?.let {
                return it + (marginTop + marginBottom)
            }
            (height as? DependentDimension)?.let {
                if (it.isCalculated)
                    it.calculatedDimension?.let {
                        return it + (marginTop + marginBottom)
                    }
            }
            return null
        }

    var background: Color by observable(Color()) { _, _, _ ->
        element.style.backgroundColor = "rgba(${background.red},${background.green},${background.blue},${background.alpha})"
    }

    fun setMargin(margin: IndependentDimension) {
        marginTop = margin
        marginStart = margin
        marginEnd = margin
        marginBottom = margin
    }

    var marginTop: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
        element.style.marginTop = "${newValue.pixels}px"
    }

    var marginStart: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
        element.style.marginLeft = "${newValue.pixels}px"
    }

    var marginEnd: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
        element.style.marginRight = "${newValue.pixels}px"
    }

    var marginBottom: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
        element.style.marginBottom = "${newValue.pixels}px"
    }

    fun setPadding(padding: IndependentDimension) {
        paddingTop = padding
        paddingStart = padding
        paddingEnd = padding
        paddingBottom = padding
    }

    var paddingTop: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
        element.style.paddingTop = "${newValue.pixels}px"
    }

    var paddingStart: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
        element.style.paddingLeft = "${newValue.pixels}px"
    }

    var paddingEnd: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
        element.style.paddingRight = "${newValue.pixels}px"
    }

    var paddingBottom: IndependentDimension by observable(Point() as IndependentDimension) { _, _, newValue ->
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


    fun visit(block: View.() -> Unit): View {
        this.block()
        addToParent()
        println("Adding $id")
        return this
    }

    open fun render() {
        println("Rendering: $id")
        updateElementDimensions()
    }

    open fun onParentWidthUpdated() {
        if ((width as? DependentDimension)?.dependency == DependentDimension.Dependency.parent) {
            (width as? DependentDimension)?.calculate()
        }

    }

    open fun onParentHeightUpdated() {
        if ((height as? DependentDimension)?.dependency == DependentDimension.Dependency.parent)
            (height as? DependentDimension)?.calculate()
    }

    fun addToParent() {
        parent?.add(this)
    }


}