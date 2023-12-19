package com.example.addressbook.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [Address::class],
    version = 1
)
abstract class AddressDatabase(
    val dao: AddressDao
): RoomDatabase() {

}