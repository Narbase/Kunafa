package com.narbase.kunafa.core.css

class Rule<T>(
        val name: String,
        val value: T
) {
    override fun toString() = "$name:$value;"
    override fun hashCode(): Int {
        return name.hashCode() * value.toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other is Rule<*> && name == other.name && value == value
    }
}
