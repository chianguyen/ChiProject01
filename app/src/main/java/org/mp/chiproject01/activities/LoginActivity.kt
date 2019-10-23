package org.mp.chiproject01.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.mp.chiproject01.fragments.LoginBaseFragment
import org.mp.chiproject01.R
import org.mp.chiproject01.fragments.ForgotFragment
import org.mp.chiproject01.fragments.LoginFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginFrag = LoginBaseFragment()
        supportFragmentManager.beginTransaction().replace(R.id.login_act, loginFrag).commit()

    }

    //go to Login Fragment
    fun goToLoginFrag(){
        supportFragmentManager!!.beginTransaction().replace(R.id.login_act, LoginFragment()).addToBackStack(null).commit()
    }

    //go to Forget Password Fragment
    fun goToForgetFrag(){
        supportFragmentManager!!.beginTransaction().replace(R.id.login_act, ForgotFragment()).addToBackStack(null).commit()
    }

}
