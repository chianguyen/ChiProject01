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
import kotlinx.android.synthetic.main.fragment_sub_category.*
import kotlinx.android.synthetic.main.fragment_sub_category.view.*
import org.json.JSONObject

import org.mp.chiproject01.R
import org.mp.chiproject01.adapter.SubCatAdapter
import org.mp.chiproject01.modules.SubCategoryItem

/**
 * A simple [Fragment] subclass.
 */


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SubCategoryFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var scatList = ArrayList<SubCategoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("CAT NAME", param1)
        Log.i("CAT ID", param2)

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_sub_category, container, false)

        activity!!.toolbar.title = "Sub-Category"

        view.recyclerViewSubCat.layoutManager = LinearLayoutManager(context)

        //Shared Preferences to get API KEY and USER ID
        var sp: SharedPreferences = this.activity!!.getSharedPreferences("chamber", Context.MODE_PRIVATE)
        var apiKey = sp.getString("user_apikey", "Default")
        var userId = sp.getString("user_id", "Default")


        //Param 2 is the id
        val url = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=$param2&api_key=$apiKey&user_id=$userId"

        //String request for URL
        var stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener {response ->

                var jsonObject = JSONObject(response)
                var jsonArraySCat = jsonObject.getJSONArray("subcategory")

                for(i in 0 until jsonArraySCat.length()){

                    var scats = jsonArraySCat.getJSONObject(i)

                    var scID = scats.getString("scid")
                    var scName = scats.getString("scname")
                    var scDes = scats.getString("scdiscription")
                    var scImg = scats.getString("scimageurl")

                    scatList.add(SubCategoryItem(scID, scName, scDes, scImg))

                    Log.i("INFO", "Sub Success!")
                }

                val adapter = SubCatAdapter(scatList, view.context, param2.toString())
                view.recyclerViewSubCat.adapter = adapter

            },

            Response.ErrorListener {
                Log.e("Sub ERROR", it.message.toString())
                Toast.makeText(view.context, "No data", Toast.LENGTH_SHORT).show()
            }
        )
        Volley.newRequestQueue(view.context).add(stringReq)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
