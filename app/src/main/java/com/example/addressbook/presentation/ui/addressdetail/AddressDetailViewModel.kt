package com.example.addressbook.presentation.ui.addressdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addressbook.data.Address
import com.example.addressbook.data.AddressRepository
import com.example.addressbook.util.Routes
import com.example.addressbook.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddressDetailViewModel @Inject constructor(
    private val repository: AddressRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val event = _uiEvent.receiveAsFlow()

    var address by mutableStateOf<Address?>(null)
        private set

    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var email by mutableStateOf<String?>("")
        private set

    var notes by mutableStateOf<String?>("")
        private set

    init {
        val addressId = savedStateHandle.get<Int>("addressId")!!
        if(addressId != -1) {
            viewModelScope.launch {
                repository.getAddressById(addressId)?.let { address ->
                    firstName = address.firstName
                    lastName = address.lastName
                    phoneNumber = address.phoneNumber
                    email = address.email
                    notes = address.notes ?: ""
                    this@AddressDetailViewModel.address  = address
                }
            }
        }

    }


    fun onEvent(event: AddressDetailEvent) {
        when (event) {
            is AddressDetailEvent.OnEditAddressClick -> {
                sendUiEvent(UIEvent.Navigate(Routes.ADD_EDIT_ADDRESS))
            }
        }
    }

    private fun sendUiEvent(uiEvent: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}