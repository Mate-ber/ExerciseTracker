package com.harbourspace.tracker.activity.model;

public record NewActivity(
        Long userId,
        String name,
        Double kcalPerMinute
) {
    public Activity toActivity(Long id){
        String type = "USER";
        if(userId == 0) type = "SYSTEM";

        return new Activity(id, (Long) userId, name, type, kcalPerMinute);

    }
}