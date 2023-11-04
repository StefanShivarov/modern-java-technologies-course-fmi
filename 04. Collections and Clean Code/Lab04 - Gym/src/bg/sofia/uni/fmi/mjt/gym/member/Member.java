package bg.sofia.uni.fmi.mjt.gym.member;

import bg.sofia.uni.fmi.mjt.gym.workout.Exercise;
import bg.sofia.uni.fmi.mjt.gym.workout.Workout;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Member implements GymMember {

    private Address address;
    private String name;
    private int age;
    private String id;
    private Gender gender;
    private Map<DayOfWeek, Workout> trainingProgram;

    public Member(Address address, String name, int age, String personalIdNumber, Gender gender) {
        this.address = address;
        this.name = name;
        this.age = age;
        this.id = personalIdNumber;
        this.gender = gender;
        this.trainingProgram = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return 200;
    }

    @Override
    public String getPersonalIdNumber() {
        return id;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public Map<DayOfWeek, Workout> getTrainingProgram() {
        return Map.copyOf(trainingProgram);
    }

    @Override
    public void setWorkout(DayOfWeek day, Workout workout) {
        if (day == null || workout == null) {
            throw new IllegalArgumentException("Invalid day or workout!");
        }

        trainingProgram.put(day, workout);
    }

    @Override
    public Collection<DayOfWeek> getDaysFinishingWith(String exerciseName) {
        if (exerciseName == null || exerciseName.isBlank()) {
            throw new IllegalArgumentException("Invalid exercise name!");
        }

        Set<DayOfWeek> result = new HashSet<>();
        for (Map.Entry<DayOfWeek, Workout> entry : trainingProgram.entrySet()) {
            if (entry.getValue().exercises().getLast().name().equals(exerciseName)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    @Override
    public void addExercise(DayOfWeek day, Exercise exercise) {
        if (day == null || exercise == null) {
            throw new IllegalArgumentException("Invalid day or exercise!");
        }

        if (trainingProgram.get(day) == null) {
            throw new DayOffException("It's a rest day on " + day + "!");
        }

        trainingProgram.get(day).exercises().add(exercise);
    }

    @Override
    public void addExercises(DayOfWeek day, List<Exercise> exercises) {
        if (day == null || exercises == null || exercises.isEmpty()) {
            throw new IllegalArgumentException("Invalid day or exercise!");
        }

        Workout workout = trainingProgram.get(day);
        if (workout == null || workout.exercises().isEmpty()) {
            throw new DayOffException("It's a rest day on " + day + "!");
        }

        workout.exercises().addAll(exercises);
    }

    @Override
    public int compareTo(GymMember other) {
        return this.name.compareTo(other.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, name, age, id, gender, trainingProgram);
    }

}
