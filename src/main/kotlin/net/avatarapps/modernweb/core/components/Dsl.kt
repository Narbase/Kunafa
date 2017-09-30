package net.avatarapps.modernweb.core.components

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

fun page(block: Page.() -> Unit = {}){
    Page.block()
    Page.render()
}