package com.midhun.hawkssolution.zygon.Config


import com.midhun.hawkssolution.zygon.Model.Category.Category
import com.midhun.hawkssolution.zygon.Response.CategoryApiModel
import com.midhun.hawkssolution.zygon.Response.ProductsApiModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("product/getAll")
    fun getProduct(
        @Header("Authorization") authorization: String,
        @Field("Authorization") Authorization: String,


    ): Call<ProductsApiModel>


    @FormUrlEncoded
    @POST("categories")
    fun getCategory(
        @Header("Authorization") authorization: String,
        @Field("Authorization") Authorization: String,


        ): Call<CategoryApiModel>


    @FormUrlEncoded
    @POST("product/categoryProducts")
    fun getCategoryProducts(
        @Header("Authorization") authorization: String,
        @Field("Authorization") Authorization: String,
        @Field("category_id") category_id: String,


        ): Call<ProductsApiModel>

    companion object {

        var BASE_URL = "https://zygon.hawkssolutions.com/basicapi/public/v1/"
        var IMG_URL="https://zygon.hawkssolutions.com/basicapi/public/"
        var API_KEY = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikhhd2tzIFNvbHV0aW9ucyIsImFkbWluIjp0cnVlfQ.B5RoeVIIAVuLBbkq_yCFoZMmy4gXmiaIF-tSM3yPzQM"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}