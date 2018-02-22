package ui.anwesome.com.kotlincirclelinemoverview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.circlelinemoverview.CircleLineMoverView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CircleLineMoverView.create(this)
    }
}
