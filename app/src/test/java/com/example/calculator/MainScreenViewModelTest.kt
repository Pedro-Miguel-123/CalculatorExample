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
   fun setFirstDigitTest() {
      //Check everything is empty when we start
      Assert.assertEquals(viewModel.uiState.firstNumber, "")
      //Set some values
      viewModel.setFirstNumberDigit("5")
      Assert.assertEquals(viewModel.uiState.firstNumber, "5")
   }

   @Test
   fun setOperationTest() {
      setFirstDigitTest()
      Assert.assertEquals(viewModel.uiState.operationToDo, "")

      viewModel.setOperation("*")
      Assert.assertEquals(viewModel.uiState.operationToDo, "*")
   }

   @Test
   fun setSecondDigitTest() {
      setOperationTest()
      viewModel.setSecondNumberDigit("3")
      Assert.assertEquals(viewModel.uiState.secondNumber, "3")
      viewModel.setSecondNumberDigit("0")
      //Second number should now be 30
      Assert.assertEquals(viewModel.uiState.secondNumber, "30")
   }

   @Test
   fun verifyResult() {
      setSecondDigitTest()
      // 5 * 30 = 150, lets check the result
      viewModel.setOperation("=")
      verify(repository).setResultValue(150)
   }

   private fun createViewModel() = MainScreenViewModel(repository, routeNavigator)
}