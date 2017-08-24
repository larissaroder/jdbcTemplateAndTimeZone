package br.com.db1.model.batch;

import br.com.db1.model.Movie;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class UpdateBatch implements BatchPreparedStatementSetter {

    private List<Movie> movies;

    public UpdateBatch(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
        Movie movie = movies.get(i);
        preparedStatement.setString(1, movie.getName());
        preparedStatement.setString(2, movie.getDateHour());
        preparedStatement.setInt(3, movie.getId());
    }

    @Override
    public int getBatchSize() {
        return movies.size();
    }
}
