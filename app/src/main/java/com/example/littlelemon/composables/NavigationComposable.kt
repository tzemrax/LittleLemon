package com.example.littlelemon.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.*

@Composable
fun Navigation(navController: NavHostController, databaseMenuItems: List<MenuItemRoom>){
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = getStartDestination(context)
    ){
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Home.route){
            Home(navController, databaseMenuItems)
        }
        composable(Profile.route){
            Profile(navController)
        }
    }
}

private fun getStartDestination(context: Context): String {
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    }
    return if (sharedPreferences.getBoolean("registered", false)) Home.route else Onboarding.route
}