package com.midhun.hawkssolution.zygon

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.midhun.hawkssolution.zygon.Adapter.CategoryAdapter
import com.midhun.hawkssolution.zygon.Adapter.ProductsAdapter
import com.midhun.hawkssolution.zygon.Config.ApiInterface
import com.midhun.hawkssolution.zygon.Model.Category.Category
import com.midhun.hawkssolution.zygon.Model.Products.Products
import com.midhun.hawkssolution.zygon.Response.CategoryApiModel
import com.midhun.hawkssolution.zygon.Response.ProductsApiModel
import com.midhun.hawkssolution.zygon.databinding.FragmentProductBinding
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding
    lateinit var productsList: List<Products>
    lateinit var categoryList: List<Category>
    lateinit var productsAdapter: ProductsAdapter
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var recyclerview: RecyclerView
    lateinit var recyclerview1: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var loading:LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentProductBinding.inflate(inflater, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View = requireActivity().window.decorView
             decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            //decor.setSystemUiVisibility(0)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            val window: Window = requireActivity()!!.getWindow()
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = getResources().getColor(R.color.white)
        }


//        requireActivity()!!.window.apply {
//            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            statusBarColor = Color.TRANSPARENT
//        }
        recyclerview1 = binding.recyclerview2;
        recyclerview = binding.recyclerview;
        loading=binding.loading
        swipeRefreshLayout=binding.swipe
        swipeRefreshLayout.visibility=View.GONE
        loading.visibility=View.VISIBLE

        swipeRefreshLayout.setOnRefreshListener {
            getProducts()

        }

        recyclerview.setHasFixedSize(true)

        val layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        recyclerview.layoutManager = layoutManager
        getProducts()


        recyclerview1.setHasFixedSize(true)

        val layoutManager1 = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)
        recyclerview1.layoutManager = layoutManager1


        getCategory()


        return binding.root
    }

    private fun getProducts() {
        var call = ApiInterface.create().getProduct(ApiInterface.API_KEY, ApiInterface.API_KEY)
        call.enqueue(object : retrofit2.Callback<ProductsApiModel> {
            override fun onResponse(
                call: retrofit2.Call<ProductsApiModel>,
                response: Response<ProductsApiModel>
            ) {

                productsList = response.body()!!.allProducts.pageData
                productsAdapter = ProductsAdapter(requireContext()!!, productsList)
                recyclerview.adapter = productsAdapter
                productsAdapter.notifyDataSetChanged()
                swipeRefreshLayout.visibility=View.VISIBLE
                loading.visibility=View.GONE

               if( swipeRefreshLayout.isRefreshing)
               {
                   swipeRefreshLayout.isRefreshing=false

               }

            }

            override fun onFailure(call: retrofit2.Call<ProductsApiModel>, t: Throwable) {
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()

            }

        })

    }

    private fun getCategory(){
        var call=ApiInterface.create().getCategory(ApiInterface.API_KEY,ApiInterface.API_KEY)
        call.enqueue(object :Callback<CategoryApiModel> {
            override fun onResponse(
                call: Call<CategoryApiModel>,
                response: Response<CategoryApiModel>
            ) {
                categoryList=response.body()!!.categories
                categoryAdapter = CategoryAdapter(requireContext()!!, categoryList)
                recyclerview1.adapter = categoryAdapter
                categoryAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<CategoryApiModel>, t: Throwable) {
                Toast.makeText(activity,t.toString(),Toast.LENGTH_LONG).show()

            }

        })
    }

}
