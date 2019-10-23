package org.mp.chiproject01.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mp.chiproject01.R
import org.mp.chiproject01.activities.MainActivity
import org.mp.chiproject01.fragments.ProductFragment
import org.mp.chiproject01.modules.SubCategoryItem

class SubCatAdapter(var scatList : ArrayList<SubCategoryItem>, val context: Context, var catID: String): RecyclerView.Adapter<SubCatAdapter.ViewHolder3>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder3 {
        var v: View = LayoutInflater.from(context).inflate(R.layout.scat_unit, parent, false)
        return ViewHolder3(v)
    }

    override fun getItemCount(): Int {
        return scatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder3, position: Int) {
        val scatItem = scatList[position]
        holder.scatName.text = scatItem.scname
        holder.scatDes.text = scatItem.scdiscription
        Glide.with(context).load(scatItem.scimageurl).into(holder.scatImg)

    }

    inner class ViewHolder3(view: View): RecyclerView.ViewHolder(view){

        var scatName = view.findViewById<TextView>(R.id.scat_name)
        var scatDes = view.findViewById<TextView>(R.id.scat_des)
        var scatImg = view.findViewById<ImageView>(R.id.scat_img)

        init {
            view.setOnClickListener {

                var scategoryItem = scatList[adapterPosition]
                Log.i("CAT ITEM?", scategoryItem.scname)



                //Pass name and ID of the category to subcategory as parameters
                var prodFrag = ProductFragment.newInstance(catID, scategoryItem.scid)

                (context as MainActivity).supportFragmentManager!!.beginTransaction().replace(R.id.frameLayout, prodFrag).addToBackStack(null).commit()
            }
        }

    }


}