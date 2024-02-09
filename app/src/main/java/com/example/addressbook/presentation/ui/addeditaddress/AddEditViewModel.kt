package com.example.addressbook.presentation.ui.addeditaddress

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addressbook.data.Address
import com.example.addressbook.data.AddressRepository
import com.example.addressbook.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: AddressRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiEvent = Channel<UIEvent>()

    private val uiEvent = _uiEvent.receiveAsFlow()

    private val _addEditEvent = Channel<AddEditEvent>()
    val addEditEvent = _addEditEvent.receiveAsFlow()

    var address by mutableStateOf<Address?>(null)
        private set

    var _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName.asStateFlow()

    var _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName.asStateFlow()

    var _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    var _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    var _notes = MutableStateFlow("")
    val notes: StateFlow<String> = _notes.asStateFlow()

    var imageUri by mutableStateOf("")
        private set

    init {
        val addressId = savedStateHandle.get<Int>("addressId")!!
        if (addressId != -1) {
            viewModelScope.launch {
                repository.getAddressById(addressId)?.let { address ->
                    _firstName.value = address.firstName
                    _lastName.value = address.lastName
                    _phoneNumber.value = address.phoneNumber
                    _email.value = address.email ?: ""
                    _notes.value = address.notes ?: ""
                    this@AddEditViewModel.address = address
                }
            }
        }

    }

    private fun resetFields() {
        _firstName.value = ""
        _lastName.value = ""
        _phoneNumber.value = ""
        _email.value = ""
        _notes.value = ""
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.OnEmailChanged -> {
                _email.value = event.email
            }

            is AddEditEvent.OnNameChanged -> {
                val indexOfLastSpace = event.name.lastIndexOf(" ")
                if (indexOfLastSpace != -1) {
                    _firstName.value = event.name.substring(0, indexOfLastSpace)
                    _lastName.value = event.name.substring(indexOfLastSpace + 1)
                } else {
                    _firstName.value = event.name
                    _lastName.value = ""
                }
            }

            is AddEditEvent.OnPhoneNumberChanged -> {
                _phoneNumber.value = event.phoneNumber
            }

            is AddEditEvent.OnNoteChanged -> {
                _notes.value = event.notes
            }

            is AddEditEvent.OnAddressSave -> {
                viewModelScope.launch {
                    val existingAddress = address?.let { repository.getAddressById(it.id) }
                    if (_firstName.value.isNotBlank() && lastName.value.isNotBlank() && phoneNumber.value.isNotBlank()) {
                        try {
                            withContext(Dispatchers.IO) {
                                if (existingAddress != null) {
                                    val address = existingAddress.copy(
                                        firstName = _firstName.value.trim(),
                                        lastName = _lastName.value.trim(),
                                        phoneNumber = _phoneNumber.value.trim(),
                                        email = _email.value.trim(),
                                        notes = _notes.value.trim()
                                    )

                                    repository.saveAddress(address)
                                } else {
                                    repository.saveAddress(
                                        Address(
                                            firstName = _firstName.value.trim(),
                                            lastName = _lastName.value.trim(),
                                            phoneNumber = _phoneNumber.value.trim(),
                                            email = _email.value.trim(),
                                            notes = _notes.value.trim()
                                        )
                                    )
                                }

                            }
                            resetFields()
                        } catch (e: Exception) {
                            e.localizedMessage?.let { Log.d("Error occurred", it) }
                        }
                    }
                }

            }
        }


    }

    val onImageSelected: (Uri?) -> Unit = { uri ->
        // Handle the selected image URI here
        // For example, store it in a variable or pass it to another function
        if (uri != null) {
            val imageData = uri.toString()
            viewModelScope.launch {

            }
        }
    }
}