package com.example.addressbook.presentation.ui.addresslist

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addressbook.data.AddressRepository
import com.example.addressbook.presentation.AddressUiState
import com.example.addressbook.util.Routes
import com.example.addressbook.util.UIEvent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddressListViewModel @Inject constructor(
    repository: AddressRepository
): ViewModel() {
    val addresses = repository.getAddresses()

    private val _uiEvent = Channel<UIEvent>()

    private val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: AddressListEvent) {
        when(event) {
            is AddressListEvent.DeleteAddress -> {

            }
            is AddressListEvent.OnAddAddress -> {
                sendUiEvent(UIEvent.Navigate(Routes.ADD_EDIT_ADDRESS))
            }
            is AddressListEvent.OnAddressClick -> {
                sendUiEvent(UIEvent.Navigate(Routes.ADD_EDIT_ADDRESS + "?addressId=${event.address.id}"))
            }
            is AddressListEvent.ShowDetails -> {
                sendUiEvent(UIEvent.Navigate(Routes.ADDRESS_DETAIL))
            }
        }
    }

    private fun sendUiEvent(uiEvent: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}