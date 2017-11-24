import hibernate.model.*;
import random.RandomWarrior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Manager {

    public static void main(String[] args) {

        System.out.println("ManagerStart");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Warrior myWarrior = new Warrior();
            myWarrior.setSurname("Wiktor");
            myWarrior.setName("Bałaban");
            myWarrior.setPower(10000);

            List<Technique> techniques = Querys.getTechniquesQueryNamedLike(entityManager, "Kamehame-Ha");
            myWarrior.setTechniques(techniques);
            List<FightingSchool> fightingSchools = Querys.getFightingSchoolQueryNamedLike(entityManager, "Szkoła Żółwia");
            if (fightingSchools.size() == 1) {
                myWarrior.setFightingSchool(fightingSchools.get(0));
            }
            Wife wife=new Wife();
            wife.setName("Bałaban");
            wife.setSurname("Ania");
            wife.setPercentageToPower(15);
            entityManager.persist(wife);
            myWarrior.setWife(wife);
            Nickname nickname=new Nickname();
            nickname.setName("El Diablo");
            entityManager.persist(nickname);
            myWarrior.setNickname(nickname);
            entityManager.persist(myWarrior);

            Warrior warriorFromBase=entityManager.find(Warrior.class,myWarrior.getId());


            entityManager.remove(warriorFromBase);


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
