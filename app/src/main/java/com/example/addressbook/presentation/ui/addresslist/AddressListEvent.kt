package com.example.addressbook.presentation.ui.addresslist

import com.example.addressbook.data.Address

sealed class AddressListEvent {
    data class ShowDetails(val address: Address): AddressListEvent()
    data class DeleteAddress(val address: Address): AddressListEvent()
    data class OnAddressClick(val address: Address): AddressListEvent()
    data object OnAddAddress: AddressListEvent()
}