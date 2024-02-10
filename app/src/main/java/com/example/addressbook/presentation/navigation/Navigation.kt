package com.example.addressbook.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.addressbook.presentation.ui.addeditaddress.AddEditAddressScreen
import com.example.addressbook.presentation.ui.addressdetail.AddressDetailScreen
import com.example.addressbook.presentation.ui.addresslist.AddressListScreen
import com.example.addressbook.util.Routes

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.ADDRESS_LIST
    ) {
        composable(Routes.ADDRESS_LIST) {
            AddressListScreen(onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(
            Routes.ADD_EDIT_ADDRESS + "?addressId={addressId}",
            arguments = listOf(
                navArgument(name="addressId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditAddressScreen(onPopBackStack = {
                navController.navigate(Routes.ADDRESS_LIST)
            })
        }
        composable(Routes.ADDRESS_DETAIL + "?addressId={addressId}",
            arguments = listOf(
                navArgument(name = "addressId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val addressId = backStackEntry.arguments?.getInt("addressId") ?: -1
            Log.d("AddressId", addressId.toString())
            AddressDetailScreen(onNavigate = {
                navController.navigate(it.route)
            })
        }

    }
}