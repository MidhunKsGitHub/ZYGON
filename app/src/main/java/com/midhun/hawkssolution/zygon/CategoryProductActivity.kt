package com.midhun.hawkssolution.zygon

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midhun.hawkssolution.zygon.Adapter.ProductsAdapter
import com.midhun.hawkssolution.zygon.Config.ApiInterface
import com.midhun.hawkssolution.zygon.Model.Products.Products
import com.midhun.hawkssolution.zygon.Response.ProductsApiModel
import com.midhun.hawkssolution.zygon.databinding.ActivityCategoryProductBinding
import retrofit2.Response

class CategoryProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryProductBinding
    lateinit var img_back: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var productsList: List<Products>
    lateinit var productAdapter: ProductsAdapter
    lateinit var CATID: String
    lateinit var loading: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        img_back = binding.imgBack;
        recyclerView = binding.recyclerview
        loading=binding.loading

        recyclerView.visibility=View.GONE
        loading.visibility=View.VISIBLE

        img_back.setOnClickListener{
            finish()
        }

         CATID=intent.extras!!.getString("product_id").toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View = window.decorView
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            //decor.setSystemUiVisibility(0)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = getWindow()
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = getResources().getColor(R.color.white)
        }


        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        getCategoryProducts()

    }

    private fun getCategoryProducts() {
        var call = ApiInterface.create().getCategoryProducts(ApiInterface.API_KEY, ApiInterface.API_KEY,CATID)
        call.enqueue(object : retrofit2.Callback<ProductsApiModel> {
            override fun onResponse(
                call: retrofit2.Call<ProductsApiModel>,
                response: Response<ProductsApiModel>
            ) {

                productsList = response.body()!!.allProducts.pageData
                productAdapter = ProductsAdapter(applicationContext, productsList)
                recyclerView.adapter = productAdapter
                productAdapter.notifyDataSetChanged()
                recyclerView.visibility=View.VISIBLE
                loading.visibility=View.GONE

            }

            override fun onFailure(call: retrofit2.Call<ProductsApiModel>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_LONG).show()

            }

        })

    }
}