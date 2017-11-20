package hibernate.test;

import hibernate.model.Arena;
import hibernate.model.Tournament;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class CreateTournametTableTest {

    public static void main(String[] args) {

        System.out.println("CreateTournametTableTestStart");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Arena arena = new Arena();
            arena.setName("Arena1");

            entityManager.persist(arena);

            Arena arenaFromBase = entityManager.find(Arena.class, arena.getId());

            if (arenaFromBase.getName() == "Arena1") {
                System.out.println("Udało się stworzyć arenę");

                Tournament tournament = new Tournament();
                tournament.setName("Turniej 1");
                tournament.setDate(LocalDate.now());
                tournament.setArena(arena);

                entityManager.persist(tournament);

                Tournament tournamentFromBase = entityManager.find(Tournament.class, tournament.getId());

                String message = "TournamentFromBase " + tournamentFromBase.getName()
                        + " odbył się " + tournamentFromBase.getDate()
                        + " na " + tournamentFromBase.getArena().getName();
                System.out.println(message);
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
