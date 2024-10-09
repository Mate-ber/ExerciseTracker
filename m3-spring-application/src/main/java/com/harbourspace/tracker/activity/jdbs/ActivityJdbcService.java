package com.harbourspace.tracker.activity.jdbs;

import com.harbourspace.tracker.activity.ActivityService;
import com.harbourspace.tracker.activity.model.Activity;
import com.harbourspace.tracker.activity.model.NewActivity;
import com.harbourspace.tracker.authorization.AuthorizationService;
import com.harbourspace.tracker.error.AuthorizationException;
import com.harbourspace.tracker.user.jdbc.UserJdbcRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityJdbcService implements ActivityService {

    private final Logger logger = LoggerFactory.getLogger(ActivityJdbcService.class);

    private final ActivityJdbcRepository activityJdbcRepository;

    private final AuthorizationService authorizationService;

    public ActivityJdbcService(ActivityJdbcRepository activityJdbcRepository, AuthorizationService authorizationService) {
        this.activityJdbcRepository = activityJdbcRepository;
        this.authorizationService = authorizationService;
    }

    @Override
    public List<Activity> getActivities() {
        if (authorizationService.isSystem()) {
            logger.debug("Getting all Activities");
            return activityJdbcRepository.selectAll();
        } else throw unauthorized();

    }

    @Override
    public Activity getActivityById(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting Activities " + id);
            return activityJdbcRepository.selectById(id);
        } else throw unauthorized();
    }

    @Override
    public List<Activity> getActivitiesByType(String type) {
        if (authorizationService.isSystem()) {
            logger.debug("Getting type " + type);
            return activityJdbcRepository.selectByType(type);
        } else throw unauthorized();
    }

    @Override
    public Activity createActivity(NewActivity activity) {
        logger.debug("Creating new activity: " + activity);
        return activityJdbcRepository.insert(activity);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        if (authorizationService.isSystem()) {
            logger.debug("Updating activity: "+ activity);
            return activityJdbcRepository.update(activity);
        } else throw unauthorized();
    }

    @Override
    public void deleteActivity(long id) {
        if (authorizationService.isSystem()) {
            logger.debug("Deleting activity " + id);
            activityJdbcRepository.delete(id);
        } else throw unauthorized();
    }

    private AuthorizationException unauthorized() {
        var authorizationException = new AuthorizationException("Activity is not authorized for this operation.");
        logger.error(authorizationException.getMessage());
        return authorizationException;
    }
}
