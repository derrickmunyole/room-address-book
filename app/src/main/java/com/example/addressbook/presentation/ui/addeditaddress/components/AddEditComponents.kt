package com.example.addressbook.presentation.ui.addeditaddress.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addressbook.R
import com.example.addressbook.presentation.ui.addeditaddress.AddEditEvent
import com.example.addressbook.presentation.ui.theme.ColorPrimary
import kotlinx.coroutines.flow.StateFlow


@Preview
@Composable
fun AddEditScreenPreview() {
    Column(modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier=Modifier.height(50.dp))
        ProfileImage()
        Spacer(modifier=Modifier.height(40.dp))

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
                {},
                addressItem = ""
            )

            LabelText("Phone", FontWeight.Medium)
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(40.dp)
                    .border(width = 1.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp)),
                {},
                addressItem = ""
            )
            LabelText("Email", FontWeight.Medium)
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(40.dp)
                    .border(width = 1.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp)),
                {},
                addressItem = ""
            )
            LabelText("Notes", FontWeight.Medium)
            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(150.dp)
                    .border(width = 1.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp)),
                {},
                addressItem = ""
            )
            Spacer(modifier=Modifier.height(40.dp))
            Box(
                modifier=Modifier.fillMaxWidth(1f),
                contentAlignment = Alignment.Center
                ) {
                SaveButton {}
            }

        }
    }
}



@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(id = R.drawable.no_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier= Modifier
            .width(150.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(100))
    )
}

@Composable
fun LabelText(label: String, fontWeight: FontWeight) {
    Text(
        text = label,
        fontWeight = fontWeight
    )
}



@Composable
fun InputTextField(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    addressItem: String
) {
    val text = remember { mutableStateOf("") }
    if(addressItem != "") {
        BasicTextField(
            value = addressItem,
            onValueChange = { newValue ->
                // Update the local state for UI recomposition
                text.value = newValue

                // Send the updated value to the ViewModel
                onValueChange(newValue)
            },
            modifier= modifier,
            decorationBox = { innerTextField ->
                Column(modifier=Modifier.padding(12.dp)) {
                    innerTextField()
                }
            }
        )
    } else {
        BasicTextField(
            value = text.value,
            onValueChange = { newValue ->
                // Update the local state for UI recomposition
                text.value = newValue

                // Send the updated value to the ViewModel
                onValueChange(newValue)
            },
            modifier = modifier,
            decorationBox = { innerTextField ->
                Column(modifier = Modifier.padding(12.dp)) {
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun SaveButton(onEvent: (AddEditEvent) -> Unit) {
    Button(
        onClick = { onEvent(AddEditEvent.OnAddressSave) },
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorPrimary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Save Address")
    }
    Log.d("Save Button", "Save triggered")
}