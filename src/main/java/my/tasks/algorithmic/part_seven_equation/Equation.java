package my.tasks.algorithmic.part_seven_equation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Task in part 7.
 * Output all positive integer solutions of the equation.
 * Equation:
 * a3 + b3 = c3 + d4
 */
public class Equation {

    public static void main(String[] args) {
        int process = process(5);
        int processOptimized = processOptimized(3);
        System.out.println();
    }

    /**
     * Brute force solution.
     * Time complexity O(N4).
     */
    private static int process(int limit) {
        int count = 0;
        for (int a = 1; a <= limit; a++) {
            for (int b = 1; b <= limit; b++) {
                for (int c = 1; c <= limit; c++) {
                    for (int d = 1; d <= limit; d++) {
                        if (Math.pow(a, 3) + Math.pow(b, 3) == Math.pow(c, 3) + Math.pow(d, 3)) {
                            count++;
                            System.out.printf("а = %s b = %s c = %s d = %s %n", a, b, c, d);
                        }
                    }
                }
            }
        }

        System.out.println("Brute force solution finished. Count of solutions: " + count);
        System.out.println("==========================");
        return count;
    }

    /**
     * Optimized solution.
     * Time complexity O(N2).
     */
    private static int processOptimized(int limit) {
        Map<Integer, List<Pair>> map = new LinkedHashMap<>();

        for (int a = 1; a <= limit; a++) {
            for (int b = 1; b <= limit; b++) {
                int result = (int) (Math.pow(a, 3) + Math.pow(b, 3));
                Pair pair = new Pair(a, b);
                Optional.ofNullable(map.get(result)).ifPresentOrElse(entry -> entry.add(pair),
                        () -> {
                            List<Pair> pairs = new ArrayList<>();
                            pairs.add(pair);
                            map.put(result, pairs);
                        });
            }
        }

        AtomicInteger count = new AtomicInteger();
        for (int c = 1; c <= limit; c++) {
            for (int d = 1; d <= limit; d++) {
                Pair currentPair = new Pair(c, d);
                int result = (int) (Math.pow(c, 3) + Math.pow(d, 3));
                List<Pair> decision = map.get(result);
                decision.forEach(pair -> {
                    count.getAndIncrement();
                    System.out.printf("а = %s b = %s c = %s d = %s %n",
                            pair.getX(), pair.getY(), currentPair.getX(), currentPair.getY());
                });
            }
        }

        System.out.println("Optimized solution finished. Count of solutions: " + count);
        return count.get();
    }

    /**
     * Pair of numbers.
     */
    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
