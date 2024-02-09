package com.example.addressbook.presentation.ui.addressdetail

import com.example.addressbook.data.Address

sealed class AddressDetailEvent {
    data class OnEditAddressClick(val route: String) : AddressDetailEvent()
}