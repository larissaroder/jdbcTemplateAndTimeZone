package br.com.db1.controller;

import br.com.db1.exception.MovieSQLException;
import br.com.db1.exception.TimeZoneException;
import br.com.db1.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class TimeZoneController {

    @Autowired
    private MovieServiceImpl movieService;

    @RequestMapping(value = "/movies-time-zone", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMovies(@RequestParam String timeZone) {
        try {
            return new ResponseEntity<>(movieService.changeTimeZone(timeZone), HttpStatus.OK);
        } catch (MovieSQLException | TimeZoneException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
