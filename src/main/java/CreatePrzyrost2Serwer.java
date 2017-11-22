import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.model.Tournament;
import hibernate.model.Warrior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreatePrzyrost2Serwer {
    public static void main(String[] args) {

        System.out.println("CreateTournametTableTestStart");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            ArrayList<Warrior>warriors=new ArrayList<>();
            ArrayList<Tournament>tournaments=new ArrayList<>();

            ObjectMapper objectMapper=new ObjectMapper();
            try {
                warriors = objectMapper.readValue(new File("data/json/warriors"),  new TypeReference<ArrayList<Warrior>>(){});
                tournaments = objectMapper.readValue(new File("data/json/tournaments"),  new TypeReference<ArrayList<Tournament>>(){});

                for (Warrior warrior: warriors
                     ) {
                    entityManager.persist(warrior);
                }
                for (Tournament tournament: tournaments
                        ) {
                    entityManager.persist(tournament);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //zakoncz transakcje
            entityManager.getTransaction().commit();

            System.out.println("Done");

            entityManager.close();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            if (entityManager != null) entityManagerFactory.close();
        }
    }
}
