package com.narbase.kunafa.core.components

import com.narbase.kunafa.core.components.layout.Container
import org.w3c.dom.HTMLImageElement
import kotlin.browser.document

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 10/15/17.
 */
class ImageView(parent: Container? = null) : View(parent) {
    override val element: HTMLImageElement = document.createElement("img") as HTMLImageElement
}