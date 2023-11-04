package bg.sofia.uni.fmi.mjt.gym.member;

import bg.sofia.uni.fmi.mjt.gym.workout.Exercise;
import bg.sofia.uni.fmi.mjt.gym.workout.Workout;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface GymMember {

    String getName();

    int getAge();

    String getPersonalIdNumber();

    Gender getGender();

    Address getAddress();

    Map<DayOfWeek, Workout> getTrainingProgram();

    void setWorkout(DayOfWeek day, Workout workout);

    Collection<DayOfWeek> getDaysFinishingWith(String exerciseName);

    void addExercise(DayOfWeek day, Exercise exercise) throws DayOffException;

    void addExercises(DayOfWeek day, List<Exercise> exercises) throws DayOffException;
}
