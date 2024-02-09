package com.example.addressbook.data

import androidx.compose.runtime.MutableState
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address(
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var email: String?,
    var notes: String?,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)