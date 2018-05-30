package com.matveyenka.spring.repository;

import com.matveyenka.spring.entity.Genre;
import com.matveyenka.spring.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, CustomMovieRepository {

    Optional<Movie> findByNameAndDirectorFirstName(String name, String firstName);

    @Query(nativeQuery = true,
            value = "select m.* from movie_storage.movie m where m.name = :name2")
    List<Movie> customMethod(@Param("name2") String name);

    Stream<Movie> findAllByDirectorFirstNameLike(String firstName);

    CompletableFuture<Movie> findFirstByGenre(Genre genre);

    CompletableFuture<List<Movie>> findTop2ByGenre(Genre genre);

    List<Movie> findAllByReleaseYearBetweenOrderByReleaseYear(Integer start, Integer end, Pageable pageable);

    @Modifying
    @Query("update Movie m set m.releaseYear = :rYear where m.name = :mName")
    int updateMovie(@Param("rYear") Integer year, @Param("mName") String name);

}
