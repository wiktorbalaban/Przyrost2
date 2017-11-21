package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hibernate.model.Warrior;
import hibernate.test.CreateWarriorTableTest;

import java.io.File;
import java.io.IOException;

public class SerializationDeserializationTest {

    public static void main(String[] args) {

        System.out.println("TestJacksona");

        objectMapperTest(new ObjectMapper(), "data/json/warrior.json");
        objectMapperTest(new XmlMapper(), "data/xml/warrior.xml");

        System.out.println("Done");
    }

    static void objectMapperTest(ObjectMapper mapper, String testFilePath) {
        Warrior warrior = CreateWarriorTableTest.createTestWarriorNoWithEntity();
        String warriorJsonString = "";

        try {
            mapper.writeValue(new File(testFilePath), warrior);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            warriorJsonString = mapper.writeValueAsString(warrior);
            System.out.println(warriorJsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Warrior warriorFromJSONFile, warriorFromJSONString;
        try {
            warriorFromJSONFile = mapper.readValue(new File(testFilePath), Warrior.class);
            System.out.println(warriorFromJSONFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            warriorFromJSONString = mapper.readValue(warriorJsonString, Warrior.class);
            System.out.println(warriorFromJSONString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
