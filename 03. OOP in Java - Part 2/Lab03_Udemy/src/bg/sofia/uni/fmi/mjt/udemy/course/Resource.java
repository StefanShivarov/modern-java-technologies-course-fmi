package bg.sofia.uni.fmi.mjt.udemy.course;

import bg.sofia.uni.fmi.mjt.udemy.course.duration.ResourceDuration;

import java.util.Objects;

public class Resource implements Completable {

    private final String name;
    private final ResourceDuration duration;
    private boolean completed;

    public Resource(String name, ResourceDuration duration) {
        this.name = name;
        this.duration = duration;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public ResourceDuration getDuration() {
        return duration;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public int getCompletionPercentage() {
        return completed ? 100 : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(name, resource.name) && Objects.equals(duration, resource.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration);
    }
}
