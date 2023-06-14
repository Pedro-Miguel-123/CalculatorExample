package com.example.calculator

import com.example.calculator.composables.MainScreen.MainScreenViewModel
import com.example.calculator.data.CalculatorRepository
import com.example.calculator.data.Repository
import com.example.calculator.nav.RouteNavigator
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MainScreenViewModelTest {

   private val repository:  CalculatorRepository = mock()
   private val routeNavigator: RouteNavigator = mock()
   lateinit var viewModel: MainScreenViewModel
   @Before
   fun setup() {
      viewModel = createViewModel()
   }
   @Test
   fun `first number is being typed`() {
      //Set some values
      viewModel.setFirstNumberDigit("5")
      Assert.assertEquals(viewModel.uiState.firstNumber, "5")
   }

   @Test
   fun `operation is being chosen`() {
      `first number is being typed`()

      viewModel.setOperation("*")
      Assert.assertEquals(viewModel.uiState.operationToDo, "*")
   }

   @Test
   fun `second number is being typed`() {
      `operation is being chosen`()
      viewModel.setSecondNumberDigit("3")
      Assert.assertEquals(viewModel.uiState.secondNumber, "3")
      viewModel.setSecondNumberDigit("0")
      //Second number should now be 30
      Assert.assertEquals(viewModel.uiState.secondNumber, "30")
   }

   @Test
   fun `when finalizing the operation we wish to know the result of`() {
      `second number is being typed`()
      // 5 * 30 = 150, lets check the result
      viewModel.setOperation("=")
      verify(repository).setResultValue(150)
   }

   private fun createViewModel() = MainScreenViewModel(repository, routeNavigator)
}