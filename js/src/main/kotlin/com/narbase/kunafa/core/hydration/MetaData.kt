package com.narbase.kunafa.core.hydration

import kotlinx.browser.document
import org.w3c.dom.get


object MetaData {


    fun getMetaClassNameGeneratorCounter(): Int {
        return getMetaData("classNameGeneratorCounter")?.toInt() ?: 0
    }

    fun getMetaUseRtl(): Boolean {
        return getMetaData("useRtl")?.toBoolean() ?: false
    }

    private fun getMetaData(name: String): String? {
        return document.body?.dataset?.get(name)
    }

}
