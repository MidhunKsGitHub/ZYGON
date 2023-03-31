package com.midhun.hawkssolution.zygon.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.parser.IntegerParser
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.midhun.hawkssolution.zygon.Config.ApiInterface
import com.midhun.hawkssolution.zygon.R

class ProductsViewAdapter(val context: Context, val productsList: List<String>) :
    RecyclerView.Adapter<ProductsViewAdapter.ProductViewHolder>() {


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit var page: TextView
        lateinit var img: ImageView

        init {
            img = itemView.findViewById(R.id.img)
            page = itemView.findViewById(R.id.page)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_products_view_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

          var pos=position+1
        holder.page.setText(pos.toString()+" of "+productsList.size.toString())
        var img_url = ApiInterface.IMG_URL + productsList.get(position)

        Glide.with(context)
            .load(img_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(
                RequestOptions() //.override(60, 60)
                    .placeholder(R.drawable.background_color_black)
                    .error(R.drawable.background_color_black).centerCrop()
            )
            .into(holder.img)


    }

    override fun getItemCount(): Int {
        return productsList.size

    }


}