package com.example.myapp.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun SetUpNavGraph( navController: NavHostController){
   NavHost(
       navController= navController,
       startDestination = Screen.Splash.route
   ){
       composable(route=Screen.Splash.route){
           AnimatesSplashScreen(navController)
       }
       composable(route=Screen.Home.route){
           Login()
       }
   }
}