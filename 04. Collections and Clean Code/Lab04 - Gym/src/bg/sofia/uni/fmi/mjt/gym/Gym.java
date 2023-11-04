package bg.sofia.uni.fmi.mjt.gym;

import bg.sofia.uni.fmi.mjt.gym.member.Address;
import bg.sofia.uni.fmi.mjt.gym.member.GymMember;
import bg.sofia.uni.fmi.mjt.gym.member.comparators.GymMemberProximityComparator;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Gym implements GymAPI {

    private int capacity;
    private Address address;
    private SortedSet<GymMember> members;

    public Gym(int capacity, Address address) {
        this.capacity = capacity;
        this.address = address;
        members = new TreeSet<>();
    }

    @Override
    public SortedSet<GymMember> getMembers() {
        return Collections.unmodifiableSortedSet(members);
    }

    @Override
    public SortedSet<GymMember> getMembersSortedByName() {
        //members are already sorted by name, because of the compareTo function in member
        return Collections.unmodifiableSortedSet(members);
    }

    @Override
    public SortedSet<GymMember> getMembersSortedByProximityToGym() {
        SortedSet<GymMember> membersSortedByProximity = new TreeSet<>(new GymMemberProximityComparator(address));
        membersSortedByProximity.addAll(members);
        return Collections.unmodifiableSortedSet(membersSortedByProximity);
    }

    @Override
    public void addMember(GymMember member) throws GymCapacityExceededException {
        if (member == null) {
            throw new IllegalArgumentException("Member is null!");
        }

        if (members.size() == capacity) {
            throw new GymCapacityExceededException("Gym is already full!");
        }

        members.add(member);
    }

    @Override
    public void addMembers(Collection<GymMember> members) throws GymCapacityExceededException {
        if (members == null || members.isEmpty()) {
            throw new IllegalArgumentException("Members is null or empty!");
        }

        if (members.size() == capacity) {
            throw new GymCapacityExceededException("Gym is already full!");
        }

        this.members.addAll(members);
    }

    @Override
    public boolean isMember(GymMember member) {
        if (member == null) {
            throw new IllegalArgumentException("Member is null!");
        }

        return members.contains(member);
    }

    @Override
    public boolean isExerciseTrainedOnDay(String exerciseName, DayOfWeek day) {
        if (day == null || exerciseName == null || exerciseName.isBlank()) {
            throw new IllegalArgumentException("Invalid arguments!");
        }

        for (GymMember member : members) {
            if (member.getTrainingProgram().get(day).hasExercise(exerciseName)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Map<DayOfWeek, List<String>> getDailyListOfMembersForExercise(String exerciseName) {
        if (exerciseName == null || exerciseName.isBlank()) {
            throw new IllegalArgumentException("Invalid exercise name!");
        }

        Map<DayOfWeek, List<String>> result = new LinkedHashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            List<String> names = new ArrayList<>();
            for (GymMember member : members) {
                if (member.getTrainingProgram().get(day).hasExercise(exerciseName)) {
                    names.add(member.getName());
                }
            }
            result.put(day, names);
        }

        return Collections.unmodifiableMap(result);
    }

}
