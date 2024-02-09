package com.example.addressbook.presentation.ui.addressdetail.components

import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.addressbook.R
import com.example.addressbook.presentation.ui.theme.AddressBookTheme
import com.example.addressbook.presentation.ui.theme.ColorPrimary

@Composable
fun UserProfileImage(
    image: Int,
    modifier: Modifier
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun UserInfo(
    name: String,
    style: TextStyle
) {
    Text(text = name, style = style)
}

@Composable
fun UserInfoWithRaster(
    infoText: String,
    icon: Image,
    modifier: Modifier?
) {
    Row {
        icon
        Spacer(modifier = Modifier.width(8.dp))
        Text(text=infoText, modifier=modifier!!)
    }
}
@Composable
fun UserInfoWithIcon(
    infoText: String,
    icon: ImageVector,
) {
    Row {
        Icon(icon,null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text=infoText)
    }
}

@Composable
fun NotesSection(
    note: String
) {
    Surface(
        modifier = Modifier
            .height(240.dp)
            .width(300.dp)
            .border(2.dp, ColorPrimary, RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Column(modifier=Modifier.padding(horizontal = 12.dp)) {
            Text("Notes",
                modifier = Modifier.padding(top=12.dp),
                style= TextStyle(color = ColorPrimary, fontWeight = FontWeight.Medium)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note,fontSize = 12.sp)
        }
    }
}

@Composable
fun CtaButton(
    onEvent: () -> Unit
) {
    IconButton(
        onClick = { onEvent.invoke() },
        modifier= Modifier
            .width(160.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = ColorPrimary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Icon(
                Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Text("Edit Contact", color = Color.White)
        }
    }

    Log.d("Ctabutton", "Click received")
}

@Preview
@Composable
fun AddressDetailScreenPreview() {
    Surface(modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        AddressBookTheme {
            Column(modifier= Modifier
                .fillMaxSize(1f)
                .padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserProfileImagePreview()
                Spacer(modifier=Modifier.height(20.dp))
                UserInfoTextPreview()
                UserInfoWithIconPreview()
                UserInfoWithIcon1()
                Spacer(modifier=Modifier.height(30.dp))
                NotesPreview()
                Spacer(modifier=Modifier.height(30.dp))
                CtaButtonPreview()
            }
        }
    }

}

@Preview
@Composable
fun UserProfileImagePreview() {
    Image(
        painter = painterResource(id = R.drawable.girl),
        contentDescription = "A girl",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
    )

}
@Preview
@Composable
fun UserInfoTextPreview() {
    Text(text = "Adam Sandler", style = MaterialTheme.typography.titleLarge)
}
@Preview
@Composable
fun UserInfoWithIconPreview() {
    Row {
        Icon(painter=painterResource(id = R.drawable.phone), contentDescription = null )
        Spacer(modifier = Modifier.width(8.dp))
        Text("+254708725851")
    }
}

@Preview
@Composable
fun UserInfoWithIcon1() {
    Row {
        Icon(Icons.Outlined.Email, contentDescription = null )
        Spacer(modifier = Modifier.width(8.dp))
        Text("adam.sandler@email.net")
    }
}

@Preview
@Composable
fun NotesPreview() {
    Surface(
        modifier = Modifier
            .height(240.dp)
            .width(300.dp)
            .border(2.dp, ColorPrimary, RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Column(modifier=Modifier.padding(horizontal = 12.dp)) {
            Text("Notes",
                modifier = Modifier.padding(top=12.dp),
                style= TextStyle(color = ColorPrimary, fontWeight = FontWeight.Medium)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(R.string.sample_notes),fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun CtaButtonPreview() {
    IconButton(
        onClick = { /*TODO*/ },
        modifier= Modifier
            .width(160.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = ColorPrimary)
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = null,
                    tint = Color.White
                    )
                Spacer(modifier = Modifier.weight(1f))
                Text("Edit Contact", color = Color.White)
            }
    }
}