package com.harbourspace.tracker.exercise.jdbc;

import com.harbourspace.tracker.exercise.model.Exercise;
import com.harbourspace.tracker.exercise.model.NewExercise;
import com.harbourspace.tracker.authorization.AuthorizationService;
import com.harbourspace.tracker.error.AuthorizationException;
import com.harbourspace.tracker.exercise.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class ExerciseJdbcService implements ExerciseService {

    private final Logger logger = LoggerFactory.getLogger(ExerciseJdbcService.class);

    private final ExerciseJdbcRepository exerciseJdbcRepository;
    private final AuthorizationService authorizationService;

    public ExerciseJdbcService(ExerciseJdbcRepository exerciseRepository, AuthorizationService authorizationService) {
        this.exerciseJdbcRepository = exerciseRepository;
        this.authorizationService = authorizationService;
    }
    @Override
    public List<Exercise> getExercises() {
        if (authorizationService.isSystem()) {
            logger.debug("Getting all exercises");
            return exerciseJdbcRepository.selectAll();
        } else throw unauthorized();
    }


    @Override
    public List<Exercise> getExerciseByFilter(Date date, Long activityId, Long duration) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting exercises " + date + activityId + duration);
            return exerciseJdbcRepository.selectByFilter(date, activityId, duration);
        } else throw unauthorized();
    }
//
    @Override
    public Exercise getExerciseById(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting exercise " + id);
            return exerciseJdbcRepository.selectById(id);
        } else throw unauthorized();
    }

    @Override
    public Exercise createExercise(NewExercise exercise) {
        if (authorizationService.isSystem()) {
            logger.debug("Creating new exercise: " + exercise);
            return exerciseJdbcRepository.insert(exercise);
        } else throw unauthorized();
    }


    @Override
    public Exercise updateExercise(Exercise exercise) {
        if (authorizationService.isSystem()) {
            logger.debug("Updating user: "+ exercise);
            return exerciseJdbcRepository.update(exercise);
        } else throw unauthorized();
    }

    @Override
    public void deleteExercise(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Deleting user " + id);
            exerciseJdbcRepository.delete(id);
        } else throw unauthorized();
    }

    private AuthorizationException unauthorized() {
        var authorizationException = new AuthorizationException("Exercise is not authorized for this operation.");
        logger.error(authorizationException.getMessage());
        return authorizationException;
    }
}
