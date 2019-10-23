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
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_act.*
import kotlinx.android.synthetic.main.fragment_main_act.view.*
import org.json.JSONObject

import org.mp.chiproject01.R
import org.mp.chiproject01.activities.MainActivity
import org.mp.chiproject01.adapter.CatAdapter
import org.mp.chiproject01.modules.CategoryItem


class MainActFrag : Fragment() {

    var catList = ArrayList<CategoryItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_main_act, container, false)

        activity!!.toolbar.title = "Categories"

        //Recycler view
        view.recyclerViewCat.layoutManager = LinearLayoutManager(context)

        var sp: SharedPreferences = this.activity!!.getSharedPreferences("chamber", Context.MODE_PRIVATE)

        var iD = sp.getString("user_id", "Default")
        var apiKey = sp.getString("user_apikey", "Default")

        val url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=$apiKey&user_id=$iD"

        Log.i("USER_INFO", url)

        var stringRequest = StringRequest(Request.Method.GET, url,

            Response.Listener {response ->

                var jsonObject = JSONObject(response)

                var jsonArrayCats =jsonObject.getJSONArray("category")

                for(i in 0 until jsonArrayCats.length()){

                    var cats =jsonArrayCats.getJSONObject(i)

                    var cID = cats.getString("cid")
                    var cName = cats.getString("cname")
                    var cDes = cats.getString("cdiscription")
                    var cImg = cats.getString("cimagerl")

                    catList.add(CategoryItem(cID, cName, cDes, cImg))

                    Log.i("INFO", "Cat Success!")

                }

                val adapter = CatAdapter(catList, view.context)
                view.recyclerViewCat.adapter = adapter

            },
            Response.ErrorListener {
                Log.i("INFO", "Cat Failed!")
                Toast.makeText(view.context, "invalid api key or user id", Toast.LENGTH_SHORT).show()
            }

        )

        Volley.newRequestQueue(view.context).add(stringRequest)

        return view
    }

}


