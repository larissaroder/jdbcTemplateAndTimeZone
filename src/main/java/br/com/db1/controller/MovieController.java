package br.com.db1.controller;


import br.com.db1.exception.MovieSQLException;
import br.com.db1.model.Movie;
import br.com.db1.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMovies() {
        try {
            return new ResponseEntity<>(movieService.loadAllMovies(), HttpStatus.OK);
        } catch (MovieSQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody List<Movie> movies) {
        try {
            movieService.insertBatch(movies);
            return new ResponseEntity<>("Filme(s) inserido com sucesso", HttpStatus.OK);
        } catch (MovieSQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/movies", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody List<Movie> movies) {
        try {
            movieService.updateBatch(movies);
            return new ResponseEntity<>("Filme(s) Atualizado com sucesso", HttpStatus.OK);
        } catch (MovieSQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/movies", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody List<Movie> movies) {
        try {
            movieService.deleteBatch(movies);
            return new ResponseEntity<>("Filme(s) Excluído com sucesso", HttpStatus.OK);
        } catch (MovieSQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
