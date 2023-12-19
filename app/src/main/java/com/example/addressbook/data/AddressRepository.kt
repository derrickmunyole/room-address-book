package com.example.addressbook.data

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    suspend fun saveAddress(address: Address)
    suspend fun deleteAddress(address: Address)
    fun orderAddressByFirstName(): Flow<List<Address>>
    fun orderAddressByLastName(): Flow<List<Address>>
}