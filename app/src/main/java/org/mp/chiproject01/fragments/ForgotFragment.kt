package org.mp.chiproject01.fragments


import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_forgot.*
import kotlinx.android.synthetic.main.fragment_forgot.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONArray

import org.mp.chiproject01.R
import org.mp.chiproject01.activities.LoginActivity
import org.mp.chiproject01.activities.MainActivity

/**
 * A simple [Fragment] subclass.
 */
class ForgotFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_forgot, container, false)

        activity!!.toolbar.title = "Forgot Password"

        view.forgot_btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {

                var email = forgot_email.text.toString()

                val url = "http://rjtmobile.com/aamir/e-commerce/android-app/forgot_pass_email.php?email=" + email

                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener {

                        /*   //To show password if it's in the JSON Array
                            var jsonArrayInfo = JSONArray(it)

                            for(i in 0 until(jsonArrayInfo.length())){
                                var userInfo = jsonArrayInfo.getJSONObject(i)
                                var passWord = userInfo.getString("password")
                                forgot_password.setText(passWord)
                            }
                        */
                        Log.i("INFO", it.toString())
                        Toast.makeText(view.context, "Password has been sent to your email!", Toast.LENGTH_SHORT).show()
                    },

                    Response.ErrorListener {}
                )

                Volley.newRequestQueue(view.context).add(stringRequest)
            }
        })

        view.forgot_btn_back.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                (activity as LoginActivity).goToLoginFrag()
            }
        })

        return view
    }


}
