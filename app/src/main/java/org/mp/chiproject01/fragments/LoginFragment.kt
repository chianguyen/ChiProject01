package org.mp.chiproject01.fragments


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.json.JSONArray
import org.json.JSONObject

import org.mp.chiproject01.R
import org.mp.chiproject01.activities.LoginActivity
import org.mp.chiproject01.activities.MainActivity
import org.mp.chiproject01.modules.User

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_login, container, false)

        var sp: SharedPreferences = this.activity!!.getSharedPreferences("chamber", Context.MODE_PRIVATE)

        var s_mobile = sp.getString("user_mobile", "")
        var s_password = sp.getString("user_password", "")

        view.login_mobile.setText(s_mobile)
        view.login_password.setText(s_password)

        view.login_btn_login.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {

                var mobile = login_mobile.text.toString()
                var password = login_password.text.toString()

                var editor = sp.edit()

                editor.putString("user_mobile", mobile)
                editor.putString("user_password", password)

                val url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?" +
                        "mobile=" + mobile + "&password=" + password

                val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener {response ->

                        var jsonArrayInfo = JSONArray(response)

                        for(i in 0 until(jsonArrayInfo.length())){

                            var userInfo = jsonArrayInfo.getJSONObject(i)

                            var msg = userInfo.getString("msg")
                            var fName = userInfo.getString("firstname")
                            var lName = userInfo.getString("lastname")
                            var id = userInfo.getString("id")
                            var eMail = userInfo.getString("email")
                            var moBile = userInfo.getString("mobile")
                            var apiKey = userInfo.getString("appapikey ")

                            editor.putString("user_fname", fName)
                            editor.putString("user_lname", lName)
                            editor.putString("user_id", id)
                            editor.putString("user_email", eMail)
                            editor.putString("user_mobile", moBile)
                            editor.putString("user_apikey", apiKey)

                            Log.i("INFO", response.toString())

                            Toast.makeText(view.context, "Welcome $fName", Toast.LENGTH_SHORT).show()

                        }

                        editor.commit()

                        var i = Intent(view.context, MainActivity::class.java)
                        startActivity(i)

                    },
                    Response.ErrorListener {
                        Toast.makeText(view.context, "Mobile not registered", Toast.LENGTH_SHORT).show()
                    }
                )

                Volley.newRequestQueue(view.context).add(stringRequest)
            }
        })

        view.login_btn_forgot.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                (activity as LoginActivity).goToForgetFrag()
            }
        })


        return view

    }
}