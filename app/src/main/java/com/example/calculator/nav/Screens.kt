package com.example.calculator.nav

sealed class Screens(val route: String) {
    object MainScreen: Screens("mainScreen")
    object SecondScreen: Screens("secondScreen")
}