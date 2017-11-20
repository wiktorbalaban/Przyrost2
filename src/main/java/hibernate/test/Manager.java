package hibernate.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class Manager {

    public static void main(String[] args) {

        System.out.println("TestManagerStart");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {
            //taka nazwa jak w persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            TestTable table = new TestTable();
            table.setTableName("Pierwsza");
            table.setColumnCount(5);

            entityManager.persist(table);

            TestTable tableFromBase = entityManager.find(TestTable.class, table.getId());

            System.out.println("TestTable " + tableFromBase.getId() + " " + tableFromBase.getTableName() +
                    " have " + tableFromBase.getColumnCount() + " columns");

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
