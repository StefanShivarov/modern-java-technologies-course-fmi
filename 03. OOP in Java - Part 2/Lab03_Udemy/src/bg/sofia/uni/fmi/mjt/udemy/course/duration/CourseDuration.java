package bg.sofia.uni.fmi.mjt.udemy.course.duration;

import bg.sofia.uni.fmi.mjt.udemy.course.Resource;

public record CourseDuration(int hours, int minutes) {

    public CourseDuration {
        if(hours < 0 || hours > 24 || minutes < 0 || minutes > 60){
            throw new IllegalArgumentException("Invalid course duration!");
        }
    }

    public static CourseDuration of(Resource[] content) {

        int totalMinutes = 0;
        for(int i = 0; i < content.length; i++){
            totalMinutes += content[i].getDuration().minutes();
        }

        int totalHours = totalMinutes / 60;
        totalMinutes %= 60;

        return new CourseDuration(totalHours, totalMinutes);
    }
}
