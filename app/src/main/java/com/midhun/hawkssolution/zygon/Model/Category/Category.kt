package com.midhun.hawkssolution.zygon.Model.Category

data class Category(
    val ancestors: String,
    val deleteStatus: String,
    val description: String,
    val id: String,
    val image: String,
    val is_top_menu: String,
    val name: String,
    val parent: String,
    val path: String,
    val sort_order: String,
    val status: String,
    val thumb: String
)