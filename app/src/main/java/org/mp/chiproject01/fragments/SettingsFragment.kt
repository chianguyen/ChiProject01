package org.mp.chiproject01.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_settings.view.*
import org.mp.chiproject01.R
import org.mp.chiproject01.activities.LoginActivity
import org.mp.chiproject01.activities.MainActivity

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.frag_settings, container, false)

        activity!!.toolbar.title = "Settings"

        view.btn_logout.setOnClickListener(object:View.OnClickListener{

            override fun onClick(v: View?) {
                fragmentManager!!.popBackStack()
                var i = Intent(view.context, LoginActivity::class.java)
                startActivity(i)
            }

        })

        return view

        //toolbar on top
        // title
        //back button on toolbar


    }

}