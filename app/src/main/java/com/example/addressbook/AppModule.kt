package com.example.addressbook

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.addressbook.data.AddressDao
import com.example.addressbook.data.AddressDatabase
import com.example.addressbook.data.AddressRepository
import com.example.addressbook.data.AddressRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAddressDatabase(app: Application): AddressDatabase {
        return Room.databaseBuilder(
            app,
            AddressDatabase::class.java,
            name = "address.db"
            ).build()
    }

    @Provides
    @Singleton
    fun provideAddressRepository(addressDatabase: AddressDatabase): AddressRepository {
        return AddressRepositoryImpl(dao = addressDatabase.dao())
    }
}