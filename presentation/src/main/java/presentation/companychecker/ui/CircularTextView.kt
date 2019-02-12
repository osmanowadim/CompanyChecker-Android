package presentation.companychecker.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

class CircularTextView : TextView {

    private var strokeWidth: Float? = null
    private var strokeColor = Color.parseColor("#EA9C00")
    private var solColor = Color.parseColor("#EA9C00")

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun draw(canvas: Canvas?) {

        setStrokeWidth()

        val circlePaint = Paint()
        circlePaint.color = solColor
        circlePaint.flags = Paint.ANTI_ALIAS_FLAG

        val strokePaint = Paint()
        strokePaint.color = strokeColor
        strokePaint.flags = Paint.ANTI_ALIAS_FLAG

        val h = this.height
        val w = this.width

        val diameter = if (h > w) h else w
        val radius = diameter / 2

        this.height = diameter
        this.width = diameter

        canvas?.drawCircle((diameter / 2).toFloat(), (diameter / 2).toFloat(), radius.toFloat(), strokePaint)

        strokeWidth?.let {
            canvas?.drawCircle(
                (diameter / 2).toFloat(),
                (diameter / 2).toFloat(),
                radius - it,
                circlePaint
            )
        }

        super.draw(canvas)
    }

    private fun setStrokeWidth() {
        val scale = context.resources.displayMetrics.density
        strokeWidth = 1 * scale
    }

}
