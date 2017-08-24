package br.com.db1.service;

import br.com.db1.exception.MovieSQLException;
import br.com.db1.model.Movie;
import br.com.db1.model.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private static final String ERROR_INSERT = "Ocorreu um erro ao inserir os dados";
    private static final String ERROR_UPDATE = "Ocorreu um erro ao atualizar os dados";
    private static final String ERROR_DELETE = "Ocorreu um erro ao delete os dados";
    private static final String ERROR_SELECT = "Ocorreu um erro ao obter os dados";

    @Autowired
    private MovieDAO movieDAO;


    @Override
    public void insertBatch(List<Movie> movies) throws MovieSQLException {
        try {
            movieDAO.insertBatch(movies);
        } catch (MovieSQLException e) {
            throw new MovieSQLException(ERROR_INSERT);
        }
    }

    @Override
    public void updateBatch(List<Movie> movies) throws MovieSQLException {
        try {
            movieDAO.updateBatch(movies);
        } catch (MovieSQLException e) {
            throw new MovieSQLException(ERROR_UPDATE);
        }
    }

    @Override
    public void deleteBatch(List<Movie> movies) throws MovieSQLException {
        try {
            movieDAO.deleteBatch(movies);
        } catch (MovieSQLException e) {
            throw new MovieSQLException(ERROR_DELETE);
        }
    }

    @Override
    public List<Movie> loadAllMovies() throws MovieSQLException {
        try {
            return movieDAO.loadAllMovies();
        } catch (MovieSQLException e) {
            throw new MovieSQLException(ERROR_SELECT);
        }
    }
}
