package org.mp.chiproject01.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_cart.*
import kotlinx.android.synthetic.main.frag_cart.view.*
import org.mp.chiproject01.R
import org.mp.chiproject01.activities.AddressActivity
import org.mp.chiproject01.activities.GPSActivity
import org.mp.chiproject01.adapter.CartAdapter
import org.mp.chiproject01.database.MyDbHelper
import org.mp.chiproject01.modules.ShopItem

class CartFragment : Fragment() {

//    var shopItem: ArrayList<ShopItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.frag_cart, container, false)

        activity!!.toolbar.title = "Cart"

        var db = MyDbHelper(view.context)
        var prodList = db.viewAllOrder()

        view.recyclerViewCart.layoutManager = LinearLayoutManager(context)

        view.recyclerViewCart.adapter = CartAdapter(prodList, view.context)

        // This is returning kotlin.Unit
        view.total_price.text = "Total: " + db.getTotalPrice().toString()

        //On Delete All button:
        view.btn_cart_delete.setOnClickListener {
            db.deleteAll()              //Delete items in database
            prodList.clear()            //Clear the prodList
            view.total_price.text = "Total: " + db.getTotalPrice().toString()    //Update the total price text
            view.recyclerViewCart.adapter = CartAdapter(prodList, view.context)     //Update the view again
        }

        view.btn_cart_checkout.setOnClickListener {
            var ii = Intent(context, GPSActivity::class.java)
            startActivity(ii)
        }


        return view
    }
/*
    fun cartUpdate(view: View) {
        var db = MyDbHelper(view!!.context)
        var prodList = db.viewAllOrder()
        view.total_price.text = "Total: " + db.getTotalPrice().toString()
        view.recyclerViewCart.adapter = CartAdapter(prodList, view.context)
    }*/

}