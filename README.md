# Exercise Tracker Application

## Overview

The exercise tracker application allows users to track their activities. 
The application has the following features:

1. Managing activity types
2. Tracking user exercise
3. Managing users _(already implemented)_


## Implementation
## Important the only things that ws implemented by me is managing activity types and tracking user exercise in folder m3-spring-application.

---
## Activities
The application allows users to manage activity types. An activity type is a type of activity that a user can perform.

There are two kinds of activity types:
- Application-defined activity types (SYSTEM)
- User-defined activity types (USER)

### Activities: functional requirements

The application performs the following operations on activity types:
1. Get a list of all activity types **in context of a user**.
2. Get a list of activity types **in context of a user**, filtered by:
   - type (SYSTEM or USER)
3. Get an activity type by its unique identifier, **in context of a user**.
4. Add a new USER activity type.
5. Update an existing USER activity type.
6. Delete an existing USER activity type, if it is not used in any user activity.

The following rules apply to activity types:
1. **SYSTEM** activity types are predefined in the application and cannot be modified or deleted by any user.
2. **USER** types are created by users and can be modified or deleted by the user who created them, under following conditions:
    - User-defined activity types cannot be deleted if they are used in any user activity.
    - User-defined activity types can be modified if they are used in any user activity, and the changes will be reflected in the user activities that have already been recorded.
    - User-defined activity types can be deleted if they are not used in any user activity, and only by the user who created them.

### Activities: model

The activity should have the following properties:
- **id** - Unique identifier of the activity.
- **userId** - The unique identifier of the user who added the activity (0 for SYSTEM)
- **type** - The type of the activity (SYSTEM or USER).
- **name** - The name of the activity.
- **kcalPerMinute** - The number of kilocalories burned per minute during the activity.


## Exercises

The application allows users to track their activities. 

### Exercise: functional requirements

The following rules apply to exercises:
1. User can add a exercise in their context (session).
   - exercise **must** be associated with a user (by id) and an activity (by id).
   - exercise **must** have a start time and duration.
2. Exercises may be updated or deleted only by the user who added it.
3. User can list all their exercises.
4. User can list their exercises filtered by 
   - date
   - activity
   - duration.
5. User can get an exercises by its unique identifier.
6. User cannot see other user exercises.

### Exercise: model

**NewExercise** - Represents a new exercise record. It has the following properties:
- **userId** - The unique identifier of the user who added the activity.
- **activityId** - The unique identifier of the activity.
- **startTime** - The date and time the activity was started.
- **duration** - The duration of the activity in seconds.

**Exercise** - Represents existing user activity. It has the following properties:
  - **id** - The unique identifier of the user activity.
  - **userId** - The unique identifier of the user who added the activity.
  - **activityId** - The unique identifier of the activity.
  - **startTime** - The date and time the activity was started.
  - **duration** - The duration of the activity in seconds.
  - **kcalBurned** - The number of kilocalories burned during the activity. This is a calculated number based on duration and activity type.
