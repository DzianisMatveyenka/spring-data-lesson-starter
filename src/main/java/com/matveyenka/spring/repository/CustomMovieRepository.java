package com.matveyenka.spring.repository;

import com.matveyenka.spring.entity.Movie;

public interface CustomMovieRepository {

    Movie getMovieByName(String name);
}
