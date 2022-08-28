@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.dimensions

import com.narbase.kunafa.core.css.RuleSet

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

abstract class Dimension

abstract class DynamicDimension : Dimension() {
    abstract fun configureHeight(ruleSet: RuleSet)
    abstract fun configureWidth(ruleSet: RuleSet)
}


class LinearDimension(
        var value: Float,
        var unit: Unit
) : Dimension() {

    override fun toString(): String {
        val string = value.toString()
        return if (string == "0") string else "$string$unit"
    }

    enum class Unit(
            val value: String
    ) {
        PX("px"),
        EM("em"),
        PERCENT("%"),
        EX("ex"),
        INCH("in"),
        CM("cm"),
        MM("mm"),
        PT("pt"),
        VH("vh"),
        VW("vw"),
        PC("pc");

        override fun toString() = value
    }

}

val Number.px: LinearDimension get() = dimen(this, LinearDimension.Unit.PX)
val Number.em: LinearDimension get() = dimen(this, LinearDimension.Unit.EM)
val Number.percent: LinearDimension get() = dimen(this, LinearDimension.Unit.PERCENT)
val Number.ex: LinearDimension get() = dimen(this, LinearDimension.Unit.EX)
val Number.inch: LinearDimension get() = dimen(this, LinearDimension.Unit.INCH)
val Number.cm: LinearDimension get() = dimen(this, LinearDimension.Unit.CM)
val Number.mm: LinearDimension get() = dimen(this, LinearDimension.Unit.MM)
val Number.pt: LinearDimension get() = dimen(this, LinearDimension.Unit.PT)
val Number.pc: LinearDimension get() = dimen(this, LinearDimension.Unit.PC)
val Number.vh: LinearDimension get() = dimen(this, LinearDimension.Unit.VH)
val Number.vw: LinearDimension get() = dimen(this, LinearDimension.Unit.VW)

@Suppress("NOTHING_TO_INLINE")
private inline fun dimen(value: Number, unit: LinearDimension.Unit) = LinearDimension(value.toFloat(), unit)

class StringDimension(val dimension: String) : Dimension() {
    override fun toString() = dimension
}

@Deprecated("Use String.dimen", replaceWith = ReplaceWith("dimension.dimen()", imports = arrayOf("com.narbase.kunafa.core.dimensions.dimen")))
fun st(dimension: String) = StringDimension(dimension)

fun String.dimen() = StringDimension(this)