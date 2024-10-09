package com.harbourspace.tracker.exercise.model;

import java.util.Date;

public record NewExercise (
        Long userId,
        Long activityId,
        Date startTime,
        Long duration
){
    public Exercise toExercise(long id){ return new Exercise(id, userId, activityId, startTime, duration, 0.0); }
}
