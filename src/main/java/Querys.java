import hibernate.model.Warrior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Querys {
    public static void main(String[] args) {

        System.out.println("Querys Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;


        try {
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            //utworz entityManagera
            entityManager = entityManagerFactory.createEntityManager();

            //rozpocznij transakcje
            entityManager.getTransaction().begin();

            List<Warrior> warriors=getWarriorsQuery(entityManager);
            //getWarriorsWhoHasThisTechniqueQuery(entityManager,"Kamehame-Ha");

            //zakoncz transakcje
            entityManager.getTransaction().commit();

            System.out.println("Done");

            entityManager.close();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }


    }
    static List<Warrior> getWarriorsQuery(EntityManager entityManager){
        TypedQuery<Warrior> query =entityManager.createQuery("SELECT k FROM Warrior k", Warrior.class);
        return query.getResultList();
    }
//    static List<Warrior> getWarriorsWhoHasThisTechniqueQuery(EntityManager entityManager,String techniqueName){
//        String queryString="select w from Technique as t inner join Warrior_technique as wt on t.name = :techniquename"
//        +" and wt.techniqueId = t.id inner join Warrior as w on w.id = wt.wariorId";
//        TypedQuery<Warrior> query =entityManager.createQuery(queryString, Warrior.class);
//        return query.setParameter("techniquename",techniqueName).getResultList();
//    }
}
