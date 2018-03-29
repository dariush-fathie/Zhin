package ir.unary.zhin

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_d.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_layout.*


class DActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        toggleBottomSheet()
    }

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var mBottomSheetCallback: BottomSheetBehavior.BottomSheetCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)
        setSupportActionBar(toolbar)

        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)
        mBottomSheetBehavior.setBottomSheetCallback(setCallback())
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        btn_more.setOnClickListener(this)

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
                Log.e("onSlide" , "offset:" + slideOffset)
            }
        }
        return mBottomSheetCallback
    }


    private fun toggleBottomSheet() {
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

}
