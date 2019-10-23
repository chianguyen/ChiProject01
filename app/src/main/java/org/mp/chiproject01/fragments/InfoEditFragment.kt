package org.mp.chiproject01.fragments


import android.content.Context
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
import kotlinx.android.synthetic.main.fragment_info_edit.*
import kotlinx.android.synthetic.main.fragment_info_edit.view.*

import org.mp.chiproject01.R

/**
 * A simple [Fragment] subclass.
 */
class InfoEditFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info_edit, container, false)

        activity!!.toolbar.title = "Edit Info"

        var sp: SharedPreferences = this.activity!!.getSharedPreferences("chamber", Context.MODE_PRIVATE)
        var editor = sp.edit()

        view.btn_save_edit.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var firstName = new_fname.text.toString()
                var lastName = new_lname.text.toString()
                var email = new_email.text.toString()
                var mobile = new_phone.text.toString()
                var address = new_address.text.toString()

                val url = "http://rjtmobile.com/aamir/e-commerce/android-app/edit_profile.php?" +
                        "fname=" + firstName +
                        "&lname=" + lastName +
                        "&address=" + address +
                        "&email=" + email +
                        "&mobile=" + mobile

                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener<String> {
                        Toast.makeText(view.context, "Successfully Updated", Toast.LENGTH_SHORT).show()

                        editor.putString("fname", firstName)
                        editor.putString("lname", lastName)
                        editor.putString("email", email)
                        editor.putString("mobile", mobile)
                        editor.putString("address", address)

                        editor.commit()

                        fragmentManager!!.popBackStack()
                    },
                    Response.ErrorListener {
                        Toast.makeText(view.context, "Mobile number not found", Toast.LENGTH_SHORT).show()
                        Log.e("error", it.message.toString())
                    }
                )

                Volley.newRequestQueue(view.context).add(stringRequest)


            }
        })

        return view
    }


}
