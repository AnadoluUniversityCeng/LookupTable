package edu.eskisehir;


import java.io.Closeable;

public class HarmonicMean implements Mean, Closeable,Cloneable {
    @Override
    public double mean(double a, double b) {
        return 2*a*b / (a+b);
    }

    @Override
    public void close() {

    }

   public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

}
