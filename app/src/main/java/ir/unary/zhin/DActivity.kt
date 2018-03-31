package ir.unary.zhin

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_d.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_layout.*


class DActivity : AppCompatActivity(), View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {


    override fun onClick(p: View?) {
        when (p?.id) {
            R.id.iv_search -> {
                enableSearchLayout()
                search()
            }
            R.id.iv_deleteSearch -> {
                disableSearchLayout()
                clearSearch()
            }
            R.id.iv_bigLayoutManager -> changeLayoutManager(0)
            R.id.iv_mediumLayoutManager -> changeLayoutManager(1)
            R.id.iv_smallLayoutManager -> changeLayoutManager(2)
            R.id.cv_filer -> openFilterDialog()
        }
    }


    private fun openFilterDialog() {

    }


    private fun changeLayoutManager(type: Int) {
        val a: RecyclerView.LayoutManager
        when (type) {
            0 -> {
                a = LinearLayoutManager(this@DActivity)
                mMainAdapter = MainAdapter(this@DActivity, 0)
            }
            1 -> {
                a = LinearLayoutManager(this@DActivity)
                mMainAdapter = MainAdapter(this@DActivity, 1)
            }
            else -> {
                a = GridLayoutManager(this@DActivity, 2)
                mMainAdapter = MainAdapter(this@DActivity, 2)
            }
        }
        rv_main.layoutManager = a
        rv_main.adapter = mMainAdapter
    }

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var mBottomSheetCallback: BottomSheetBehavior.BottomSheetCallback
    private lateinit var mBottomSheetAdapter: BottomSheetAdapter
    private lateinit var mMainAdapter: MainAdapter


    private fun enableSearchLayout() {
        if (et_search.visibility != View.VISIBLE) {
            et_search.visibility = View.VISIBLE
            iv_deleteSearch.visibility = View.VISIBLE
            iv_search.setImageResource(R.drawable.ic_search_black_36dp)
        }
    }

    private fun disableSearchLayout() {
        et_search.visibility = View.GONE
        iv_deleteSearch.visibility = View.GONE
        iv_search.setImageResource(R.drawable.ic_search_white)
    }

    fun search() {
        if (et_search.text.toString() != "") {
            hideKeyboard()
        }
    }


    private fun hideKeyboard(){
        val i:InputMethodManager = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        i.hideSoftInputFromInputMethod(et_search.windowToken, 0)
        et_search.clearFocus()
    }

    private fun clearSearch() {
        et_search.text.clear()
    }

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


        iv_search.setOnClickListener(this)
        iv_deleteSearch.setOnClickListener(this)
        iv_bigLayoutManager.setOnClickListener(this)
        iv_mediumLayoutManager.setOnClickListener(this)
        iv_smallLayoutManager.setOnClickListener(this)
        cv_filer.setOnClickListener(this)

        et_search.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search()
                Log.e("seatch","sdfs")
                return@OnEditorActionListener true
            }
            false
        })

    }


    fun showDialog() {

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
