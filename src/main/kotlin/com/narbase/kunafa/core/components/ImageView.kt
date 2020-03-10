package com.narbase.kunafa.core.components

import org.w3c.dom.HTMLImageElement
import kotlin.browser.document

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class ImageView(parent: View? = null) : View(parent) {
    override val element: HTMLImageElement = document.createElement("img") as HTMLImageElement
}