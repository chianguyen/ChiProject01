package org.mp.chiproject01.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login_base.view.*
import org.mp.chiproject01.R

/**
 * A simple [Fragment] subclass.
 */
class LoginBaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login_base, container, false)

        view.login_btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var fragLogWithUs = LoginFragment()
                fragmentManager!!.beginTransaction().replace(R.id.login_act, fragLogWithUs).addToBackStack(null).commit()
            }
        })

        view.login_btn2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var registerFrag = RegisterFragment()
                fragmentManager!!.beginTransaction().replace(R.id.login_act, registerFrag).addToBackStack(null).commit()
            }
        })

        return view
    }


}
