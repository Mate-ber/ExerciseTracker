package com.harbourspace.tracker.activity.jdbs;

import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.activity.model.NewActivity;
import com.harbourspace.tracker.user.jdbc.UserJdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ActivityJdbcRepository {

    private Logger logger = LoggerFactory.getLogger(UserJdbcRepository.class);

    protected final JdbcTemplate jdbcTemplate;

    public ActivityJdbcRepository(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    public List<Activity> selectAll() {
        logger.debug("Selecting all activities");
        return jdbcTemplate.query("SELECT id, user_id, name, CASE user_id WHEN 0 THEN 'SYSTEM' ELSE 'USER' END type , kcal_per_minute FROM activity", (resultSet, index) -> new Activity(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getString("name"),
                resultSet.getString("type"),
                resultSet.getDouble("kcal_per_minute")
        ));
    }

    public Activity selectById(Long id) {
        logger.debug("Selecting activity" + id);
        return jdbcTemplate.query("SELECT * FROM activity WHERE id = ? LIMIT 1", this::rowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Activity> selectByType(String type) {
        logger.debug("Selecting  type" + type);

        String query =  "SELECT id, user_id, name, CASE user_id WHEN 0 THEN 'SYSTEM' ELSE 'USER' END type , kcal_per_minute FROM activity WHERE user_id > 0";

        if(type.equals("SYSTEM")) query = "SELECT id, user_id, name, CASE user_id WHEN 0 THEN 'SYSTEM' ELSE 'USER' END type , kcal_per_minute FROM activity WHERE user_id = 0";

        return jdbcTemplate.query(query, (resultSet, index) -> new Activity(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getString("name"),
                resultSet.getString("type"),
                resultSet.getDouble("kcal_per_minute")
        ));

    }

    public Activity insert(NewActivity activity) {
        logger.debug("Inserting new activity: " + activity);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (INSERT INTO activity (user_id, name, kcal_per_minute) VALUES (?,?,?))",
                this::rowMapper,
                activity.userId(),
                activity.name(),
                activity.kcalPerMinute()
        ).stream().findFirst().orElse(null);
    }

    public Activity update(Activity activity) {
        logger.debug("Updating user: " + activity);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (UPDATE activity SET name = ?, kcal_per_minute = ? WHERE id = ? AND user_id > 0)",
                this::rowMapper,
                activity.name(),
                activity.kcalPerMinute(),
                activity.id()
        ).stream().findFirst().orElse(null);
    }

    public void delete(Long id) {
        logger.debug("Deleting activity " + id);
        jdbcTemplate.update("DELETE FROM activity WHERE id = ?", id);
    }

    private Activity rowMapper(ResultSet rs, int i) throws SQLException {
        String type = "USER";

        if( rs.getLong("user_id") == 0 ) type = "SYSTEM";

        return new Activity(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("name"),
                type,
                rs.getDouble("kcal_per_minute")
        );
    }

}