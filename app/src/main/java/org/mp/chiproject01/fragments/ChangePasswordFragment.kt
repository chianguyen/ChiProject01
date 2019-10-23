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
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONArray

import org.mp.chiproject01.R
import org.mp.chiproject01.activities.LoginActivity
import org.mp.chiproject01.activities.MainActivity

/**
 * A simple [Fragment] subclass.
 */
class ChangePasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_change_password, container, false)

        activity!!.toolbar.title = "Change Password"

        view.btn_new_pwd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {

                var mobile = txt_mobile.text.toString()
                var oldPwd = current_pwd.text.toString()
                var newPwd = new_password.text.toString()

                val url = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reset_pass.php?&mobile=" + mobile +
                "&password=" + oldPwd + "&newpassword=" + newPwd

                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener {response ->
                        Log.i("Info", response.toString())
                        Toast.makeText(view.context, "Password changed", Toast.LENGTH_SHORT).show()
                        var i = Intent(view.context, LoginActivity::class.java)
                        startActivity(i)

                    },

                    Response.ErrorListener {
                        Log.i("Info", it.toString())
                        Toast.makeText(view.context, "Password change failed", Toast.LENGTH_SHORT).show()
                    }
                )

                Volley.newRequestQueue(view.context).add(stringRequest)


            }
        })


        return view
    }


}
