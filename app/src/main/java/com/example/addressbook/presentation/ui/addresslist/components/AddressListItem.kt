package com.example.addressbook.presentation.ui.addresslist.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addressbook.R
import com.example.addressbook.data.Address
import com.example.addressbook.presentation.ui.theme.CardColor
import com.example.addressbook.presentation.ui.theme.ColorPrimary
@Composable
fun AddressItem(
    address: Address,
    modifier: Modifier
) {
    ElevatedCard(modifier= modifier)
    {
        Row(modifier= Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 16.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically

        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.attractive),
                    contentDescription = "A girl",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column(modifier = Modifier.height(80.dp), verticalArrangement = Arrangement.Center) {
                    Text("${address.firstName} ${address.lastName}", style = MaterialTheme.typography.titleMedium)
                    Text(address.phoneNumber, style = MaterialTheme.typography.bodySmall)
                }
            }
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null, tint= ColorPrimary)
        }
    }
}


@Preview
@Composable
fun ScreenPreview(){
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Surface(modifier=Modifier.background(MaterialTheme.colorScheme.background)) {
            AddressItemPreview()
        }
    }
}


@Preview
@Composable
fun AddressItemPreview() {
    Row(modifier= Modifier
        .fillMaxWidth()
        .border(width = 2.dp, color = ColorPrimary, shape = RoundedCornerShape(8.dp))
        .background(color = CardColor, shape = RoundedCornerShape(8.dp))
        .padding(vertical = 12.dp, horizontal = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.attractive),
            contentDescription = "A girl",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column(modifier = Modifier.height(80.dp), verticalArrangement = Arrangement.Center) {
            Text("Lisa Lee", style = MaterialTheme.typography.titleMedium)
            Text("+2348012345678", style = MaterialTheme.typography.bodySmall)
        }
    }
}