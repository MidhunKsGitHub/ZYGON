package com.midhun.hawkssolution.zygon.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.midhun.hawkssolution.zygon.CategoryProductActivity
import com.midhun.hawkssolution.zygon.Config.ApiInterface
import com.midhun.hawkssolution.zygon.Model.Category.Category
import com.midhun.hawkssolution.zygon.Model.Products.Products
import com.midhun.hawkssolution.zygon.R

class CategoryAdapter(val context:Context, val categoryList: List<Category>): RecyclerView.Adapter<CategoryAdapter.ProductViewHolder>() {

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var name :TextView
    lateinit var desc :TextView
    lateinit var img :ImageView

    init {
        name=itemView.findViewById(R.id.name)
        img=itemView.findViewById(R.id.img)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
           val view = LayoutInflater.from(context).inflate(R.layout.custom_category_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var category =categoryList.get(position)
        holder.name.setText(category.name)


        var img_url=ApiInterface.IMG_URL+category.image

        Glide.with(context)
            .load(img_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(
                RequestOptions() //.override(60, 60)
                    .placeholder(R.drawable.background_color_black)
                    .error(R.drawable.background_color_black).centerCrop()
            )
            .into(holder.img)

        holder.img.setOnClickListener{
            var inn = Intent()
            inn.setClass(context,CategoryProductActivity::class.java)
            inn.putExtra("product_id",category.id)
            context.startActivity(inn)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size

    }



}