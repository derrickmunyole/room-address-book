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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.addressbook.presentation.ui.addeditaddress.components.InputTextField
import com.example.addressbook.presentation.ui.addeditaddress.components.LabelText
import com.example.addressbook.presentation.ui.addeditaddress.components.ProfileImage
import com.example.addressbook.presentation.ui.addeditaddress.components.SaveButton
import com.example.addressbook.presentation.ui.theme.ColorPrimary

@Composable
fun AddEditAddressScreen(
    onPopBackStack: () -> Unit,
    addEditViewModel: AddEditViewModel = hiltViewModel()
) {
    val firstName = addEditViewModel.firstName
    val lastName = addEditViewModel.lastName
    val phoneNumber = addEditViewModel.phoneNumber
    val email = addEditViewModel.email
    val notes = addEditViewModel.notes

    val fullName = "${firstName.collectAsState().value} ${lastName.collectAsState().value}"



    val onEvent: (AddEditEvent) -> Unit = {
        addEditViewModel.onEvent(it)
    }


//    val imageLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia()
//    ) { uri: Uri? ->
//        uri?.let { addEditViewModel.onImageSelected(it) }
//    }

    Column(modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier= Modifier.height(50.dp))
        ProfileImage()
        Spacer(modifier= Modifier.height(40.dp))

        Column(
            modifier= Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 32.dp)
        ) {
            LabelText("Full Name", FontWeight.Medium)
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(40.dp)
                    .border(width = 1.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp)),
                { onEvent(AddEditEvent.OnNameChanged(it)) },
                addressItem = fullName
            )
            LabelText("Phone", FontWeight.Medium)
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(40.dp)
                    .border(width = 1.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp)),
                { onEvent(AddEditEvent.OnPhoneNumberChanged(it)) },
                addressItem = phoneNumber.collectAsState().value
            )
            LabelText("Email", FontWeight.Medium)
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(40.dp)
                    .border(width = 1.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp)),
                { onEvent(AddEditEvent.OnEmailChanged(it)) },
                addressItem = email.collectAsState().value
            )
            LabelText("Notes", FontWeight.Medium)
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(150.dp)
                    .border(width = 1.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp)),
                { onEvent(AddEditEvent.OnNoteChanged(it)) },
                addressItem = notes.collectAsState().value
            )
            Spacer(modifier= Modifier.height(40.dp))
            Box(
                modifier= Modifier.fillMaxWidth(1f),
                contentAlignment = Alignment.Center
            ) {
                SaveButton(onEvent)
            }

        }
    }
}