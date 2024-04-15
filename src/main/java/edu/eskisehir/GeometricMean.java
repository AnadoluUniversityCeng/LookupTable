package edu.eskisehir;

public class GeometricMean implements Mean {
    @Override
    public double mean(double a, double b) {
        return Math.sqrt(a * b);
    }
}
