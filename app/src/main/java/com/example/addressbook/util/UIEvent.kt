package com.example.addressbook.util

sealed class UIEvent {
    data object PopBackStack : UIEvent()
    data class Navigate(val route: String): UIEvent()
    data class showSnackBar(
        val message: String,
        val action: String? = null
    ): UIEvent()
}