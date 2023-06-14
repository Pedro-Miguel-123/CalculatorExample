package com.example.calculator.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.calculator.MainActivityViewModel

@Composable
fun NavigationComponent(navHostController: NavHostController, mainActivityViewModel: MainActivityViewModel) {
    NavHost(navController = navHostController, startDestination = Screens.MainScreen.route ) {
        MainScreenRoute.composable(this, navHostController, mainActivityViewModel)
        SecondScreenRoute.composable(this, navHostController, mainActivityViewModel)
    }
}