package jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.model.Warrior;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class DataDeSerializer {

    public static void deserializeToFile(String filePath, ArrayList<Warrior> warriors){
        ObjectMapper mapper=new ObjectMapper();
        try {
            mapper.writeValue(new File(filePath), warriors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static  ArrayList<Warrior> serializeFromFile(String filePath){
        ObjectMapper mapper=new ObjectMapper();
        ArrayList<Warrior> result=new ArrayList<>();
        try {
            result = mapper.readValue(new File(filePath), new TypeReference<ArrayList<Warrior>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
