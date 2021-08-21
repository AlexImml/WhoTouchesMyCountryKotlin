package com.example.whotouchesmycountry.data

data class Country(
    val name: String,
    val nativeName: String,
    val area: Double,
    val cioc: String,
    val borders: Array<String>
    )