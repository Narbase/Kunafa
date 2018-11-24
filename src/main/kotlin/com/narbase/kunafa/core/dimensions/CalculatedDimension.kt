package com.narbase.kunafa.core.dimensions

import org.w3c.dom.HTMLElement

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2013] - [2018] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */

abstract class Dimension {
    enum class Type {
        width,
        height,
    }
}

abstract class CalculatedDimension : Dimension() {
    abstract var pixels: Int
}


abstract class DynamicDimension : Dimension() {
    abstract fun configure(element: HTMLElement, type: Dimension.Type)
}


class IndependentDimension(
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
        PC("pc");

        override fun toString() = value
    }

}

val Number.px: IndependentDimension get() = dimen(this, IndependentDimension.Unit.PX)
val Number.em: IndependentDimension get() = dimen(this, IndependentDimension.Unit.EM)
val Number.percent: IndependentDimension get() = dimen(this, IndependentDimension.Unit.PERCENT)
val Number.ex: IndependentDimension get() = dimen(this, IndependentDimension.Unit.EX)
val Number.inch: IndependentDimension get() = dimen(this, IndependentDimension.Unit.INCH)
val Number.cm: IndependentDimension get() = dimen(this, IndependentDimension.Unit.CM)
val Number.mm: IndependentDimension get() = dimen(this, IndependentDimension.Unit.MM)
val Number.pt: IndependentDimension get() = dimen(this, IndependentDimension.Unit.PT)
val Number.pc: IndependentDimension get() = dimen(this, IndependentDimension.Unit.PC)

@Suppress("NOTHING_TO_INLINE")
private inline fun dimen(value: Number, unit: IndependentDimension.Unit) = IndependentDimension(value.toFloat(), unit)

