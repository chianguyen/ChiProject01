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
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_product.view.*
import org.json.JSONObject

import org.mp.chiproject01.R
import org.mp.chiproject01.adapter.ProductAdapter
import org.mp.chiproject01.modules.ProductItem

/**
 * A simple [Fragment] subclass.
 */

private const val ARG_PARAM1 = "param1" //cat ID
private const val ARG_PARAM2 = "param2" //subcat ID

class ProductFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var productList = ArrayList<ProductItem>()

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

        Log.i("CAT ID", param1)
        Log.i("SUBCAT ID", param2)

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_product, container, false)

        view.recyclerViewProduct.layoutManager = LinearLayoutManager(context)

        activity!!.toolbar.title = "Products"

        var sp: SharedPreferences = this.activity!!.getSharedPreferences("chamber", Context.MODE_PRIVATE)

        var apiKey = sp.getString("user_apikey", "Default")
        var userID = sp.getString("user_id", "Default")

        val url = "http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=$param1&scid=$param2&api_key=$apiKey&user_id=$userID"

        Log.i("PROD URL", url)

        var stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener {


                var jsonObject = JSONObject(it)
                var jsonArrayProduct = jsonObject.getJSONArray("products")

                for(i in 0 until jsonArrayProduct.length()){

                    var products = jsonArrayProduct.getJSONObject(i)

                    var proId = products.getString("id")
                    var proName = products.getString("pname")
                //      var proQuan = products.getString("quantity")
                    var proPrice = products.getString("prize")
                    var proDes = products.getString("discription")
                    var proImg = products.getString("image")

                    productList.add(ProductItem(proId, proName, proPrice, proDes, proImg))

                }

                val adapter = ProductAdapter(productList, view.context)
                view.recyclerViewProduct.adapter = adapter

            },

            Response.ErrorListener {
                Log.e("PROD ERROR", it.message.toString())
                Toast.makeText(view.context, "No data", Toast.LENGTH_SHORT).show()
            }
        )

        Volley.newRequestQueue(view.context).add(stringReq)

        return view
    }

    //Intance of Product object
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
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}
