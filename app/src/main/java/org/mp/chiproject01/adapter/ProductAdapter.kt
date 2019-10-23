package org.mp.chiproject01.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mp.chiproject01.R
import org.mp.chiproject01.database.MyDbHelper
import org.mp.chiproject01.modules.ProductItem

class ProductAdapter(var productList: ArrayList<ProductItem>, val context: Context):
RecyclerView.Adapter<ProductAdapter.ViewHolder4>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder4 {
        var v : View = LayoutInflater.from(context).inflate(R.layout.product_unit, parent, false)
        return ViewHolder4(v)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder4, position: Int) {
        var productItem = productList[position]
        holder.pName.text = productItem.pName
        holder.pDes.text = productItem.pDes
        holder.pPrice.text = productItem.pPrice
        Glide.with(context).load(productItem.pImg).into(holder.pImg)
    }


    inner class ViewHolder4(view: View): RecyclerView.ViewHolder(view){
        var pName = view.findViewById<TextView>(R.id.text_pname)
        var pDes = view.findViewById<TextView>(R.id.text_pdes)
        var pPrice = view.findViewById<TextView>(R.id.text_pprice)
        var pImg = view.findViewById<ImageView>(R.id.img_product)

        init {
            view.setOnClickListener {
                //SQLite DB, product as parameter
                //class SQLite
                val db = MyDbHelper(view.context)

                var prodItem  = productList[adapterPosition]

                var p_id = prodItem.pId
                var p_name = prodItem.pName
                var p_des = prodItem.pDes
                var p_price = prodItem.pPrice
                var p_img = prodItem.pImg

                var response = db.addProduct(ProductItem(p_id, p_name, p_price, p_des, ""))

                Toast.makeText(view.context, "Response id: $response", Toast.LENGTH_SHORT ).show()

            }
        }
    }
}


