package com.example.addressbook.presentation

data class AddressUiState(
    val firstName: String? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val notes: String? = null,
)