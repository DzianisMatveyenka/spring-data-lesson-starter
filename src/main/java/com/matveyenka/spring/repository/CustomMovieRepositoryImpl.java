package com.matveyenka.spring.repository;

import com.matveyenka.spring.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Movie getMovieByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Movie> criteria = cb.createQuery(Movie.class);

        Root<Movie> root = criteria.from(Movie.class);
        criteria.select(root).where(cb.equal(root.get("name"), name));

        List<Movie> resultList = entityManager.createQuery(criteria).getResultList();

        return resultList.stream().findFirst().orElse(null);
    }
}
