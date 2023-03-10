package my.tasks.algorithmic;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Task in part 7.
 * Output all positive integer solutions of the equation.
 * Equation:
 * a<sup>3</sup> + b<sup>3</sup> = c<sup>3</sup> + d<sup>3</sup>
 */
public class Equation {

    public static void main(String[] args) {
        int limit = 100;

        Stopwatch stopwatch = Stopwatch.createStarted();
        long bruteForceSolutions = process(limit);
        long processMilliSeconds = stopwatch.elapsed().toMillis();

        stopwatch = Stopwatch.createStarted();
        long optimizedSolutions = processOptimized(limit);
        long processOptimizedMilliSeconds = stopwatch.elapsed().toMillis();

        System.out.println("Time process: " + processMilliSeconds + " ms");
        System.out.println("Time process optimized: " + processOptimizedMilliSeconds + " ms");
        System.out.println("Time difference: " + (processMilliSeconds - processOptimizedMilliSeconds) + " ms");
        System.out.println("Limit: " + limit);
        System.out.println("Brute force solution count: " + bruteForceSolutions);
        System.out.println("Optimized solution count: " + optimizedSolutions);
    }

    /**
     * Brute force solution.
     * Time complexity O(N<sup>4</sup>).
     */
    private static long process(int limit) {
        long count = 0;
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

        System.out.println("Brute force solution finished");
        System.out.println("==========================");
        return count;
    }

    /**
     * Optimized solution.
     * Time complexity O(N<sup>2</sup>).
     */
    private static long processOptimized(int limit) {
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

        AtomicLong count = new AtomicLong();
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

        System.out.println("Optimized solution finished");
        System.out.println("==========================");
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
