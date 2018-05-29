package com.matveyenka.spring.util;

import com.matveyenka.spring.entity.Director;
import com.matveyenka.spring.entity.Genre;
import com.matveyenka.spring.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void cleanDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Movie").executeUpdate();
        entityManager.createQuery("delete from Director").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Director nolanDirector = new Director("Christopher", "Nolan");
        Director fincherDirector = new Director("David", "Fincher");
        Director eastwoodDirector = new Director("Clint", "Eastwood");
        Director scottDirector = new Director("Ridley", "Scott");
        entityManager.persist(nolanDirector);
        entityManager.persist(fincherDirector);
        entityManager.persist(eastwoodDirector);
        entityManager.persist(scottDirector);

        entityManager.persist(new Movie("Interstellar", 2014, Genre.SCIENCE_FICTION, nolanDirector));
        entityManager.persist(new Movie("The Prestige", 2006, Genre.DRAMA, nolanDirector));
        entityManager.persist(new Movie("Memento", 2000, Genre.DRAMA, nolanDirector));
        entityManager.persist(new Movie("Inception", 2010, Genre.ACTION, nolanDirector));

        entityManager.persist(new Movie("Seven", 1995, Genre.THRILLER, fincherDirector));
        entityManager.persist(new Movie("Gone Girl", 2014, Genre.THRILLER, fincherDirector));
        entityManager.persist(new Movie("Fight Club", 1999, Genre.DRAMA, fincherDirector));

        entityManager.persist(new Movie("Gran Torino", 2008, Genre.DRAMA, eastwoodDirector));
        entityManager.persist(new Movie("Million Dollar Baby", 2004, Genre.DRAMA, eastwoodDirector));
        entityManager.persist(new Movie("Mystic River", 2003, Genre.THRILLER, eastwoodDirector));

        entityManager.persist(new Movie("Gladiator", 2000, Genre.ACTION, scottDirector));
        entityManager.persist(new Movie("Alien", 1979, Genre.HORROR, scottDirector));
        entityManager.persist(new Movie("The Martian", 2015, Genre.COMEDY, scottDirector));
        entityManager.persist(new Movie("Blade Runner", 1982, Genre.SCIENCE_FICTION, scottDirector));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
