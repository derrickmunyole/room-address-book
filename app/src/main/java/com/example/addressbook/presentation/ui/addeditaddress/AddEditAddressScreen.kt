package com.example.addressbook.presentation.ui.addeditaddress

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.addressbook.presentation.ui.addeditaddress.components.InputTextField
import com.example.addressbook.presentation.ui.addeditaddress.components.LabelText
import com.example.addressbook.presentation.ui.addeditaddress.components.ProfileImage
import com.example.addressbook.presentation.ui.addeditaddress.components.SaveButton
import com.example.addressbook.presentation.ui.theme.ColorPrimary
import com.example.addressbook.util.UIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditAddressScreen(
    onPopBackStack: () -> Unit,
    addEditViewModel: AddEditViewModel = hiltViewModel()
) {
    val firstName = addEditViewModel.firstName.collectAsState()
    val lastName = addEditViewModel.lastName.collectAsState()
    val phoneNumber = addEditViewModel.phoneNumber.collectAsState()
    val email = addEditViewModel.email.collectAsState()
    val notes = addEditViewModel.notes.collectAsState()

    val fullName = "${firstName.value} ${lastName.value}"


    val onEvent: (AddEditEvent) -> Unit = {
        addEditViewModel.onEvent(it)
    }


//    val imageLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia()
//    ) { uri: Uri? ->
//        uri?.let { addEditViewModel.onImageSelected(it) }
//    }

    LaunchedEffect(key1=true) {
        addEditViewModel.uiEvent.collect { event->
            when(event) {
                is UIEvent.Navigate -> {

                }
                UIEvent.PopBackStack -> {
                   onPopBackStack()
                }
                is UIEvent.showSnackBar -> {
                    TODO()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                ProfileImage()
                Spacer(modifier = Modifier.height(40.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 32.dp)
                ) {
                    LabelText("Full Name", FontWeight.Medium)
                    InputTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(40.dp)
                            .border(
                                width = 1.dp,
                                color = ColorPrimary,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        { addEditViewModel.onEvent(AddEditEvent.OnNameChanged(it)) },
                        addressItem = fullName
                    )
                    LabelText("Phone", FontWeight.Medium)
                    InputTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(40.dp)
                            .border(
                                width = 1.dp,
                                color = ColorPrimary,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        {addEditViewModel.onEvent(AddEditEvent.OnPhoneNumberChanged(it)) },
                        addressItem = phoneNumber.value
                    )
                    LabelText("Email", FontWeight.Medium)
                    InputTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(40.dp)
                            .border(
                                width = 1.dp,
                                color = ColorPrimary,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        {addEditViewModel.onEvent(AddEditEvent.OnEmailChanged(it)) },
                        addressItem = email.value
                    )
                    LabelText("Notes", FontWeight.Medium)
                    InputTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .height(150.dp)
                            .border(
                                width = 1.dp,
                                color = ColorPrimary,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        {addEditViewModel.onEvent(AddEditEvent.OnNoteChanged(it)) },
                        addressItem = notes.value
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        SaveButton(onEvent)
                    }

                }
            }
        }
    )


}