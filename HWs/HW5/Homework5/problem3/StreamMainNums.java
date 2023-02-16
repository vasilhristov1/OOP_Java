package problem3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMainNums {
    public static void main(String[] args) {
        List<String> numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(Integer.toString(i));
        }

        numbers.stream()
                .reduce((s1, s2) -> s1 + "#" + s2)
                .ifPresent(System.out::println);

        ////////////////

        List<Integer> randomNums = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            randomNums.add(rand.nextInt(31));
        }

        boolean matchedResult = randomNums.stream()
                .anyMatch((d) -> d % 5 == 0);
        System.out.printf("Is there number divisible by 5? %s%n", (matchedResult ? "Yes" : "No"));

        matchedResult = randomNums.stream()
                .allMatch((d) -> d < 15);
        System.out.printf("Are all numbers are less than 15? %s%n", (matchedResult ? "Yes" : "No"));

        int[] copyRandNums = new int[20];
        for (int i = 0; i < 20; i++) {
            copyRandNums[i] = randomNums.get(i);
        }

        double avg = IntStream.of(copyRandNums)
                .average()
                .orElseGet(() -> -1);

        Predicate<Integer> intPredGreaterAvg = (x) -> x > avg;

        int cnt = 0;

        for (int i = 0; i < 20; i++) {
            if (intPredGreaterAvg.test(randomNums.get(i))) {
                cnt++;
            }
        }

        System.out.printf("Is the count of all numbers bigger than the average is more than 5? %s%n", (cnt > 5 ? "Yes" : "No"));
    }
}
