import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "src/main/resources/data.csv";
        String fileName2 = "src/main/resources/data.xml";
        String fileName3 = "src/main/resources/new_data.json";

        List<Employee> list = parseCSV(columnMapping, fileName);
        List<Employee> list2 = parseXML(fileName2);

        if (list != null) {
            String json = listToJson(list);
            writeString(json, "data.json");
            System.out.println("JSON записан в файл data.json");
        } else {
            System.out.println("Ошибка при чтении CSV-файла.");
        }

        if (list2 != null) {
            String json2 = listToJson(list2);
            writeString(json2, "data2.json");
            System.out.println("JSON2 записан в файл data2.json");
        } else {
            System.out.println("Ошибка при чтении XML-файла.");
        }

        // получение JSON из файла
        String json3 = readString(fileName3);

        if (json3 != null) {
            // преобразование json3 в список сотрудников
            List<Employee> list3 = jsonToList(json3);

            // Выводим список в консоль
            if (list3 != null) {
                list3.forEach(System.out::println);
            } else {
                System.out.println("Ошибка при преобразоваании JSON3 в список.");
            }
        } else {
            System.out.println("Ошибка при чтении файла");
        }
    }


    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            ColumnPositionMappingStrategy<Employee> strategy =
                    new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(fileReader)
                    .withMappingStrategy(strategy)
                    .build();

            return csv.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String listToJson(List<Employee> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    private static void writeString(String json, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> parseXML(String fileName) {
        List<Employee> employees = new ArrayList<>();

        try {
            // Создаем фабрику для создания парсера
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсим XML-файл
            Document document = builder.parse(new File(fileName));

            // Получаем корневой элемент
            Element root = document.getDocumentElement();

            // Получаем список всех элементов <employee>
            NodeList nodeList = root.getElementsByTagName("employee");

            // Проходим по каждому элементу <employee>
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Извлекаем данные из кждого элемента
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());

                    // Создаем объект Employee и добавляем его в список
                    Employee employee = new Employee(id, firstName, lastName, country, age);
                    employees.add(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return employees;
    }

    private static String readString(String fileName) {
        StringBuilder json = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json.toString();
    }

    private static List<Employee> jsonToList(String json) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
}










