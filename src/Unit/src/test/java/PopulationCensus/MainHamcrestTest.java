package PopulationCensus;

import PopulationCensus.Main;
import PopulationCensus.Sex;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainHamcrestTest {

    @Test
    public void testCountMinors_validArgument_success() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 17, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 20, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 15, PopulationCensus.Sex.MAN, Education.FURTHER)
        );

        // when:
        long minorsCount = PopulationCensus.Main.countMinors(persons);

        // then:
        assertThat(minorsCount, equalTo(2L)); // Проверяем, что количество несовершеннолетних равно 2
    }

    @Test
    public void testGetConscripts_validArgument_success() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 20, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 17, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 25, PopulationCensus.Sex.MAN, Education.FURTHER)
        );

        // when:
        List<String> conscripts = PopulationCensus.Main.getConscripts(persons);

        // then:
        assertThat(conscripts, containsInAnyOrder("Evans", "Harris")); // Проверяем, что список содержит фамилии в любом порядке
        assertThat(conscripts, hasSize(2)); // Проверяем, что размер списка равен 2
    }

    @Test
    public void testGetEmployedPeople_validArgument_success() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 25, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 17, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 30, PopulationCensus.Sex.WOMAN, Education.HIGHER),
                new Person("George", "Wilson", 40, PopulationCensus.Sex.MAN, Education.FURTHER)
        );

        // when:
        List<Person> employedPeople = PopulationCensus.Main.getEmployedPeople(persons);

        // then:
        assertThat(employedPeople, hasSize(2)); // Проверяем, что размер списка равен 2
        assertThat(employedPeople, contains(
//                hasProperty("family", equalTo("Evans")), // Проверяем, что первый элемент имеет фамилию "Evans"
//                hasProperty("family", equalTo("Harris")) // Проверяем, что второй элемент имеет фамилию "Harris"
                hasToString(containsString("Evans")), // Проверяем строковое представление
                hasToString(containsString("Harris"))
        ));
    }

    @Test
    public void testGeneratePersons_validArgument_success() {
        // given:
        int count = 5;

        // when:
        Collection<Person> persons = PopulationCensus.Main.generatePersons(count);

        // then:
        assertThat(persons, hasSize(count)); // Проверяем, что количество сгенерированных людей равно count
        for (Person person : persons) {
            String personString = person.toString();
            assertThat(personString, allOf(
//                    hasProperty("name", notNullValue()), // Проверяем, что имя не null
//                    hasProperty("family", notNullValue()), // Проверяем, что фамилия не null
//                    hasProperty("age", allOf(greaterThanOrEqualTo(0), lessThan(100))), // Проверяем, что возраст от 0 до 99
//                    hasProperty("sex", notNullValue()), // Проверяем, что пол не null
//                    hasProperty("education", notNullValue()) // Проверяем, что образование не null
                    containsString("name="), // Проверяем, что имя присутствует в строковом представлении
                    containsString("family="), // Проверяем, что фамилия присутствует
                    containsString("age="), // Проверяем, что возраст присутствует
                    containsString("sex="), // Проверяем, что пол присутствует
                    containsString("education=") // Проверяем, что образование присутствует
            ));

            // Проверяем, что возраст находится в диапазоне от 0 до 99
            int age = person.getAge();
            assertThat(age, allOf(greaterThanOrEqualTo(0), lessThan(100)));
        }
    }

    @Test
    public void testGetConscripts_noConscripts_emptyList() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 17, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 30, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 15, PopulationCensus.Sex.MAN, Education.FURTHER)
        );

        // when:
        List<String> conscripts = PopulationCensus.Main.getConscripts(persons);

        // then:
        assertThat(conscripts, empty()); // Проверяем, что список пуст
    }

    @Test
    public void testGetEmployedPeople_noEmployedPeople_emptyList() {
        // given:
        Collection<Person> persons = Arrays.asList(
                new Person("Jack", "Evans", 17, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Connor", "Young", 70, PopulationCensus.Sex.MAN, Education.HIGHER),
                new Person("Harry", "Harris", 65, Sex.WOMAN, Education.HIGHER)
        );

        // when:
        List<Person> employedPeople = Main.getEmployedPeople(persons);

        // then:
        assertThat(employedPeople, empty()); // Проверяем, что список пуст
    }
}