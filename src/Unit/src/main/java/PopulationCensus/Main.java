package PopulationCensus;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Генерация данных
        Collection<Person> persons = generatePersons(10_000_000);

        // 1. Найти количество несовершеннолетних
        long minorsCount = countMinors(persons);
        System.out.println("Количество несовершеннолетних: " + minorsCount);

        // 2. Получить список фамилий призывников
        List<String> conscripts = getConscripts(persons);
        System.out.println("Список фамилий призывников: " + conscripts);

        // 3. Получить список потенциально работоспособных людей с высшим образованием
        List<Person> employedPeople = getEmployedPeople(persons);
        System.out.println("Список потенциально работоспособных людей с высшим образованием: " + employedPeople);
    }

    /**
     * Генерация коллекции Person.
     *
     * @param count количество людей для генерации
     * @return коллекция Person
     */
    public static Collection<Person> generatePersons(int count) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)])
            );
        }

        return persons;
    }

    /**
     * Подсчет количества несовершеннолетних.
     *
     * @param persons коллекция Person
     * @return количество несовершеннолетних
     */
    public static long countMinors(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
    }

    /**
     * Получение списка фамилий призывников.
     *
     * @param persons коллекция Person
     * @return список фамилий призывников
     */
    public static List<String> getConscripts(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .toList();
    }

    /**
     * Получение списка потенциально работоспособных людей с высшим образованием.
     *
     * @param persons коллекция Person
     * @return список людей с высшим образованием
     */
    public static List<Person> getEmployedPeople(Collection<Person> persons) {
        return persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60) ||
                        (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
    }
}