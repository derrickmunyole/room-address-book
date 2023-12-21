package com.example.addressbook.data

import kotlinx.coroutines.flow.Flow

class AddressRepositoryImpl(
    private val dao: AddressDao
): AddressRepository {
    override suspend fun saveAddress(address: Address) {
        dao.saveAddress(address)
    }

    override suspend fun deleteAddress(address: Address) {
        dao.deleteAddress(address)
    }

    override fun orderAddressByFirstName(): Flow<List<Address>> {
       return dao.orderAddressByFirstName()
    }

    override fun orderAddressByLastName(): Flow<List<Address>> {
        return dao.orderAddressByLastName()
    }
}