package com.narbase.kunafa.core.css

class GridTemplateAreas(private vararg val areas: String) {
    override fun toString() = areas.map { "'$it'" }.joinToString(separator = "")
}

fun areas(vararg areas: String) = GridTemplateAreas(*areas)
