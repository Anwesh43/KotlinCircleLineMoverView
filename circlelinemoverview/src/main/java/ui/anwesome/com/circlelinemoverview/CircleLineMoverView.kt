package ui.anwesome.com.circlelinemoverview

/**
 * Created by anweshmishra on 22/02/18.
 */
import android.content.*
import android.view.*
import android.graphics.*
class CircleLineMoverView(ctx:Context):View(ctx) {
    val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class Animator(var view:View, var animated:Boolean = false) {
        fun animate(updatecb : () -> Unit) {
            if(animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun start() {
            if(!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if(animated) {
                animated = false
            }
        }
    }
    data class State(var dir:Float = 0f, var j:Int = 0) {
        var scales:Array<Float> = arrayOf(0f, 0f)
        fun update(stopcb : () -> Unit) {
            scales[j] += 0.1f * dir
            if(Math.abs(scales[j] - 1) > 1) {
                scales[j] = 1f
                j++
                if(j == scales.size || j == -1) {
                    j = 0
                    dir = 0f
                    stopcb()
                }
            }
        }
        fun startUpdating(startcb : () -> Unit) {
            if(dir == 0f) {
                dir = 1f
                scales = arrayOf(0f, 0f)
            }
        }
    }
    data class Screen(var x:Float = 0f, var y:Float = 0f) {
        fun update(w:Float) {
            x -= w
        }
        fun drawInScreen(canvas:Canvas, drawcb : (Canvas) -> Unit) {
            canvas.save()
            canvas.translate(x, 0f)
            drawcb(canvas)
            canvas.restore()
        }
        fun getUpdatedX(x : Float):Float = x - this.x
    }
    data class CircleLine(var x:Float, var y:Float, var size:Float) {
        val state = State()
        fun draw(canvas:Canvas, paint:Paint) {
            canvas.save()
            canvas.translate(x,y)
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = size/30
            paint.strokeCap = Paint.Cap.ROUND
            canvas.drawArc(RectF(-size/2, -size/2, size/2, size/2), 0f , 360f * (1 - state.scales[0]), false, paint)
            canvas.drawLine((2 * Math.PI * (size / 2)).toFloat()*(state.scales[1]), 0f, (2 * Math.PI * (size / 2)).toFloat()*(state.scales[0]), 0f, paint)
            canvas.save()
            canvas.translate((2 * Math.PI * (size/2)).toFloat() * state.scales[0],0f)
            canvas.drawArc(RectF(-size/2, -size/2, size/2, size/2), 0f , 360f * state.scales[1], false, paint)
            canvas.restore()
            canvas.restore()
        }
        fun startUpdating(startcb: () -> Unit) {
            state.startUpdating(startcb)
        }
        fun update(stopcb : () -> Unit, updatecb: (Float) -> Unit) {
            updatecb(state.scales[0])
            state.update(stopcb)
        }
    }
}