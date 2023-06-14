package com.example.calculator

import com.example.calculator.composables.MainScreen.MainScreenViewModel
import com.example.calculator.data.CalculatorRepository
import com.example.calculator.data.Repository
import com.example.calculator.nav.RouteNavigator
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock

class MainScreenViewModelTest {

   private val repository:  CalculatorRepository = mock()
   private val routeNavigator: RouteNavigator = mock()

   @Test
   fun setDigits() {
      val viewModel = createViewModel()
      //Check everything is empty when we start
      Assert.assertEquals(viewModel.uiState.firstNumber, "")
      Assert.assertEquals(viewModel.uiState.operationToDo, "")
      Assert.assertEquals(viewModel.uiState.secondNumber, "")
      //Set some values
      viewModel.setFirstNumberDigit("5")
      Assert.assertEquals(viewModel.uiState.firstNumber, "5")
      viewModel.setOperation("*")
      Assert.assertEquals(viewModel.uiState.operationToDo, "*")
      viewModel.setSecondNumberDigit("3")
      Assert.assertEquals(viewModel.uiState.secondNumber, "3")
      viewModel.setSecondNumberDigit("0")
      //Second number should now be 30
      Assert.assertEquals(viewModel.uiState.secondNumber, "30")
      // 5 * 30 = 150, lets check the result
      viewModel.setOperation("=")
      Assert.assertEquals(repository.getResultValue(), 150)
   }

   private fun createViewModel() = MainScreenViewModel(repository, routeNavigator)
}