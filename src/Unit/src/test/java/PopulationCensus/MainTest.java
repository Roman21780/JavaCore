package PopulationCensus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainTest {

    @Test
    public void testCountMinors_validArgument_success() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 17, Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 20, Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 15, Sex.MAN, Education.FURTHER)
        );

        // when:
        long minorsCount = Main.countMinors(persons);

        // then:
        Assertions.assertEquals(2, minorsCount, "Количество несовершеннолетних должно быть 2");
    }

    @Test
    public void testGetConscripts_validArgument_success() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 20, Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 17, Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 25, Sex.MAN, Education.FURTHER)
        );

        // when:
        List<String> conscripts = Main.getConscripts(persons);

        // then:
        Assertions.assertIterableEquals(Arrays.asList("Evans", "Harris"), conscripts, "Список фамилий призывников должен содержать Evans и Harris");
    }

    @Test
    public void testGetEmployedPeople_validArgument_success() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 25, Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 17, Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 30, Sex.WOMAN, Education.HIGHER),
                new Person("George", "Wilson", 40, Sex.MAN, Education.FURTHER)
        );

        // when:
        List<Person> employedPeople = Main.getEmployedPeople(persons);

        // then:
        Assertions.assertEquals(2, employedPeople.size(), "Должно быть 2 человека с высшим образованием");
        Assertions.assertEquals("Evans", employedPeople.get(0).getFamily(), "Первая фамилия должна быть Evans");
        Assertions.assertEquals("Harris", employedPeople.get(1).getFamily(), "Вторая фамилия должна быть Harris");
    }

    @Test
    public void testGeneratePersons_validArgument_success() {
        // given:
        int count = 5;

        // when:
        Collection<Person> persons = Main.generatePersons(count);

        // then:
        Assertions.assertEquals(count, persons.size(), "Количество сгенерированных людей должно быть " + count);
        for (Person person : persons) {
            Assertions.assertNotNull(person.getName(), "Имя не должно быть null");
            Assertions.assertNotNull(person.getFamily(), "Фамилия не должна быть null");
            Assertions.assertTrue(person.getAge() >= 0 && person.getAge() < 100, "Возраст должен быть от 0 до 99");
            Assertions.assertNotNull(person.getSex(), "Пол не должен быть null");
            Assertions.assertNotNull(person.getEducation(), "Образование не должно быть null");
        }
    }
}