package ir.unary.zhin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_dariush.setOnClickListener(this)
        btn_behzad.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            btn_dariush.id -> startActivity(Intent(this@MainActivity, DActivity::class.java))

            btn_behzad.id -> startActivity(Intent(this@MainActivity, BActivity::class.java))
            else -> return
        }
    }

}
