import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.model.FightingSchool;
import hibernate.model.Technique;
import hibernate.model.Tournament;
import hibernate.model.Warrior;
import random.RandomWarrior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static random.RandomWarrior.getRandomIntBetween;

public class CreatePrzyrost2Serwer {
    public static void main(String[] args) {

        System.out.println("CreateTournametTableTestStart");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
//
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            RandomWarrior randomWarrior=new RandomWarrior();

            for (FightingSchool a: randomWarrior.getFightingSchools()
                        ) {
                    entityManager.persist(a);
                }
            for (Technique a: randomWarrior.getTechniques()
                    ) {
                entityManager.persist(a);
            }

            ArrayList<Warrior>warriors=new ArrayList<>();
            ArrayList<Tournament>tournaments=new ArrayList<>();
            for(int j=0;j<1000;j++){
                warriors.add(randomWarrior.getWithoutRandomSchoolAndTechnicks());
                ArrayList<Integer> integers = new ArrayList<>();

                int howMany= getRandomIntBetween(0,11);
                for(int i=0;i<randomWarrior.getTechniques().size();i++){
                    integers.add(i);
                }
                for (int i = 0; i < howMany && i < randomWarrior.getTechniques().size(); i++) {
                    int integerIndex = getRandomIntBetween(0, integers.size());
                    int toTake = integers.get(integerIndex);
                    warriors.get(j).getTechniques().add(randomWarrior.getTechniques().get(toTake));
                    integers.remove(integerIndex);
                }
                warriors.get(j).setFightingSchool(randomWarrior.getFightingSchools().get(getRandomIntBetween(0, randomWarrior.getFightingSchools().size())));
                entityManager.persist(warriors.get(j));
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
