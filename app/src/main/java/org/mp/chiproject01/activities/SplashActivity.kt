package org.mp.chiproject01.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import org.mp.chiproject01.R

class SplashActivity : AppCompatActivity() {

    var myAni: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        myAni = AnimationUtils.loadAnimation(this, R.anim.myanim)
        loading_img.startAnimation(myAni)

        var thread = Thread {
            kotlin.run {
                Thread.sleep(3000)
            }

            var i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        thread.start()
    }

}
