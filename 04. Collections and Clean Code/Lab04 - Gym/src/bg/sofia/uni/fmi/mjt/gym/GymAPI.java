package bg.sofia.uni.fmi.mjt.gym;

import bg.sofia.uni.fmi.mjt.gym.member.GymMember;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface GymAPI {

    SortedSet<GymMember> getMembers();

    SortedSet<GymMember> getMembersSortedByName();

    SortedSet<GymMember> getMembersSortedByProximityToGym();

    void addMember(GymMember member) throws GymCapacityExceededException;

    void addMembers(Collection<GymMember> members) throws GymCapacityExceededException;

    boolean isMember(GymMember member);

    boolean isExerciseTrainedOnDay(String exerciseName, DayOfWeek day);

    Map<DayOfWeek, List<String>> getDailyListOfMembersForExercise(String exerciseName);
}
