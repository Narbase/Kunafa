package com.narbase.kunafa.core.css

class Rule<T>(
        val name: String,
        val value: T
) {
    override fun toString() = "$name:$value;"
}
