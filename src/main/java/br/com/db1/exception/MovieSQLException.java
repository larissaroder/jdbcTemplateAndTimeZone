package br.com.db1.exception;

import java.sql.SQLException;

public class MovieSQLException extends SQLException {

    public MovieSQLException() {
    }

    public MovieSQLException(String message) {
        super(message);
    }
}
