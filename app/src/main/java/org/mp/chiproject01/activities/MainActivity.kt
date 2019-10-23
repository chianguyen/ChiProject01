package org.mp.chiproject01.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.mp.chiproject01.R
import org.mp.chiproject01.fragments.*

//Thanks to
//https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305
//


class MainActivity : AppCompatActivity() {

    //Navigation Bar
    private val onNaviItemSelected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){

            R.id.navigation_profile ->{
                var profFrag = ProfileFragment()
                supportFragmentManager!!.popBackStack()
                supportFragmentManager!!.beginTransaction().replace(R.id.frameLayout, profFrag).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_cart -> {
                val cartFrag = CartFragment()
                supportFragmentManager!!.popBackStack()
                supportFragmentManager!!.beginTransaction().replace(R.id.frameLayout, cartFrag).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_settings -> {
                val setsFrag = SettingsFragment()
                supportFragmentManager!!.popBackStack()
                supportFragmentManager!!.beginTransaction().replace(R.id.frameLayout, setsFrag).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }

        }

        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent()

        var fName = intent.getStringExtra("fName")
        var lName = intent.getStringExtra("lName")
        var id = intent.getStringExtra("id")
        var eMail = intent.getStringExtra("eMail")
        var moBile = intent.getStringExtra("moBile")

        var bundle = Bundle()
        bundle.putString("fName", fName)
        bundle.putString("lName", lName)
        bundle.putString("id", id)
        bundle.putString("eMail", eMail)
        bundle.putString("moBile", moBile)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.navView)
        bottomNavigation.setOnNavigationItemSelectedListener (onNaviItemSelected)

        val mainFrag = MainActFrag()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, mainFrag).commit()

        var mToolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        var actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)


        mToolbar.setNavigationOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                supportFragmentManager.popBackStack()
            }
        })

    }

}


