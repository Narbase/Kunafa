@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import org.w3c.dom.Element
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.svg.SVGElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.removeClass

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class Svg(parent: BaseElement? = null) : BaseElement(parent) {

    override var isViewMounted: Boolean = false

    override val element: SVGElement = document.createElement("svg") as SVGElement


}
