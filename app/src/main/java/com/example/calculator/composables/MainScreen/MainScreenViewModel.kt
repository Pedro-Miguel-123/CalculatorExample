package com.example.calculator.composables.MainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculator.data.Repository
import com.example.calculator.nav.RouteNavigator
import com.example.calculator.nav.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

data class MainScreenUIState(
    val firstNumber: String = "",
    val secondNumber: String = "",
    val operationToDo: String = ""
)


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator {

    var uiState by mutableStateOf(MainScreenUIState())
        private set

    private fun setResultOperation() {
        val result = uiState.firstNumber.toInt().doOperation(uiState.operationToDo, uiState.secondNumber.toInt())
        repository.setResultValue(result)
        navigateToRouteAndPop(Screens.SecondScreen.route)

    }


    fun setFirstNumberDigit(number: String) {
        uiState = uiState.copy(
            firstNumber = uiState.firstNumber + number
        )
    }

    fun setSecondNumberDigit(number: String) {
        uiState = uiState.copy(
            secondNumber = uiState.secondNumber + number
        )
    }

    fun setOperation(operation: String) {
        if (operation == "=") {
            setResultOperation()
        } else {
            uiState = uiState.copy(
                operationToDo = operation
            )
        }
    }

    fun Int.doOperation(charOperator: String, x: Int) = when(charOperator) {
        "+" -> this + x
        "-" -> this - x
        "*" -> this * x
        "/" -> this / x
        else -> throw IllegalArgumentException("Not supported")
    }
}