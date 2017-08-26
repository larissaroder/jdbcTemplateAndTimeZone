package br.com.db1.service;

import br.com.db1.exception.TimeZoneException;
import br.com.db1.infraestructure.TimeZoneMovie;
import br.com.db1.model.Movie;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeZoneMovieService implements TimeZoneMovie<Movie> {

    private static final String INCORRECT_TIME_ZONE = "Time Zone Inexistente";
    private String timeZone;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TimeZoneMovieService(String timeZone) {
        this.timeZone = timeZone.trim();
    }

    @Override
    public List<Movie> changedTimeZone(List<Movie> movies) throws TimeZoneException {
        if (!hasCorrectTimeZone(timeZone)) {
            throw new TimeZoneException(INCORRECT_TIME_ZONE);
        }
        return changeTimeZone(movies);
    }

    private List<Movie> changeTimeZone(List<Movie> movies) {
        List<Movie> moviesWithTimeZoneUpdated = Lists.newLinkedList();
        movies.forEach(m -> {
            LocalDateTime ldt = getStringToDateTime(m, FORMATTER);
            ZonedDateTime newTimeZoneDate = getDefaultTimeZone(ldt);
            ZonedDateTime newDateTime = getNewTimeZone(newTimeZoneDate, timeZone);
            String newDate = getStringToLocalDateTime(newDateTime, FORMATTER);
            moviesWithTimeZoneUpdated.add(new Movie(m.getId(), m.getName(), newDate));
        });
        return moviesWithTimeZoneUpdated;
    }
}
