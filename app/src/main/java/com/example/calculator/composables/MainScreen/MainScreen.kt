package com.example.calculator.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.MainActivityViewModel
import com.example.calculator.composables.MainScreen.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel, mainActivityViewModel: MainActivityViewModel) {

    val viewState = viewModel.uiState
    val firstNumber = viewState.firstNumber
    val secondNumber = viewState.secondNumber
    val operation = viewState.operationToDo


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Display(text = "${firstNumber}${operation}${secondNumber}")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(text = "7", number = true, viewModel)
            Button(text = "8", number = true, viewModel)
            Button(text = "9", number = true, viewModel)
            Button(text = "/", number = false, viewModel)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(text = "4", number = true, viewModel)
            Button(text = "5", number = true, viewModel)
            Button(text = "6", number = true, viewModel)
            Button(text = "*", number = false, viewModel)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(text = "1", number = true, viewModel)
            Button(text = "2", number = true, viewModel)
            Button(text = "3", number = true, viewModel)
            Button(text = "-", number = false, viewModel)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(text = "0", number = true, viewModel)
            Button(text = "=", number = false, viewModel)
            Button(text = "+", number = false, viewModel)
        }
    }
}



@Composable
fun Button(text: String, number: Boolean, viewModel: MainScreenViewModel) {
    Button(
        onClick = {
            if (number && viewModel.uiState.operationToDo == "") {
                viewModel.setFirstNumberDigit(text)
                println("Changed first number")
                println("Current value -> ${viewModel.uiState.firstNumber}")
            }
            else if (number && viewModel.uiState.operationToDo != "") {
                viewModel.setSecondNumberDigit(text)
                println("Changed second number")
                println("Current value -> ${viewModel.uiState.secondNumber}")
            }
            else {
                println("Changed operation")
                viewModel.setOperation(text)
            }
        },
        modifier = Modifier
            .padding(8.dp)
            .size(64.dp)
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun Display(text: String) {
    Text(
        text = text,
        fontSize = 48.sp,
        modifier = Modifier.padding(16.dp)
    )
}
