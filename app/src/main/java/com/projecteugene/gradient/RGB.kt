package com.projecteugene.gradient

import android.util.Log
import kotlin.math.*

/**
 * Created by Eugene Low
 */
data class RGB(val r: Float = 0.toFloat(),
               val g: Float = 0.toFloat(),
               val b: Float = 0.toFloat()) {
    var a: Int = 255

    val redInt: Int
        get() = min(max(r.roundToInt(), 0), 255)

    val greenInt: Int
        get() = min(max(g.roundToInt(), 0), 255)

    val blueInt: Int
        get() = min(max(b.roundToInt(), 0), 255)

    companion object {
        private val colorRegex: Regex = "[0-9A-F]{6}".toRegex(RegexOption.IGNORE_CASE)

        fun parseString(color: String): RGB? {
            if (color.matches(colorRegex)) {
                val colorInt = color.toInt(16)
                val r = colorInt shr 16 and 0xFF
                val g = colorInt shr 8 and 0xFF
                val b = colorInt shr 0 and 0xFF
                return RGB(r.toFloat(), g.toFloat(), b.toFloat())
            }
            return null
        }
    }

    operator fun minus(other: RGB): RGB {
        val newR = r-other.r
        val newG = g-other.g
        val newB = b-other.b
        return RGB(newR, newG, newB)
    }

    operator fun div(other: Int): RGB {
        val newR = r/other
        val newG = g/other
        val newB = b/other
        return RGB(newR, newG, newB)
    }

    operator fun plus(other: RGB): RGB {
        val newR = r+other.r
        val newG = g+other.g
        val newB = b+other.b
        return RGB(newR, newG, newB)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RGB

        if (r != other.r) return false
        if (g != other.g) return false
        if (b != other.b) return false
        if (a != other.a) return false

        return true
    }

    override fun hashCode(): Int {
        var result = r.hashCode()
        result = 31 * result + g.hashCode()
        result = 31 * result + b.hashCode()
        result = 31 * result + a
        return result
    }
}