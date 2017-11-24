package jackson;

import hibernate.model.Warrior;
import org.junit.Before;
import org.junit.Test;
import random.RandomWarrior;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataDeSerializerTest {
    private RandomWarrior randomWarrior;
    @Before
    public void setUp() throws Exception {
        randomWarrior=new RandomWarrior();
    }

    @Test
    public void deserializeToJsonFileSerializeFromFile() throws Exception {
        ArrayList<Warrior> warriors=new ArrayList<>();
        for(int i=0;i<10;i++){
            warriors.add(randomWarrior.get());
        }
        DataDeSerializer.serializeToJsonFile("data/json/testJson.json",warriors);
        ArrayList<Warrior> warriorsFromJson=DataDeSerializer.deserializeFromJsonFile("data/json/testJson.json");
        boolean result=true;
        for(int i=0;i<10;i++){
            if(!warriors.get(i).sameAs(warriorsFromJson.get(i))){
                result=false;
                break;
            }
        }
        assertTrue(result);
    }

//    @Test
//    public void deserializeToXmlFileSerializeFromFile() throws Exception {
//        ArrayList<Warrior> warriors=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            warriors.add(randomWarrior.get());
//        }
//        DataDeSerializer.serializeToXmlFile("data/xml/test.xml",warriors);
//        ArrayList<Warrior> warriorsFromXml=DataDeSerializer.deserializeFromXmlFile("data/xml/test.xml");
//        boolean result=true;
//        for(int i=0;i<10;i++){
//            if(!warriors.get(i).sameAs(warriorsFromXml.get(i))){
//                result=false;
//                break;
//            }
//        }
//        assertTrue(result);
//    }

}