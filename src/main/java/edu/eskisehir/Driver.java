package edu.eskisehir;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Driver {

    public static void main(String[] args) {

        evaluate((i, j) -> (i + j) / 2);
        evaluate((a, b) -> 2 * a * b / (a + b));
        evaluate((a, b) -> Math.sqrt(a * b));
        evaluate((a, b) -> 0);

        UnaryOperator t;
        BinaryOperator b ;


    }

    static void evaluate(Mean mean) {
        System.out.println("the central tendency is  " + mean.mean(8, 9));
    }
}
