package com.example.addressbook.presentation.ui.addresslist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.addressbook.presentation.ui.addresslist.components.AddressItem
import com.example.addressbook.presentation.ui.theme.AccentColor
import com.example.addressbook.presentation.ui.theme.CardColor
import com.example.addressbook.presentation.ui.theme.ColorPrimary
import com.example.addressbook.util.Routes
import com.example.addressbook.util.UIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressListScreen(
    onNavigate: ((UIEvent.Navigate) -> Unit)?,
    addressListViewModel: AddressListViewModel = hiltViewModel()
) {

    val addressListState = addressListViewModel.addresses.collectAsState(initial = emptyList())
    val addresses = addressListState.value

    val onEvent: (AddressListEvent) -> Unit = {
        addressListViewModel.onEvent(it)
    }



    if (addresses.isEmpty()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("AddressBook") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    )
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No addresses found")
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNavigate?.invoke(UIEvent.Navigate(Routes.ADD_EDIT_ADDRESS)) },
                    containerColor = AccentColor,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("AddressBook") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    )
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .padding(paddingValues)
                ) {
                    LazyColumn(modifier = Modifier
                        .fillMaxSize(1f)
                        .padding(16.dp)) {
                        items(addresses.count()) { index ->
                            val address = addresses[index]
                            Box(modifier=Modifier.padding(top=12.dp)) {
                                AddressItem(
                                    address = address,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            width = 2.dp,
                                            color = ColorPrimary,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .background(color = CardColor, shape = RoundedCornerShape(8.dp))
                                        .padding(vertical = 12.dp, horizontal = 16.dp)
                                        .clickable {
                                            onNavigate?.invoke(UIEvent.Navigate(Routes.ADDRESS_DETAIL + "?addressId=${address.id}"))
                                        },

                                    )
                            }
                        }
                    }

                }

            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNavigate?.invoke(UIEvent.Navigate(Routes.ADD_EDIT_ADDRESS)) },
                    containerColor = AccentColor,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        )
    }

}





