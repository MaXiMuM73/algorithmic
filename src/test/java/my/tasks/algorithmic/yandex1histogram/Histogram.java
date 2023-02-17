package my.tasks.algorithmic.yandex1histogram;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Histogram {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        StringBuilder collector = new StringBuilder();
        collector.append(input.nextLine());
        while (input.hasNext()) {
            collector.append(input.nextLine());
        }

        String inputWithoutSpace = collector.toString().replaceAll("[\\s\n]", "");
        Map<String, Integer> charPerCount = new TreeMap<>();

        for (int i = 0; i < inputWithoutSpace.length(); i++) {
            String symbol = String.valueOf(inputWithoutSpace.charAt(i));
            if (charPerCount.containsKey(symbol)) {
                charPerCount.put(symbol, charPerCount.get(symbol) + 1);
            } else {
                charPerCount.put(symbol, 1);
            }
        }

        int rowCount = charPerCount.values().stream()
                .max(Integer::compareTo).get();

        for (int currentRowIndex = 0; currentRowIndex < rowCount; currentRowIndex++) {
            int finalCurrentRowIndex = currentRowIndex;
            charPerCount.forEach((key, value) -> {
                if ((value + finalCurrentRowIndex) >= rowCount) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            });
            System.out.print("\n");
        }

        charPerCount.keySet().forEach(System.out::print);
    }
}
