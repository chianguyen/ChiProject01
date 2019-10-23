package org.mp.chiproject01.fragments


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import org.json.JSONObject

import org.mp.chiproject01.R
import org.mp.chiproject01.activities.LoginActivity
import java.lang.reflect.Method
import java.util.prefs.Preferences
import java.lang.Exception as Exception1

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)

     //   activity!!.toolbar.title = "Register"

        view.reg_btn.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {

                //register input
                var firstName = reg_firstName.text.toString()
                var lastName = reg_lastName.text.toString()
                var email = reg_email.text.toString()
                var mobile = reg_mobile.text.toString()
                var password = reg_password.text.toString()
                var address = reg_address.text.toString()

                //API source
                val url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?" +
                        "fname=" + firstName +
                        "&lname=" + lastName +
                        "&address=" + address +
                        "&email=" + email +
                        "&mobile=" + mobile +
                        "&password=" + password

                val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> {
                        Toast.makeText(view.context, "Successfully Registered", Toast.LENGTH_SHORT).show()
                        fragmentManager!!.popBackStack()
                       (activity as LoginActivity).goToLoginFrag()
                    },
                    Response.ErrorListener {
                        Log.e("error", it.message)
                    }
                )

                Volley.newRequestQueue(view.context).add(stringRequest)
            }
        })
            return view
    }


}
