package com.example.movie.service;

import com.example.movie.process.ShowMovie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowMovieServiceTest {
    @Autowired ShowMovie showMovie;
    @Test
    public void insert1() {
        showMovie.getMovie();
    }
}