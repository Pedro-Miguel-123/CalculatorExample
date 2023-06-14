package com.example.calculator.composables.SecondScreen

import androidx.lifecycle.ViewModel
import com.example.calculator.data.Repository
import com.example.calculator.nav.RouteNavigator
import com.example.calculator.nav.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator  {

    var finalResult: Int = 0

    init {
        finalResult = repository.result
    }

    fun goBack() {
        navigateToRouteAndPop(Screens.MainScreen.route)
    }
}