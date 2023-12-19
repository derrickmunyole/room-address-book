package com.example.addressbook.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val notes: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)