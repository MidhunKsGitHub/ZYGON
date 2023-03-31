package com.midhun.hawkssolution.zygon.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.green
import androidx.core.text.HtmlCompat
import androidx.core.view.ContentInfoCompat.Flags
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.midhun.hawkssolution.zygon.Config.ApiInterface
import com.midhun.hawkssolution.zygon.Model.Products.Products
import com.midhun.hawkssolution.zygon.ProductsViewActivity
import com.midhun.hawkssolution.zygon.R

class ProductsAdapter(val context: Context, val productsList: List<Products>) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

   // lateinit var listImages:ArrayList<String>
    var listImages: ArrayList<String> = ArrayList()

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit var desc: TextView
        lateinit var img: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            desc = itemView.findViewById(R.id.desc)
            img = itemView.findViewById(R.id.img)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.custom_products_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        var products = productsList.get(position)





        holder.name.setText(products.name)
        holder.desc.setText(HtmlCompat.fromHtml(products.description, 0))

        var img_url = ApiInterface.IMG_URL + products.image

        Glide.with(context)
            .load(img_url)
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .apply(
//                RequestOptions() //.override(60, 60)
//                    .placeholder(R.drawable.background_color_black)
//                    .error(R.drawable.background_color_black).centerCrop()
//            )
            .into(holder.img)

        holder.img.setOnClickListener {

            for(item in productsList.get(position).imgArr)
            {

                //Toast.makeText(context,item,Toast.LENGTH_LONG).show()
                listImages.add(item)
            }

            var intent = Intent()
            intent.setClass(context, ProductsViewActivity::class.java)
            intent.putExtra("product_id",products.id)
            intent.putExtra("product_name",products.name)
            intent.putExtra("product_desc",products.description)
            intent.putStringArrayListExtra("product_img",listImages)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
              listImages.clear()
        }


    }

    override fun getItemCount(): Int {
        return productsList.size

    }


}