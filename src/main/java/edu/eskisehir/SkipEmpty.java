package edu.eskisehir;

import java.util.function.Predicate;

public class SkipEmpty implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return !s.isEmpty();
    }
}
