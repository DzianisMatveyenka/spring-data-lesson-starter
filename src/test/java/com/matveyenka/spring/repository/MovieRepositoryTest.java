package com.matveyenka.spring.repository;

import com.matveyenka.spring.config.TestConfiguration;
import com.matveyenka.spring.util.DatabaseHelper;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }
}
