package com.example.calculator.data

class Repository: CalculatorRepository {

     var result: Int = 0

    override fun getResultValue(): Int {
        return result
    }

    override fun setResultValue(value: Int) {
        result = value
    }

}