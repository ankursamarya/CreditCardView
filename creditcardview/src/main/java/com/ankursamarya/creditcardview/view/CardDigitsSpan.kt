package com.ankursamarya.creditcardview.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt


class CardDigitsSpan(private val space: Boolean) : ReplacementSpan() {
    companion object {
        private val INPUT_CHARS = "X 1234567890"
        private var MAX_CHAR_WIDTH: Int? = null
    }

    private fun getMaxCharWidth(paint: Paint): Int {
        if (MAX_CHAR_WIDTH != null) {
            return MAX_CHAR_WIDTH!!
        }
        val widths = FloatArray(INPUT_CHARS.length)
        paint.getTextWidths(INPUT_CHARS, 0, INPUT_CHARS.length.minus(1), widths)
        return widths.max()!!.roundToInt().also { MAX_CHAR_WIDTH = it }
    }

    private fun getCharsWidth(paint: Paint, text: CharSequence, start: Int, end: Int): FloatArray {
        val widths = FloatArray(end - start)
        paint.getTextWidths(text, start, end, widths)
        return widths;
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int,
            fm: FontMetricsInt?): Int {
        if (fm != null) {
            paint.getFontMetricsInt(fm)
        }
        var count = end - start
        if (count < 0) {
            count = 0
        }
        return getMaxCharWidth(paint) * (count + (if (space) 1 else 0))
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int,
            y: Int, bottom: Int, paint: Paint) {

        val max = getMaxCharWidth(paint)
        val widths = getCharsWidth(paint, text, start, end)

        var i = 0
        val n = end - start
        while (i < n) {
            val p = (if (space) max else 0) + (max - widths[i]) / 2
            canvas.drawText(text, start + i, start + i + 1, x + max * i + p, y.toFloat(), paint)
            ++i
        }
    }
}