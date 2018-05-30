package com.matveyenka.spring.repository;

import com.matveyenka.spring.config.TestConfiguration;
import com.matveyenka.spring.entity.Genre;
import com.matveyenka.spring.entity.Movie;
import com.matveyenka.spring.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private MovieRepository movieRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void checkFindAllMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        List<Object> values = new ArrayList<>();
        movies.forEach(values::add);
        final int expectedSize = 14;
        assertThat(values, hasSize(expectedSize));
    }

    @Test
    public void checkDeletion() {
        Iterable<Movie> all = movieRepository.findAll();
    }

    @Test
    public void checkFindByName() {
        Optional<Movie> interstellar = movieRepository.findByNameAndDirectorFirstName("Interstellar", "Christopher");
        assertTrue(interstellar.isPresent());
    }

    @Test
    public void test1() {
        Stream<Movie> christopher = movieRepository.findAllByDirectorFirstNameLike("%ristop%");
        System.out.println();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Movie> firstByGenre = movieRepository.findFirstByGenre(Genre.COMEDY);
        //asdgfsdfg
        assertNotNull(firstByGenre.get());
        System.out.println();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Movie>> top2ByGenre = movieRepository.findTop2ByGenre(Genre.ACTION);
        System.out.println(top2ByGenre.get());
    }

    @Test
    public void test4() {
        List<Movie> allByReleaseYearBetween = movieRepository.findAllByReleaseYearBetweenOrderByReleaseYear(1900, 2000, PageRequest.of(0, 2));
        System.out.println();
    }

    @Test
    public void test5() {
        List<Movie> movies = movieRepository.customMethod("Interstellar");
        System.out.println();
    }

    @Test
    public void test7() {
        Movie interstellar = movieRepository.getMovieByName("Interstellar");
        assertNotNull(interstellar);
    }

    @Test
    public void test8() {
        int actual = movieRepository.updateMovie(1800, "Interstellar");
        final int expected = 1;
        assertEquals(expected, actual);
    }
}
