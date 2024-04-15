package edu.eskisehir;

import java.util.List;

public abstract class UnmodifiableList<T> implements @NonNull List<@NonNull T> {

    T ahmet;

    String s = (@NonNull String) "";
    String data = new @NonNull String("original");

    void monitorTemperature() throws @NonNull IllegalArgumentException {

        String s = (@NonNull String) "";

        String data = new @NonNull String("original");
    }

    void printMe(String a) {
        System.out.println(a.toString());
    }
}
