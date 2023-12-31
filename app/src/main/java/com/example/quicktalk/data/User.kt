package com.example.quicktalk.data

data class User(
    val id: String? = "",
    val name: String? = "",
    val lastName: String? = "",
    val age: Int? = 0,
    val isOnline: Boolean? = false
)