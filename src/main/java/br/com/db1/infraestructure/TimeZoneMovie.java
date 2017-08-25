package br.com.db1.infraestructure;

import br.com.db1.exception.TimeZoneException;
import br.com.db1.model.Movie;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public interface TimeZoneMovie<F> {

    List<F> changedTimeZone (List<F> movies) throws TimeZoneException;

    default Boolean hasCorrectTimeZone(String timeZone) {
        return Arrays.stream(TimeZone.getAvailableIDs())
                .anyMatch(id -> id.equals(timeZone));
    }

    default OffsetDateTime getOffsetDateTime(String dateHour, DateTimeFormatter format, String timeZone) {
        LocalDateTime dateTime = LocalDateTime.parse(dateHour, format);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of(timeZone));
        OffsetDateTime timeUtc = dateTime.atOffset(ZoneOffset.UTC);
        return timeUtc.withOffsetSameInstant(zonedDateTime.getOffset());
    }
}
