package com.narbase.kunafa.core.ssr.components

class TextBuilder {
    private val list = mutableListOf<Any>()
    operator fun String.unaryPlus() {
        list.add(this)
    }

    operator fun View.unaryPlus() {
        this.parent?.removeChild(this)
        list.add(this)
    }

    fun build(): String {
        return list.joinToString(separator = "") {
            if (it is View) it.build()
            else it.toString()
        }
    }

}