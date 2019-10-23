package org.mp.chiproject01.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_address.*
import org.mp.chiproject01.R

class AddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        address_btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var i = Intent(this@AddressActivity, MainActivity::class.java)
                startActivity(i)
            }
        })

        gps_btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var ii = Intent(this@AddressActivity, GPSActivity::class.java)
                startActivity(ii)
            }
        })

    }
}
