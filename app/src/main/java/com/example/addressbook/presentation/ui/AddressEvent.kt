package com.example.addressbook.presentation.ui

import com.example.addressbook.data.Address

sealed class AddressEvent {
    data class InsertAddress(val address: Address): AddressEvent()

    data class DeleteAddress(val address: Address): AddressEvent()
}