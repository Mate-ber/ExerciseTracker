package com.harbourspace.tracker.exercise;


import com.harbourspace.tracker.exercise.model.NewExercise;
import com.harbourspace.tracker.exercise.model.Exercise;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    ResponseEntity<List<Exercise>> getExercises() {
        return ResponseEntity.ok(exerciseService.getExercises());
    }

    @GetMapping("/filter/{date}/{activityId}/{duration}")
    ResponseEntity<List<Exercise>> getExerciseByFilter(@PathVariable("date") String date, @PathVariable("activityId") Long activityId, @PathVariable("duration") Long duration) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);
        return ResponseEntity.ok(exerciseService.getExerciseByFilter(date1, activityId, duration));
    }

    @GetMapping("{id}")
    ResponseEntity<Exercise> getExerciseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    @PostMapping
    ResponseEntity<Exercise> createExercise(@RequestBody NewExercise exercise) {
        return new ResponseEntity<>(exerciseService.createExercise(exercise), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<Exercise> updateExercise(
            @PathVariable("id") Long id,
            @RequestBody Exercise exercise
    ) {
        return ResponseEntity.ok(exerciseService.updateExercise(exercise.copyWithId(id)));
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> deleteExercise(@PathVariable("id") Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok().build();
    }
}
