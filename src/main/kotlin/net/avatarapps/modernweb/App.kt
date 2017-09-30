package net.avatarapps.modernweb
import net.avatarapps.modernweb.core.components.View
import net.avatarapps.modernweb.core.components.layout.LinearLayout
import net.avatarapps.modernweb.core.components.layout.LinearLayout.Orientation.horizontal
import net.avatarapps.modernweb.core.components.layout.LinearLayout.Orientation.vertical
import net.avatarapps.modernweb.core.components.page
import net.avatarapps.modernweb.core.dimensions.pixels
import net.avatarapps.modernweb.core.drawable.Color

fun main(args: Array<String>) {

    page {
        root = LinearLayout(orientation = horizontal).apply {
            background = Color(200, 200, 200)
            width = 1500.pixels
            height = 600.pixels
            setPadding(20.pixels)
            add(LinearLayout(orientation = vertical).apply {
                background = Color(220, 220, 220)
                width = 300.pixels
                height = 400.pixels
                setPadding(20.pixels)
                isScrollableVertically = true
                add(View().apply {
                    width = 300.pixels
                    height = 500.pixels
                    background = Color(red = 255, green = 0, blue = 0)
                })

                add(View().apply {
                    width = 300.pixels
                    height = 100.pixels
                    background = Color(red = 0, green = 0, blue = 255)
                    marginTop = 20.pixels
                })
            })

            add(View().apply {
                width = 600.pixels
                height = 200.pixels
                background = Color(red = 0, green = 255, blue = 0)
                marginStart = 20.pixels
            })
        }

    }
}

