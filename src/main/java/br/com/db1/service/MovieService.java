package br.com.db1.service;

import br.com.db1.exception.MovieSQLException;
import br.com.db1.model.Movie;

import java.util.List;

public interface MovieService {

    void insertBatch(List<Movie> movies) throws MovieSQLException;
    void updateBatch (List<Movie> movies) throws MovieSQLException;
    void deleteBatch (List<Movie> movies) throws MovieSQLException;
    List<Movie> loadAllMovies() throws MovieSQLException;
}
