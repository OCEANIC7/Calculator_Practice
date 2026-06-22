package com.example.calculator;

public class Division extends Operation {

    @Override
    public double calculate(double a, double b) {

        if(b == 0)
            throw new ArithmeticException(
                    "Cannot divide by zero");

        return a / b;
    }

}