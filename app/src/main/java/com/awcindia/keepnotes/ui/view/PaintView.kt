package com.awcindia.keepnotes.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint().apply {
        color = 0xFF000000.toInt() // Black color
        strokeWidth = 10f
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    private val path = android.graphics.Path()

    init {
        // Initialize any attributes or properties if needed
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
            }

            else -> return false
        }
        invalidate() // Redraw the view
        return true
    }
}
