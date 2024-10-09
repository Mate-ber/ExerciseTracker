package com.harbourspace.tracker.exercise;

import com.harbourspace.tracker.exercise.model.Exercise;
import com.harbourspace.tracker.exercise.model.NewExercise;

import java.util.List;
import java.util.Date;

public interface ExerciseService {

    List<Exercise> getExercises();

    List<Exercise> getExerciseByFilter(Date date, Long activityId, Long duration);

    Exercise getExerciseById(long id);

    Exercise createExercise(NewExercise exercise);

    Exercise updateExercise(Exercise exercise);

    void deleteExercise(long id);

}
