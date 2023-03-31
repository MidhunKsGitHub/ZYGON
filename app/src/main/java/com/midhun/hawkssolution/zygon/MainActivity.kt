package com.midhun.hawkssolution.zygon

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.midhun.hawkssolution.zygon.Config.ApiInterface
import com.midhun.hawkssolution.zygon.Model.Products.Products
import com.midhun.hawkssolution.zygon.Response.ProductsApiModel
import com.midhun.hawkssolution.zygon.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
     lateinit var container:FrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.conatiner, ProductFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}