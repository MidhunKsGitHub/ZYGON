package com.midhun.hawkssolution.zygon.Model.Products

data class Products(
    val categories: String,
    val description: String,
    val id: Int,
    val image: String,
    val imgArr: List<String>,
    val name: String
)