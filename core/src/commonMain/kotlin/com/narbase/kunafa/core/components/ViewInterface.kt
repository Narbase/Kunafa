package com.narbase.kunafa.core.components

interface ViewInterface {
    var id: String?
    val children: Set<ViewInterface>

}