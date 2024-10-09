package com.harbourspace.tracker.activity.model;

public record Activity(
        Long id,
        Long userId,
        String name,
        String type,
        Double kcalPerMinute
) {
    public Activity copyWithId(Long id){ return new Activity(id, userId, name, type, kcalPerMinute); }
}
