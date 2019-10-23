package org.mp.chiproject01.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frag_cart.view.*
import org.mp.chiproject01.R
import org.mp.chiproject01.database.MyDbHelper
import org.mp.chiproject01.modules.ProductItem

class CartAdapter(var cartList : ArrayList<ProductItem>, var context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder5>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder5 {
        var view = LayoutInflater.from(context).inflate(R.layout.cart_unit, parent, false)
        return ViewHolder5(view)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: ViewHolder5, position: Int) {
        var cart_item = cartList[position]
        holder.itemName.text = cart_item.pName
        holder.itemPrice.text = cart_item.pPrice
        holder.itemDel.setOnClickListener {
            val db = MyDbHelper(context)
            var itemID = cart_item.pId.toInt()
            val response = db.deleteProduct(itemID)

            Toast.makeText(context, "Response Sucess $response", Toast.LENGTH_SHORT).show()
        }
  //    Glide.with(context).load(cart_item.pImg).into(holder.itemImg)
    }


    inner class ViewHolder5(view: View): RecyclerView.ViewHolder(view){
        var itemName = view.findViewById<TextView>(R.id.text_cart_name)
        var itemPrice = view.findViewById<TextView>(R.id.text_cart_price)
        var itemDel = view.findViewById<Button>(R.id.btn_item_delete)

/*        init {

            view.btn_cart_delete.setOnClickListener {
                val db = MyDbHelper(context)

                var cartitem = cartList[adapterPosition]

                var itemID = cartitem.pId.toInt()
                val response = db.deleteProduct(itemID)

                Toast.makeText(context, "Response Sucess $response", Toast.LENGTH_SHORT).show()

            }

        }*/


    }
}