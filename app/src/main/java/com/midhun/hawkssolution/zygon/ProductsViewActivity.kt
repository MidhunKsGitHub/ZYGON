package com.midhun.hawkssolution.zygon

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.midhun.hawkssolution.zygon.Adapter.ProductsViewAdapter
import com.midhun.hawkssolution.zygon.databinding.ActivityProductsViewBinding


class ProductsViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductsViewBinding
    lateinit var name: TextView
    lateinit var desc: TextView
    lateinit var imageList: ArrayList<String>
    lateinit var NAME: String
    lateinit var DESC: String
    lateinit var ID: String
    lateinit var recyclerView: RecyclerView
    lateinit var productsViewAdapter: ProductsViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        name = binding.name
        desc = binding.desc

        NAME = getIntent().extras!!.getString("product_name").toString()
        DESC = getIntent().extras!!.getString("product_desc").toString()
        ID = getIntent().extras!!.getString("product_id").toString()
        imageList = getIntent().extras!!.getStringArrayList("product_img") as ArrayList<String>

        name.setText(NAME.toString())
        desc.setText(HtmlCompat.fromHtml(DESC,0))

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val decor: View = window.decorView
//            //  decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
//            decor.setSystemUiVisibility(0)
//        }
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            val window: Window = getWindow()
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.statusBarColor = getResources().getColor(R.color.white)
//        }


        recyclerView = binding.recyclerview;
        recyclerView.setHasFixedSize(true)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager
        productsViewAdapter=ProductsViewAdapter(applicationContext,imageList)
        recyclerView.adapter=productsViewAdapter;
    }
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
