package com.narbase.kunafa.core.components

import kotlinx.browser.document
import org.w3c.dom.HTMLImageElement

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
actual class ImageView(parent: View? = null, override val element: HTMLImageElement = document.createElement("img") as HTMLImageElement) : View(parent)