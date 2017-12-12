package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.*
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.Point
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color
import net.avatarapps.kunafa.core.presenter.Presenter
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
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
        set(value) {
            field = value
            value?.let {
                element.id = it
            }
        }

    open val element: HTMLElement = document.createElement("div") as HTMLDivElement

    var presenter: Presenter? = null

    private fun updateElementDimensions() {
        updateElementWidth()
        updateElementHeight()
    }

    open fun updateElementWidth() {
        if (width is DynamicDimension) {
            (width as? DynamicDimension)?.configure(element, Dimension.Type.width)
        } else (width as? CalculatedDimension)?.let {
            if (element.style.width == "${it.pixels}px") return
            element.style.width = "${it.pixels}px"
            element.style.minWidth = "${it.pixels}px"
            onResizedListeners.forEach { it.second() }
        }
        updateContentWidth()
    }

    open internal fun updateContentWidth() {

    }

    open fun updateElementHeight() {
        if (height is DynamicDimension) {
            (height as? DynamicDimension)?.configure(element, Dimension.Type.height)
        } else (height as? CalculatedDimension)?.let {
            if (element.style.height == "${it.pixels}px") return
            element.style.height = "${it.pixels}px"
            element.style.minHeight = "${it.pixels}px"
            onResizedListeners.forEach { it.second() }
        }
        updateContentHeight()
    }

    open internal fun updateContentHeight() {

    }

    open var onClick: ((Event) -> Unit)? = null
        set(value) {
            field = value
            element.onclick = value
        }

    open var width: Dimension = wrapContent
        set(value) {
            field = value
            updateElementDimensions()
        }

    open var height: Dimension = wrapContent
        set(value) {
            field = value
            updateElementDimensions()
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

    open fun configureElement() {
        element.style.boxSizing = "border-box"
        setMargin(0.px)
        setPadding(0.px)
    }

    fun <V : View> V.visit(setup: V.() -> Unit): V {
        configureElement()
        this.addToParent()
        this.setup()
        this.presenter?.onViewCreated(this)
        return this
    }


    internal open fun addToParent() {
        val validParent = parent ?: throw ParentNotFoundException()
        validParent.addChild(this)
        validParent.addOnResizedListener(this, this::onParentResized)
        addOnResizedListener(validParent, validParent::onChildrenResized)
    }


    protected val onResizedListeners: ArrayList<Pair<View, () -> Unit>> = arrayListOf()
    protected val onChildrenResizedListeners: ArrayList<Pair<View, () -> Unit>> = arrayListOf()
    protected val onParentResizedListeners: ArrayList<Pair<View, () -> Unit>> = arrayListOf()

    fun addOnResizedListener(listener: View, onResizeListener: () -> Unit) {
        onResizedListeners.add(Pair(listener, onResizeListener))
    }

    fun addOnParentResizedListener(listener: View, onResizeListener: () -> Unit) {
        onParentResizedListeners.add(Pair(listener, onResizeListener))
    }

    fun addOnChildrenResizedListener(listener: View, onResizeListener: () -> Unit) {
        onChildrenResizedListeners.add(Pair(listener, onResizeListener))
    }

    private fun onParentResized() {
        onParentResizedListeners.forEach { it.second() }
    }

    internal fun onChildrenResized() {
        onChildrenResizedListeners.forEach { it.second() }
    }

    var alignSelf: Alignment = Alignment.Start
        set(value) {
            element.style.alignSelf = value.cssName
        }

}


class ParentNotFoundException : Exception()

class DimensionNotAvailableOnViewException : Exception()

class WrapContentNotSupportedException(message: String) : Exception(message)
