package com.example.addressbook.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Upsert
    suspend fun saveAddress(address: Address)
    @Delete
    suspend fun deleteAddress(address: Address)
    @Query("SELECT * FROM Address ORDER BY firstName ASC")
    fun orderAddressByFirstName(): Flow<List<Address>>
    @Query("SELECT * FROM Address ORDER BY lastName ASC")
    fun orderAddressByLastName(): Flow<List<Address>>
}