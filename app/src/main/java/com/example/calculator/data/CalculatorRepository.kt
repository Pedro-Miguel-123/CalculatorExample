package com.example.calculator.data

interface CalculatorRepository {


    fun setResultValue(value: Int)

    fun getResultValue(): Int
}