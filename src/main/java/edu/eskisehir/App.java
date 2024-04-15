package edu.eskisehir;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Hello world!
 */
@NonNull
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        try (Stream<String> stream = Files.lines(Paths.get("ZChart.tsv"));) {

            double key = 0.25;
            Record min = stream.parallel()
                    .skip(1)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                  //  .filter(p -> !p.startsWith("#"))
                    .map(Record::fromString)
                    .min(Comparator.comparingDouble((Record r) -> Math.abs(r.getF() - key)))
                    .orElseThrow(IllegalArgumentException::new);


            NormalDistribution normal = new NormalDistribution();
            System.out.println("actual " + normal.inverseCumulativeProbability(key));

            System.out.println(min);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
