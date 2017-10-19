package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.*
import net.avatarapps.kunafa.core.dimensions.DependentDimension.Dependency
import net.avatarapps.kunafa.core.dimensions.DependentDimension.Dependency.children
import net.avatarapps.kunafa.core.dimensions.DependentDimension.Type
import net.avatarapps.kunafa.core.dimensions.independent.Pixel
import net.avatarapps.kunafa.core.dimensions.independent.Point
import net.avatarapps.kunafa.core.dimensions.independent.px
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
open class View(var parent: Container? = null) {
    var id: String? = null
    val element = document.createElement("div") as HTMLDivElement

    private fun updateElementDimensions() {
        updateElementWidth()
        updateElementHeight()
    }

     open fun updateElementWidth() {
        contentWidth?.let {
            println("Updating dimensions")
            if (element.style.width == "${it.pixels}px") return
            element.style.width = "${it.pixels}px"
            println("$id width changed to ${it.pixels}px")
            onResizedListeners.forEach { it.second() }
        }
    }

     open fun updateElementHeight() {
        contentHeight?.let {
            if (element.style.height == "${it.pixels}px") return
            element.style.height = "${it.pixels}px"
            println("$id height changed to ${it.pixels}px")
            onResizedListeners.forEach { it.second() }
        }
    }

    open var width: Dimension = Point()
        set(value) {
            field = value
            if (value is DependentDimension){
                value.type = Type.width
                value.setListeners()
                value.onChange = {
                    updateElementDimensions()
                }
            }
            updateElementDimensions()
        }

    open var height: Dimension = Point()
        set(value) {
            field = value
            if (value is DependentDimension){
                value.type = Type.height
                value.setListeners()
                value.onChange = {
                    updateElementDimensions()
                }
            }
            updateElementDimensions()
        }

    val contentWidth: Pixel?
        get() {
            return Pixel((width.pixels - (paddingStart.pixels + paddingEnd.pixels)).takeIf { it > 0 }?:0)
        }

    val contentHeight: Pixel?
        get() {
            return Pixel((height.pixels - (paddingTop.pixels + paddingBottom.pixels)).takeIf { it > 0 }?:0)
        }

    val extendedWidth: Pixel?
        get() {
            return Pixel(width.pixels + (marginStart.pixels + marginEnd.pixels))
        }

    val extendedHeight: Pixel?
        get() {
            return Pixel(height.pixels + (marginTop.pixels + marginBottom.pixels))
        }

    open val wrappedContentWidth: IndependentDimension
        get() = throw WrapContentNotSupportedException("WrapContent not supported in View")

    open val wrappedContentHeight: IndependentDimension
        get() = throw WrapContentNotSupportedException("WrapContent not supported in View")

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

    }

    fun <V : View> V.visit(setup: V.() -> Unit): V {
        this.addToParent()
        this.setup()

//        (this.width as? DependentDimension)?.let {
//            when (it.dependency) {
//                children ->
//                    calculateWidthWithChildrenDependency()
//
//                Dependency.parent ->
//                    if (this.parent?.width?.isCalculated == true)
//                        calculateWidthWithParentDependency()
//            }
//        }
//
//        (this.height as? DependentDimension)?.let {
//            when (it.dependency) {
//                children ->
//                    calculateHeightWithChildrenDependency()
//
//                Dependency.parent ->
//                    if (this.parent?.height?.isCalculated == true)
//                        calculateHeightWithParentDependency()
//            }
//        }
//        (this as? Container)?.let {
//            if (width.isCalculated) it.updateChildrenWidths()
//            if (height.isCalculated) it.updateChildrenHeights()
//        }
        return this
    }

    open protected fun calculateWidthWithChildrenDependency() {
        (width as? DependentDimension)?.let {
            if (it.dependency == children)
                it.calculate()
        }
//        if (width.isCalculated)
//            updateElementWidth()
    }

    open protected fun calculateHeightWithChildrenDependency() {
        (height as? DependentDimension)?.let {
            if (it.dependency == children)
                it.calculate()
        }
//        if (height.isCalculated)
//            updateElementHeight()
    }

    open protected fun calculateWidthWithParentDependency() {
        (width as? DependentDimension)?.let {
            if (it.dependency == Dependency.parent)
                it.calculate()
        }
//        if (width.isCalculated)
//            updateElementWidth()
    }

    open protected fun calculateHeightWithParentDependency() {
        (height as? DependentDimension)?.let {
            if (it.dependency == Dependency.parent)
                it.calculate()
        }
//        if (height.isCalculated)
//            updateElementHeight()
    }

    open fun render() {
        updateElementDimensions()
    }

    open fun onParentWidthUpdated() {
        if ((width as? DependentDimension)?.dependency == Dependency.parent) {
            (width as? DependentDimension)?.calculate()
        }
    }

    open fun onParentHeightUpdated() {
        if ((height as? DependentDimension)?.dependency == Dependency.parent)
            (height as? DependentDimension)?.calculate()
    }

    internal open fun addToParent() {
        val validParent = parent ?: throw ParentNotFoundException()
        validParent.add(this)
        validParent.addOnResizedListener(this, this::onParentResized)
        addOnResizedListener(validParent, validParent::onChildrenResized)
    }


    protected val onResizedListeners: ArrayList<Pair<View, () -> Unit>> = arrayListOf()
    protected val onChildrenResizedListeners: ArrayList<Pair<View, () -> Unit>> = arrayListOf()
    protected val onParentResizedListeners: ArrayList<Pair<View, () -> Unit>> = arrayListOf()

    fun addOnResizedListener(listener: View, onResizeListener: () -> Unit){
        onResizedListeners.add(Pair(listener, onResizeListener))
    }

    fun addOnParentResizedListener(listener: View, onResizeListener: () -> Unit){
        onParentResizedListeners.add(Pair(listener, onResizeListener))
    }

    fun addOnChildrenResizedListener(listener: View, onResizeListener: () -> Unit){
        onChildrenResizedListeners.add(Pair(listener, onResizeListener))
    }

    private fun onParentResized(){
        onParentResizedListeners.forEach { it.second()}
    }

    internal fun onChildrenResized(){
        onChildrenResizedListeners.forEach { it.second() }
    }


}

class ParentNotFoundException : Exception()

class DimensionNotAvailableOnViewException : Exception()

class WrapContentNotSupportedException(message: String) : Exception(message)
