package ir.unary.zhin

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.content_layout.*

class TestActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {
    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        when {
            verticalOffset == 0 -> {
                Log.e("offset", "expand")
                v_shadow.visibility= View.VISIBLE
            }
            Math.abs(verticalOffset) == app_bar?.totalScrollRange -> {
                v_shadow.visibility= View.GONE
                Log.e("offset", "collapsed")
            }
            else -> {
                v_shadow.visibility= View.VISIBLE
                Log.e("offset", "someWhere!")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        setSupportActionBar(toolbar1)

        list1.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        list1.addItemDecoration(PaddingItemDecoration(20, 30))
        list1.adapter = MainAdapter(this, 1)


        tl1.addTab(tl1.newTab().setText("HOME"))
        tl1.addTab(tl1.newTab().setText("GAMES"))

        tl2.addTab(tl2.newTab().setText("for you"))
        tl2.addTab(tl2.newTab().setText("top chart"))
        tl2.addTab(tl2.newTab().setText("categories"))
        tl2.addTab(tl2.newTab().setText("editors choice"))
        tl2.addTab(tl2.newTab().setText("x"))
        tl2.addTab(tl2.newTab().setText("y"))

        app_bar.addOnOffsetChangedListener(this)

    }

}
