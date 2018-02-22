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
    }
}