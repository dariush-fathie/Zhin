package ir.unary.zhin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        supportActionBar?.hide()

        //startActivity(Intent(this, TestActivity::class.java))

        var i = 0
        val handler = Handler()
        handler.postDelayed({
            runOnUiThread {
                startActivity(Intent(this, DActivity::class.java))
                finish()
            }
        }, 100)

        val h = Handler()
        h.postAtTime({
            runOnUiThread {
                progress.progress = (100 / (i + 1)).toFloat()
                i++
            }
        }, 500)


    }


}
