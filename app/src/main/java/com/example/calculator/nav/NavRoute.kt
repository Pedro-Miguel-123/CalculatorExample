package com.example.calculator.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.calculator.MainActivityViewModel

interface NavRoute<T: RouteNavigator> {
    val route: String

    @Composable
    fun Content(viewModel: T, mainActivityViewModel: MainActivityViewModel)

    @Composable
    fun viewModel(): T

    fun getArguments(): List<NamedNavArgument> = listOf()

    fun composable(
        builder: NavGraphBuilder,
        navHostController: NavHostController,
        mainActivityViewModel: MainActivityViewModel
    ) {
        builder.composable(route, getArguments()) {
            val viewModel = viewModel()
            val viewStateAsState by viewModel.navigationState.collectAsState()

            LaunchedEffect(viewStateAsState) {
                Log.d(null,"Nav ${this@NavRoute} updateNavigationState to $viewStateAsState")
                updateNavigationSate(navHostController, viewStateAsState, viewModel::onNavigated)
            }

            Content(viewModel = viewModel, mainActivityViewModel)
        }
    }

    private fun updateNavigationSate(
        navHostController: NavHostController,
        navigationState: NavigationState,
        onNavigated: (navState: NavigationState) -> Unit
    ) {
        when (navigationState) {
            is NavigationState.NavigateToRoute -> {
                navHostController.navigate(navigationState.route)
                onNavigated(navigationState)
            }
            is NavigationState.PopToRoute -> {
                navHostController.popBackStack(navigationState.staticRoute, false)
                onNavigated(navigationState)
            }
            is NavigationState.NavigateUp -> {
                navHostController.navigateUp()
            }
            is NavigationState.NavigateToRouteAndPop -> {
                navHostController.navigate(navigationState.route) {
                    popUpTo(0)
                }
                onNavigated(navigationState)
            }
            is NavigationState.Idle -> {
            }
        }
    }
}