package com.harbourspace.tracker.exercise.jdbc;


import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.exercise.model.Exercise;
import com.harbourspace.tracker.exercise.model.NewExercise;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;


@Repository
public class ExerciseJdbcRepository {

    private Logger logger = LoggerFactory.getLogger(ExerciseJdbcRepository.class);

    protected final JdbcTemplate jdbcTemplate;

    public ExerciseJdbcRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    public List<Exercise> selectAll() {
        logger.debug("Selecting all users");
        return jdbcTemplate.query("select e.id, e.user_id, e.activity_id, e.start_time , e.duration, a.kcal_per_minute*e.duration/60 kcal_burned from exercise e, activity a where e.activity_id=a. id", (resultSet, index) -> new Exercise(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                resultSet.getLong("activity_id"),
                resultSet.getTimestamp("start_time"),
                resultSet.getLong("duration"),
                resultSet.getDouble("kcal_burned")
        ));
    }

    public List<Exercise> selectByFilter(Date date, Long activityId, Long duration) {
        logger.debug("Selecting all users");
        long updatetime = date.getTime() + duration*1000;
        Date date2 = new Date(updatetime);
        return jdbcTemplate.query(
                "SELECT e.id, e.user_id, e.activity_id, e.start_time, e.duration, a.kcal_per_minute*e.duration/60 kcal_burned " +
                        "FROM exercise e, activity a " +
                        "WHERE e.activity_id = a.id AND a.id = ? AND e.start_time >= ? AND e.start_time <= ?",
                (resultSet, index) -> new Exercise(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("activity_id"),
                        resultSet.getTimestamp("start_time"),
                        resultSet.getLong("duration"),
                        resultSet.getDouble("kcal_burned")
                ),
                activityId, date, date2
        );
    }


    public Exercise selectById(Long id) {
        logger.debug("Selecting exercise" + id);
        return jdbcTemplate.query("SELECT * FROM exercise WHERE id = ? LIMIT 1", this::rowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Exercise insert(NewExercise exercise) {
        logger.debug("Inserting new exercise: " + exercise);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (INSERT INTO exercise (user_id, activity_id, start_time, duration) VALUES (?,?,?,?))",
                this::rowMapper,
                exercise.userId(),
                exercise.activityId(),
                exercise.startTime(),
                exercise.duration()
        ).stream().findFirst().orElse(null);
    }

    public Exercise update(Exercise exercise) {
        logger.debug("Updating exercise: " + exercise);
        return jdbcTemplate.query(
                "SELECT * FROM FINAL TABLE (UPDATE exercise SET activity_id = ? , start_time = ? , duration = ? WHERE id = ?)",
                this::rowMapper,
                exercise.activityId(),
                exercise.startTime(),
                exercise.duration(),
                exercise.id()
        ).stream().findFirst().orElse(null);
    }

    public void delete(Long id) {
        logger.debug("Deleting exercise " + id);
        jdbcTemplate.update("DELETE FROM exercise WHERE id = ?", id);
    }

    private Exercise rowMapper(ResultSet rs, int i) throws SQLException {
        return new Exercise(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("activity_id"),
                rs.getTimestamp("start_time"),
                rs.getLong("duration"),
                0.0
        );
    }
}
