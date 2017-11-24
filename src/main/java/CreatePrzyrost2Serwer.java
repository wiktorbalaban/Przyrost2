import hibernate.model.*;
import jackson.DataDeSerializer;
import random.RandomTournament;
import random.RandomWarrior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

            RandomTournament randomTournament=new RandomTournament();
            for (Arena a: randomTournament.getArenas()
                    ) {
                entityManager.persist(a);
            }
            for(int j=0;j<10;j++) {
                tournaments.add(randomTournament.getWithoutArenaAndParticipants());
                ArrayList<Integer> integers = new ArrayList<>();

                int howMany= 100;
                for(int i=0;i<warriors.size();i++){
                    integers.add(i);
                }
                for (int i = 0; i < howMany && i < warriors.size(); i++) {
                    int integerIndex = getRandomIntBetween(0, integers.size());
                    int toTake = integers.get(integerIndex);
                    tournaments.get(j).getParticipants().add(warriors.get(toTake));
                    integers.remove(integerIndex);
                }
                tournaments.get(j).setArena(randomTournament.getArenas().get(getRandomIntBetween(0, randomTournament.getArenas().size())));
                entityManager.persist(tournaments.get(j));
            }

            //zakoncz transakcje
            entityManager.getTransaction().commit();

            DataDeSerializer.serializeToJsonFile("data/json/createPrzyrost/warriors.json",warriors);
            System.out.println("Done");

            entityManager.close();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            if (entityManager != null) entityManagerFactory.close();
        }
    }
}
