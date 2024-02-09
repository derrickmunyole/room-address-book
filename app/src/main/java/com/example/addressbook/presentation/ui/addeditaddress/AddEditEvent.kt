package com.example.addressbook.presentation.ui.addeditaddress

sealed class AddEditEvent {
    data class OnNameChanged(val name: String) : AddEditEvent()
    data class OnPhoneNumberChanged(val phoneNumber: String) : AddEditEvent()
    data class OnEmailChanged(val email: String) : AddEditEvent()
    data class OnNoteChanged(val notes: String) : AddEditEvent()
    data object OnAddressSave : AddEditEvent()
}