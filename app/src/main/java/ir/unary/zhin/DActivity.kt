package ir.unary.zhin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_d.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.filter.*


class DActivity : AppCompatActivity(), View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {


    override fun onClick(p0: View?) {
        toggleBottomSheet()
    }

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var mBottomSheetCallback: BottomSheetBehavior.BottomSheetCallback
    private lateinit var mBottomSheetAdapter: BottomSheetAdapter
    private lateinit var mMainAdapter: MainAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)
        setSupportActionBar(toolbar)
        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)
        mBottomSheetBehavior.setBottomSheetCallback(setCallback())
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        bottomNavigation.setOnNavigationItemReselectedListener(this)


        rv_main.layoutManager = LinearLayoutManager(this)
        val decor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        rv_main.addItemDecoration(decor)
        mMainAdapter = MainAdapter(this, 0)
        rv_main.adapter = mMainAdapter


        rv_bottomList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val a = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        a.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        rv_bottomList.addItemDecoration(a)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        toggleBottomSheet()
        when (item.itemId) {
            R.id.menu_nearby -> {
                mBottomSheetAdapter = BottomSheetAdapter(this@DActivity, 0)
                rv_bottomList.adapter = mBottomSheetAdapter
                return true
            }
            R.id.menu_popular -> {
                mBottomSheetAdapter = BottomSheetAdapter(this@DActivity, 1)
                rv_bottomList.adapter = mBottomSheetAdapter
                return true
            }

            R.id.menu_new -> {
                mBottomSheetAdapter = BottomSheetAdapter(this@DActivity, 2)
                rv_bottomList.adapter = mBottomSheetAdapter
                return true
            }
        }
        return false
    }


    override fun onNavigationItemReselected(item: MenuItem) {
        toggleBottomSheet()
        x()
        return
    }

    fun x() {
        when (bottomNavigation.selectedItemId) {
            R.id.menu_nearby -> {
                mBottomSheetAdapter = BottomSheetAdapter(this@DActivity, 0)
                rv_bottomList.adapter = mBottomSheetAdapter
            }
            R.id.menu_popular -> {
                mBottomSheetAdapter = BottomSheetAdapter(this@DActivity, 1)
                rv_bottomList.adapter = mBottomSheetAdapter
            }

            R.id.menu_new -> {
                mBottomSheetAdapter = BottomSheetAdapter(this@DActivity, 2)
                rv_bottomList.adapter = mBottomSheetAdapter
            }
        }
    }

    fun setCallback(): BottomSheetBehavior.BottomSheetCallback {
        mBottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.e("onSlide", "offset:" + slideOffset)
            }
        }
        return mBottomSheetCallback
    }


    private fun toggleBottomSheet() {
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            //mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

}
