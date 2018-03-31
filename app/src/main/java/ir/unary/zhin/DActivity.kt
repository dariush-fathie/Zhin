package ir.unary.zhin

import `in`.galaxyofandroid.spinerdialog.OnSpinerItemClick
import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_d.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_layout.*


class DActivity : AppCompatActivity(), View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener
        , BottomNavigationView.OnNavigationItemReselectedListener, AppBarLayout.OnOffsetChangedListener {


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        Log.e("offset", "$verticalOffset")
        if (Math.abs(verticalOffset) == appBarLayout?.totalScrollRange) {
            // fully collapsed
            iv_moreToolbar.visibility = View.VISIBLE
        }/* else if (verticalOffset == 0) {
            // fully expanded
        }*/ else {
            // not closed nor expanded
            iv_moreToolbar.visibility = View.GONE
        }
    }


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
            R.id.tv_filter -> openFilterDialog()
            R.id.tv_ostan -> openOstanDialog()
            R.id.tv_city -> openCityDialog()
            R.id.iv_moreToolbar -> openPopUpMenu()
            R.id.iv_sort -> sort()
        }
    }

    private fun sort() {
        val mBuilder = AlertDialog.Builder(this@DActivity)
        val charSequences = arrayOfNulls<CharSequence>(4)
        charSequences[0] = "بر اساس بازدید"
        charSequences[1] = "بر اساس تاریخ"
        charSequences[2] = "بر اساس نزدیکی"
        charSequences[3] = "بر اساس دسته"
        mBuilder.setItems(charSequences) { dialogInterface, i ->
            Toast.makeText(applicationContext, "مرتب سازی بر اساس $i", Toast.LENGTH_SHORT).show()
        }
        val t = layoutInflater.inflate(R.layout.title_cutom, null)
        t.findViewById<TextView>(R.id.tv_title_custom).text = "مرتب سازی"
        mBuilder.setCustomTitle(t)
        mBuilder.show()
    }


    private fun openPopUpMenu() {
        val mPopupMenu = PopupMenu(this, iv_moreToolbar)
        mPopupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_sort -> Toast.makeText(applicationContext , "مرتب سازی" , Toast.LENGTH_SHORT).show()
                R.id.menu_filter -> Toast.makeText(applicationContext , "دسته بندی" , Toast.LENGTH_SHORT).show()
                R.id.menu_ostan -> Toast.makeText(applicationContext , "استان" , Toast.LENGTH_SHORT).show()
                R.id.menu_city -> Toast.makeText(applicationContext , "شهر" , Toast.LENGTH_SHORT).show()
            }
            false
        })
        mPopupMenu.inflate(R.menu.popup_menu)
        mPopupMenu.show()
    }


    private val items = ArrayList<String>()
    lateinit var spinnerDialog: SpinnerDialog;

    private fun openCityDialog() {
        items.clear()
        items.add("شهر #1")
        items.add("شهر #2")
        items.add("شهر #3")
        items.add("شهر #4")
        items.add("شهر #5")
        items.add("شهر #6")
        items.add("شهر #7")
        items.add("شهر #8")
        items.add("شهر #9")
        items.add("شهر #10")

        spinnerDialog = SpinnerDialog(this@DActivity, items, "انتخاب شهر ", "بستن")
        spinnerDialog.bindOnSpinerListener(OnSpinerItemClick { item, position ->
            Toast.makeText(this@DActivity, "$item  $position", Toast.LENGTH_SHORT).show()
            tv_city.text = "$item"
        })
        spinnerDialog.showSpinerDialog()
    }

    private fun openOstanDialog() {
        items.clear()
        items.add("آذربایجان غربی")
        items.add("آذربایجان غربی")
        items.add("اردبیل")
        items.add("اصفهان")
        items.add("البرز")
        items.add("ایلام")
        items.add("بوشهر")
        items.add("تهران")
        items.add("چهارمحال بخیاری")
        items.add("خراسان جنوبی")
        items.add("خراسان رضوی")
        items.add("خراسان شمالی")
        items.add("خوزستان")
        items.add("زنجان")
        items.add("سمنان")
        items.add("سیستان و بلوچستان")
        items.add("فارس")
        items.add("قزوین")
        items.add("قم")
        items.add("کردستان")
        items.add("کرمان")
        items.add("کرمانشاه")
        items.add("کهکیلویه و بویراحمد")
        items.add("گلستان")
        items.add("لرستان")
        items.add("مرکزی")
        items.add("هرمزگان")
        items.add("همدان")
        items.add("یزد")
        spinnerDialog = SpinnerDialog(this@DActivity, items, "انتخاب استان", "بستن")
        spinnerDialog.bindOnSpinerListener(OnSpinerItemClick { item, position ->
            Toast.makeText(this@DActivity, "$item  $position", Toast.LENGTH_SHORT).show()
            tv_ostan.text = "$item"
        })
        spinnerDialog.showSpinerDialog()
    }

    private fun openFilterDialog() {
        val filter = layoutInflater.inflate(R.layout.filter, null)
        val mBuilder = AlertDialog.Builder(this@DActivity)
        mBuilder.setView(filter)
        mBuilder.setPositiveButton("فیلترکردن") { _, i ->
            filter1()
        }
        mBuilder.setNegativeButton("ریست") { _, i ->
            reset()
        }
        mBuilder.setNeutralButton("بستن") { dialogInterface, i ->
            dialogInterface.cancel()
        }
        mBuilder.setTitle("انتخاب دسته بندی ")
        val v = layoutInflater.inflate(R.layout.title_cutom, null)
        v.findViewById<TextView>(R.id.tv_title_custom).text = "انتخاب دسته بندی "
        mBuilder.setCustomTitle(v)
        mBuilder.show()
    }


    fun filter1() {

    }

    fun reset() {

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
            tv_toolbar_title.visibility = View.GONE
            iv_moreToolbar.visibility = View.GONE
            et_search.visibility = View.VISIBLE
            iv_deleteSearch.visibility = View.VISIBLE
            iv_search.setImageResource(R.drawable.ic_search_black_36dp)
        }
    }

    private fun disableSearchLayout() {
        iv_moreToolbar.visibility = View.VISIBLE
        tv_toolbar_title.visibility = View.VISIBLE
        et_search.visibility = View.GONE
        iv_deleteSearch.visibility = View.GONE
        iv_search.setImageResource(R.drawable.ic_search_white)
    }

    fun search() {
        if (et_search.text.toString() != "") {
            hideKeyboard()
        }
    }


    private fun hideKeyboard() {
        val i: InputMethodManager = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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

        appBarLayout.addOnOffsetChangedListener(this)

        rv_main.layoutManager = LinearLayoutManager(this)

        val decor = PaddingItemDecoration(20, 30)
        rv_main.addItemDecoration(decor)

        mMainAdapter = MainAdapter(this, 0)
        rv_main.adapter = mMainAdapter

        rv_bottomList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_bottomList.addItemDecoration(decor)

        iv_search.setOnClickListener(this)
        iv_deleteSearch.setOnClickListener(this)
        iv_bigLayoutManager.setOnClickListener(this)
        iv_mediumLayoutManager.setOnClickListener(this)
        iv_smallLayoutManager.setOnClickListener(this)
        tv_filter.setOnClickListener(this)
        iv_sort.setOnClickListener(this)
        tv_ostan.setOnClickListener(this)
        tv_city.setOnClickListener(this)
        iv_moreToolbar.setOnClickListener(this)

        et_search.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search()
                Log.e("seatch", "sdfs")
                return@OnEditorActionListener true
            }
            false
        })

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
