package org.mp.chiproject01.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.frag_profile.view.*
import org.mp.chiproject01.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.frag_profile, container, false)

        activity!!.toolbar.title = "Profile"

        var sp: SharedPreferences = this.activity!!.getSharedPreferences("chamber", Context.MODE_PRIVATE)

        var fName = sp.getString("fname", "Default")
        var lName = sp.getString("lname", "Default")
        var eMail = sp.getString("email", "Default")
        var moBile = sp.getString("mobile", "Default")
        var address = sp.getString("address", "Default")

        view.profile_fname.setText(fName)
        view.profile_lname.setText(lName)
        view.profile_email.setText(eMail)
        view.profile_mobile.setText(moBile)
        view.profile_address.setText(address)

        view.btn_profile_edit.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                fragmentManager!!.beginTransaction().replace(R.id.frameLayout, InfoEditFragment()).addToBackStack(null).commit()
            }
        })

        view.btn_change_pwd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                fragmentManager!!.beginTransaction().replace(R.id.frameLayout, ChangePasswordFragment()).addToBackStack(null).commit()
            }
        })

        return view

    }

}