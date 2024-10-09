package com.harbourspace.tracker.user;

import com.harbourspace.tracker.user.model.NewUser;
import com.harbourspace.tracker.user.model.Exercise;

import java.util.List;

public class UserFixtures {

    public static final Exercise user0 = new Exercise(0L, "SYSTEM");
    public static final Exercise user1 = new Exercise(1L, "John");
    public static final Exercise user2 = new Exercise(2L, "Jane");
    public static final List<Exercise> users = List.of(user0, user1, user2);
    public static final NewUser newUser = new NewUser("Jack");
    public static final Exercise user3 = new Exercise(3L, newUser.name());
    public static  final Exercise user3Updated = new Exercise(user3.id(), user3.name() + " UPDATED");

}
