package com.example.addressbook.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAddress(address: Address)
    @Delete
    suspend fun deleteAddress(address: Address)
    @Query("SELECT * FROM Address")
    fun getAddresses(): Flow<List<Address>>
    @Query("SELECT * FROM Address WHERE id = :id")
    suspend fun getAddressById(id: Int): Address?
    @Query("SELECT * FROM Address ORDER BY firstName ASC")
    fun orderAddressByFirstName(): Flow<List<Address>>
    @Query("SELECT * FROM Address ORDER BY lastName ASC")
    fun orderAddressByLastName(): Flow<List<Address>>
}