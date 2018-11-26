@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.dimensions

import com.narbase.kunafa.core.css.RuleSet

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
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

@Suppress("NOTHING_TO_INLINE")
private inline fun dimen(value: Number, unit: LinearDimension.Unit) = LinearDimension(value.toFloat(), unit)

