package hibernate.test;

import hibernate.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class CreateWarriorTableTest {

    public static void main(String[] args) {

        System.out.println("CreateWarriorTableTestStart");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Warrior warrior = createTestWarrior(entityManager);

            Warrior warriorFromBase = entityManager.find(Warrior.class, warrior.getId());
            String message = "Wojownik " + warriorFromBase.getSurname() + " " + warriorFromBase.getName()
                    + " o ksywie " + warriorFromBase.getNickname().getName()
                    + " i mocy " + warriorFromBase.getPower()
                    + " jednostek zna techniki";
            StringBuilder stringBuilder = new StringBuilder();
            for (Technique t : warriorFromBase.getTechniques()
                    ) {
                stringBuilder.append(" ");
                stringBuilder.append(t.getName());
            }
            message += stringBuilder
                    + " i uczy się w szkole walki " + warriorFromBase.getFightingSchool().getName() + ".";
            System.out.println(message);

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

    public static Warrior createTestWarrior(EntityManager entityManager) {
        Technique kamehamehaTechnique = new Technique();
        kamehamehaTechnique.setName("Kamehameha");
        kamehamehaTechnique.setPercentageToPower(30);
        entityManager.persist(kamehamehaTechnique);

        Nickname nickname = new Nickname();
        nickname.setName("Kakarot");
        entityManager.persist(nickname);

        FightingSchool fightingSchool = new FightingSchool();
        fightingSchool.setName("Szkoła Żółwia");
        fightingSchool.setPercentageToPower(10);
        entityManager.persist(fightingSchool);

        Warrior warrior = new Warrior();
        warrior.setName("Goku");
        warrior.setSurname("Son");
        warrior.setNickname(nickname);
        warrior.setPower(9008);
        warrior.getTechniques().add(kamehamehaTechnique);
        warrior.setFightingSchool(fightingSchool);
        entityManager.persist(warrior);
        return warrior;
    }

}
