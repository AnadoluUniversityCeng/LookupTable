package edu.eskisehir;

public class ArithmeticMean implements @NonNull Mean {
    @Override
    public double mean(double a, double b) {
        return (a + b) / 2;
    }
}
