package org.mp.chiproject01.adapter

import android.content.Context
import android.os.Bundle
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
import org.mp.chiproject01.fragments.SubCategoryFragment
import org.mp.chiproject01.modules.CategoryItem

class CatAdapter(var catList: ArrayList<CategoryItem>, val context: Context): RecyclerView.Adapter<CatAdapter.ViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        var v: View = LayoutInflater.from(context).inflate(R.layout.cat_unit, parent, false)
        return ViewHolder2(v)
    }

    override fun getItemCount( ): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        val catItem = catList[position]
        Glide.with(context).load(catItem.cImg).into(holder.catImg)
        holder.catName.text = catItem.cName
    //    holder.catDes.text = catItem.cDes
    }

    inner class ViewHolder2(view: View): RecyclerView.ViewHolder(view){
        var catName = view.findViewById<TextView>(R.id.cat_name)
      //  var catDes = view.findViewById<TextView>(R.id.cat_des)
        var catImg = view.findViewById<ImageView>(R.id.cat_img)


        init {
            view.setOnClickListener {
                var categoryItem = catList[adapterPosition]
                Log.i("CAT ITEM?", categoryItem.cName)

                //Pass name and ID of the category to subcategory as parameters
                var subCatFrag = SubCategoryFragment.newInstance(categoryItem.cName, categoryItem.cID)
                (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.frameLayout, subCatFrag).addToBackStack(null).commit()

            }
        }
    }

}

