package br.com.db1.model.batch;

import br.com.db1.model.Movie;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class DeleteBatch implements BatchPreparedStatementSetter {

    private List<Movie> movies;

    public DeleteBatch(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
        Movie movie = movies.get(i);
        preparedStatement.setInt(1, movie.getId());
    }

    @Override
    public int getBatchSize() {
        return movies.size();
    }
}
