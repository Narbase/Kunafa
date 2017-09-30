package net.avatarapps.modernweb.core.components

import net.avatarapps.modernweb.core.dimensions.Dimension
import net.avatarapps.modernweb.core.dimensions.Point
import net.avatarapps.modernweb.core.drawable.Color
import org.w3c.dom.HTMLElement
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
open class View {
    val element = document.createElement("div") as HTMLElement

    var width: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.width = "${width.pixels}px"
    }

    var height: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.height = "${height.pixels}px"
    }

    var background: Color by observable(Color()){ _,_,_ ->
        element.style.backgroundColor = "rgba(${background.red},${background.green},${background.blue},${background.alpha})"
    }

    fun setMargin(margin: Dimension) {
        marginTop = margin
        marginStart = margin
        marginEnd = margin
        marginBottom = margin
    }

    var marginTop: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.marginTop = "${marginTop.pixels}px"
    }

    var marginStart: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.marginLeft = "${marginStart.pixels}px"
    }

    var marginEnd: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.marginRight = "${marginEnd.pixels}px"
    }

    var marginBottom: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.marginBottom = "${marginBottom.pixels}px"
    }

    fun setPadding(padding: Dimension) {
        paddingTop = padding
        paddingStart = padding
        paddingEnd = padding
        paddingBottom = padding
    }

    var paddingTop: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.paddingTop = "${paddingTop.pixels}px"
    }

    var paddingStart: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.paddingLeft = "${paddingStart.pixels}px"
    }

    var paddingEnd: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.paddingRight = "${paddingEnd.pixels}px"
    }

    var paddingBottom: Dimension by observable(Point() as Dimension){ _,_,_ ->
        element.style.paddingBottom = "${paddingBottom.pixels}px"
    }

    var isScrollableHorizontally by Delegates.observable(false){ _, _, newValue ->
        element.style.overflowX =  if (newValue) "scroll" else "hidden"
    }
    var isScrollableVertically by Delegates.observable(false){  _, _, newValue ->
        element.style.overflowY =  if (newValue) "scroll" else "hidden"
    }


    open fun render(): dynamic  {
        return element
    }

}