package net.avatarapps.kunafa.core.drawable

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
class Color {
    var red: Int = 255
    var green: Int = 255
    var blue: Int = 255
    var alpha: Double = 1.0

    companion object {
        fun rgb(r: Int, g: Int, b: Int, a: Double = 1.0): Color{
            return Color().apply {
                red = r
                green = g
                blue = b
                alpha = a
            }
        }
        val red = Color.rgb(255,0,0)
        val blue = Color.rgb(0,0,255)
        val white = Color.rgb(255,255,255)
        val black = Color.rgb(0,0,0)
        val transparent = Color.rgb(0,0,0,0.0)
    }
}
