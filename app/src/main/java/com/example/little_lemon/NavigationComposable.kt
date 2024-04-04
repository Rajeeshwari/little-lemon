package com.example.little_lemon

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.room.RoomDatabase

@Composable

fun Navigation(navController: NavHostController){
    var startDest =""
    val context = LocalContext.current

    val preferencesManager = remember { PreferencesManager(context) }
    //preferencesManager.getData()
    val firstNameData =preferencesManager.getData("firstName","")
    val secondNameData=preferencesManager.getData("lastName","lastName")
    val emailData=preferencesManager.getData("email","")
    Log.d("value1",firstNameData)
    Log.d("value1",secondNameData)
    Log.d("value1",emailData)
    if (firstNameData.isNullOrBlank()|| secondNameData.isNullOrBlank() || emailData.isNullOrBlank()){
        startDest =Onboarding.route
    }
    else{
        startDest =Home.route
    }
    NavHost(navController = navController, startDestination = startDest) {
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }

        /*composable(
            Menu.route + "/{${Menu.argOrderNo}}",
            arguments = listOf(
                navArgument(Menu.argOrderNo) { type = NavType.LongType })
        ) {
            MenuScreen(it.arguments?.getInt(Menu.argOrderNo))
        }*/
    }
}