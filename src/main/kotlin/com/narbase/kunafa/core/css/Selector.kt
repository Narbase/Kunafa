@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.css

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2018] Narbase Technologies
 * All Rights Reserved.
 * Created by ${user}
 * On: ${date}.
 */

open class Selector

class ClassSelector(val name: String) : Selector() {

    override fun toString(): String {
        return if (name.startsWith('.')) name else ".$name"
    }
}

class CompoundSelector(val names: List<Selector>) : Selector() {

    override fun toString(): String {
        return names.joinToString(" ")
    }
}

class IdSelector(val name: String) : Selector() {

    override fun toString(): String {
        return if (name.startsWith('#')) name else "#$name"
    }
}

class PseudoSelector(val selector: Selector, val name: String) : Selector() {
    override fun toString(): String {
        return "$selector$name"
    }
}

class StringSelector(val selector: String) : Selector() {
    override fun toString(): String {
        return selector
    }
}

class EmptySelector : Selector() {
    override fun toString(): String {
        return ""
    }
}