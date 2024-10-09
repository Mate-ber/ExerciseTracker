package com.harbourspace.tracker.exercise.model;

import java.util.Date;

public record Exercise (
        Long id,
        Long userId,
        Long activityId,
        Date startTime,
        Long duration,
        Double kcalBurned
){
    public Exercise copyWithId(Long id){ return new Exercise(id, userId, activityId, startTime, duration, kcalBurned); }
}
