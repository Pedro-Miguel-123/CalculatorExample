package com.example.calculator.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculator.MainActivityViewModel
import com.example.calculator.composables.MainScreen
import com.example.calculator.composables.MainScreen.MainScreenViewModel
import com.example.calculator.composables.SecondScreen.SecondScreen
import com.example.calculator.composables.SecondScreen.SecondScreenViewModel

object MainScreenRoute : NavRoute<MainScreenViewModel> {
    override val route = Screens.MainScreen.route

    @Composable
    override fun viewModel(): MainScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: MainScreenViewModel, mainActivityViewModel: MainActivityViewModel) = MainScreen(viewModel = viewModel, hiltViewModel())
}

object SecondScreenRoute : NavRoute<SecondScreenViewModel> {
    override val route = Screens.SecondScreen.route

    @Composable
    override fun viewModel(): SecondScreenViewModel = hiltViewModel()

    @Composable
    override fun Content(
        viewModel: SecondScreenViewModel,
        mainActivityViewModel: MainActivityViewModel
    ) = SecondScreen(viewModel = viewModel, hiltViewModel())
 }