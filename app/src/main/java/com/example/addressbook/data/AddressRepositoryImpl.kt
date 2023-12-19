package com.example.addressbook.data

import kotlinx.coroutines.flow.Flow

class AddressRepositoryImpl: AddressRepository {
    override suspend fun saveAddress(address: Address) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAddress(address: Address) {
        TODO("Not yet implemented")
    }

    override fun orderAddressByFirstName(): Flow<List<Address>> {
        TODO("Not yet implemented")
    }

    override fun orderAddressByLastName(): Flow<List<Address>> {
        TODO("Not yet implemented")
    }
}