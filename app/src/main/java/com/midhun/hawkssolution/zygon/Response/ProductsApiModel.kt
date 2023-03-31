package com.midhun.hawkssolution.zygon.Response

import com.google.gson.annotations.SerializedName
import com.midhun.hawkssolution.zygon.Model.Products.AllProducts
import com.midhun.hawkssolution.zygon.Model.Products.Products

data class ProductsApiModel(
    @SerializedName("allProducts")
    val allProducts: AllProducts


)

