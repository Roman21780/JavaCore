package WorkingWithNumbers;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        List<Integer> positiveNumbers = new ArrayList<>();

        // фильтрация положительных чисел
        for (Integer number: intList) {
            if (number > 0) {
                positiveNumbers.add(number);
            }
        }

        // фильтрация четных чисел
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer number: positiveNumbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            }
        }

        // сортировка чисел
        Collections.sort(evenNumbers);

        // вывод результата
        for (Integer number: evenNumbers) {
            System.out.println(number);
        }
    }
}
