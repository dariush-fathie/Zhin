package ir.unary.zhin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_b.*

class BActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        supportActionBar?.hide()
        login_tv.setOnClickListener(this)
        register_tv.setOnClickListener(this)
    }
}
