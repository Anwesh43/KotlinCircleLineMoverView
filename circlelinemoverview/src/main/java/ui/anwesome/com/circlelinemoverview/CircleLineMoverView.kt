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
}