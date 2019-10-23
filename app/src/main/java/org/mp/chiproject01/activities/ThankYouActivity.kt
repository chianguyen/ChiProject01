package org.mp.chiproject01.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_thank_you.*
import org.mp.chiproject01.R

class ThankYouActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)

        btn_order_again.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var ii = Intent(this@ThankYouActivity, MainActivity::class.java)
                startActivity(ii)
            }
        })

    }
}
