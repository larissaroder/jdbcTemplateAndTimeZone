package br.com.db1.model;

import br.com.db1.exception.MovieSQLException;

import java.util.List;

public interface MovieDAO {

    void insertBatch(List<Movie> movies) throws MovieSQLException;

    void updateBatch(List<Movie> movies) throws MovieSQLException;

    void deleteBatch(List<Movie> movies) throws MovieSQLException;

    List<Movie> loadAllMovies() throws MovieSQLException;
}
