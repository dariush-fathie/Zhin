package ir.unary.zhin

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

class CAppBar : AppBarLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e("dispatch", "${ev.action} + ")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e("onTouch", "${event.action} + ")
        return super.onTouchEvent(event)
    }
}
