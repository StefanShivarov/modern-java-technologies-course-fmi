package bg.sofia.uni.fmi.mjt.gym.member.comparators;

import bg.sofia.uni.fmi.mjt.gym.member.Address;
import bg.sofia.uni.fmi.mjt.gym.member.GymMember;

import java.util.Comparator;

public class GymMemberProximityComparator implements Comparator<GymMember> {

    private final Address gymAddress;

    public GymMemberProximityComparator(Address gymAddress) {
        this.gymAddress = gymAddress;
    }

    @Override
    public int compare(GymMember o1, GymMember o2) {
        return Double.compare(o1.getAddress().getDistanceTo(gymAddress), o2.getAddress().getDistanceTo(gymAddress));
    }

}
