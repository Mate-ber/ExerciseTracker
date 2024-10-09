package com.harbourspace.tracker.activity;

import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.activity.model.NewActivity;

import java.util.List;

public interface ActivityService {

    List<Activity> getActivities();

    Activity getActivityById(long id);

    List<Activity> getActivitiesByType(String type);

    Activity createActivity(NewActivity activity);

    Activity updateActivity(Activity activity);

    void deleteActivity(long id);
}
