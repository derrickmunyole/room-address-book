package com.example.addressbook.presentation.ui.addressdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addressbook.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.addressbook.presentation.ui.addressdetail.components.CtaButton
import com.example.addressbook.presentation.ui.addressdetail.components.NotesSection
import com.example.addressbook.presentation.ui.addressdetail.components.UserInfo
import com.example.addressbook.presentation.ui.addressdetail.components.UserInfoWithIcon
import com.example.addressbook.presentation.ui.addressdetail.components.UserProfileImage
import com.example.addressbook.util.Routes
import com.example.addressbook.util.UIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressDetailScreen(
    onNavigate: ((UIEvent.Navigate) -> Unit)?,
    addressDetailViewModel: AddressDetailViewModel = hiltViewModel(),
) {
    val firstName = addressDetailViewModel.firstName
    val lastName = addressDetailViewModel.lastName
    val phoneNumber = addressDetailViewModel.phoneNumber
    val email = addressDetailViewModel.email
    val notes = addressDetailViewModel.notes

    val address = addressDetailViewModel.address


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
            Box(modifier = Modifier
                .fillMaxSize(1f)
                .padding(paddingValues),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(top= 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserProfileImage(
                        image = R.drawable.attractive,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    UserInfo(
                        name = "$firstName $lastName",
                        style = TextStyle(fontSize = 18.sp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserInfoWithIcon(
                        infoText = phoneNumber,
                        icon = Icons.Outlined.Call
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    UserInfoWithIcon(
                        infoText = email!!,
                        icon = Icons.Outlined.Email
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    NotesSection(note = notes ?: "Add a note for this address")
                    Spacer(modifier = Modifier.height(30.dp))
                    CtaButton(onEvent = {
                            onNavigate?.invoke(UIEvent.Navigate(Routes.ADD_EDIT_ADDRESS + "?addressId=${address?.id}"))
                    })
                }
            }
        }
    )


}