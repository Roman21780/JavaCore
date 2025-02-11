package PopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)])
            );
        }

        // 1. Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long minorsCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + minorsCount);

        // 2. Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN
                && person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person :: getFamily)
                .toList();
        System.out.println("Список фамилий призывников: " + conscripts);

        // 3. Получить отсортированный по фамилии список потенциально работоспособных людей
        // с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин
        // и до 65 лет для мужчин).
        List<Person> employedPeople = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60) ||
                        (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        System.out.println("Список потенциально работоспособных людей с высшим образованием: " + employedPeople);
    }
}
