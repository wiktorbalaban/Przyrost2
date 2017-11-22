import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.model.Tournament;
import hibernate.model.Warrior;
import random.RandomTournament;
import random.RandomWarrior;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreatePrzyrost2Data {

    public static void main(String[] args) {
        System.out.println("CreatePrzyrost2Data Start");

        ArrayList<Warrior> warriors = new ArrayList<>();
        RandomWarrior randomWarrior = new RandomWarrior();
        for (int i = 0; i < 1000; i++) {
            warriors.add(randomWarrior.get());
        }

        ArrayList<Tournament> tournaments = new ArrayList<>();
        RandomTournament randomTournament = new RandomTournament();
        for (int i = 0; i < 10; i++) {
            tournaments.add(randomTournament.get(warriors, 100));
        }
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            objectMapper.writeValue(new File("data/json/tournaments"),tournaments);
            objectMapper.writeValue(new File("data/json/warriors"),warriors);
            objectMapper.writeValue(new File("data/json/techniques"),randomWarrior.getTechniques());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
