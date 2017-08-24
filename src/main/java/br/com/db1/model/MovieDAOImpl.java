package br.com.db1.model;

import br.com.db1.exception.MovieSQLException;
import br.com.db1.model.batch.DeleteBatch;
import br.com.db1.model.batch.InsertBatch;
import br.com.db1.model.batch.UpdateBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MovieDAOImpl extends JdbcDaoSupport implements MovieDAO {

    private static final String SQL_INSERT = "INSERT INTO movie " +
            "(name, date_hour) VALUES (?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT * FROM movie";

    private static final String SQL_UPDATE = "UPDATE movie SET name=?, date_hour=? WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM movie WHERE id=?";

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insertBatch(List<Movie> movies) throws MovieSQLException {
        InsertBatch batch = new InsertBatch(movies);
        getJdbcTemplate().batchUpdate(SQL_INSERT, batch);
    }

    @Override
    public void updateBatch(List<Movie> movies) throws MovieSQLException {
        UpdateBatch batch = new UpdateBatch(movies);
        getJdbcTemplate().batchUpdate(SQL_UPDATE, batch);
    }

    @Override
    public void deleteBatch(List<Movie> movies) throws MovieSQLException {
        DeleteBatch batch = new DeleteBatch(movies);
        getJdbcTemplate().batchUpdate(SQL_DELETE, batch);
    }

    @Override
    public List<Movie> loadAllMovies() throws MovieSQLException {
        getJdbcTemplate().setFetchSize(200);
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(SQL_SELECT_ALL);

        List<Movie> result = new ArrayList<>();
        rows.forEach((Map<String, Object> row) -> {
            Integer id = (Integer) row.get("id");
            String name = (String) row.get("name");
            String dateHour = (String) row.get("date_hour");
            Movie movie = new Movie(id, name, dateHour);
            result.add(movie);
        });
        return result;
    }
}
