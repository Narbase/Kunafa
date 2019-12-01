@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.components

import org.w3c.dom.svg.SVGElement
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class Svg(parent: View? = null) : View(parent) {

    override var isViewMounted: Boolean = false

    override val element: SVGElement = document.createElement("svg") as SVGElement


}
