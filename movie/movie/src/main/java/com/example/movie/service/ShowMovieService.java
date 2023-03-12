package com.example.movie.service;

import com.example.movie.process.ShowMovie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowMovieService {
    private final ShowMovie showMovie;

    public void insert() {
        showMovie.getMovie();
    }
}
