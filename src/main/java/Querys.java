import hibernate.model.FightingSchool;
import hibernate.model.Technique;
import hibernate.model.Warrior;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
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

            //List<Warrior> warriors=getWarriorsQuery(entityManager);
            List<Warrior> warriors =getWarriorsWhoHasThisTechniqueQuery(entityManager,"Kamehame-Ha");
            List<Warrior> warriorsStronicowanie=getWarriorsStronicowanieQuery(entityManager);
            //List<FightingSchool> fightingSchools=getFightingSchoolsQuery(entityManager);
            //List<Collection> techniques=getWarriorsTechniquesQuery(entityManager);
            //List<Technique> techniques = getTechniquesQuery(entityManager);
            //List<Technique> techniques = getTechniquesQueryNamedLike(entityManager,"");
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

    static List<Warrior> getWarriorsQuery(EntityManager entityManager) {
        TypedQuery<Warrior> query = entityManager.createQuery("SELECT k FROM Warrior k", Warrior.class);
        return query.getResultList();
    }

    static List<Warrior> getWarriorsStronicowanieQuery(EntityManager entityManager) {
        TypedQuery<Warrior> query = entityManager.createQuery("SELECT k FROM Warrior k", Warrior.class);
        int pageNumber = 1;
        int pageSize = 10;
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    static List<FightingSchool> getFightingSchoolsQuery(EntityManager entityManager) {
        TypedQuery<FightingSchool> query = entityManager.createQuery("SELECT k FROM FightingSchool k", FightingSchool.class);
        return query.getResultList();
    }

    static List<Warrior> getWarriorsWitchSurnameQuery(EntityManager entityManager, String surname) {
        TypedQuery<Warrior> query = entityManager.createQuery("SELECT k FROM Warrior k where k.surname = :surname", Warrior.class);
        return query.setParameter("surname", surname).getResultList();
    }

    static List<Warrior> getWarriorsWhoHasThisTechniqueQuery(EntityManager entityManager, String techniqueName) {
        String queryString = "select w from Warrior as w inner join w.techniques as t where t.name = :techniquename";
        TypedQuery<Warrior> query = entityManager.createQuery(queryString, Warrior.class);
        return query.setParameter("techniquename", techniqueName).getResultList();
    }

    static List<Collection> getWarriorsTechniquesQuery(EntityManager entityManager) {
        String queryString = "select w.techniques from Warrior w";
        TypedQuery<Collection> query = entityManager.createQuery(queryString, Collection.class);
        return query.getResultList();
    }

    static List<Technique> getTechniquesQuery(EntityManager entityManager) {
        TypedQuery<Technique> query = entityManager.createQuery("SELECT k FROM Technique k", Technique.class);
        return query.getResultList();
    }

    static List<Technique> getTechniquesQueryNamedLike(EntityManager entityManager,String name) {
        name="%"+name+"%";
        TypedQuery<Technique> query = entityManager.createQuery("SELECT k FROM Technique k where k.name like :name", Technique.class);
        return query.setParameter("name",name).getResultList();
    }
    static List<FightingSchool> getFightingSchoolQueryNamedLike(EntityManager entityManager,String name) {
        name="%"+name+"%";
        TypedQuery<FightingSchool> query = entityManager.createQuery("SELECT k FROM FightingSchool k where k.name like :name", FightingSchool.class);
        return query.setParameter("name",name).getResultList();
    }
}
