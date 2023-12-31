package bg.sofia.uni.fmi.mjt.udemy.course;

import bg.sofia.uni.fmi.mjt.udemy.course.duration.CourseDuration;
import bg.sofia.uni.fmi.mjt.udemy.exception.ResourceNotFoundException;

import java.util.Arrays;
import java.util.Objects;

public class Course implements Completable, Purchasable {

    private static final int MAX_RESOURCES_AMOUNT = 100;
    private String name;
    private String description;
    private double price;
    private Resource[] content;
    private int resourcesAmount;
    private Category category;
    private CourseDuration totalTime;
    private boolean purchased;
    private boolean completed;

    public Course(String name, String description, double price, Resource[] content, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.resourcesAmount = content.length;
        this.content = new Resource[MAX_RESOURCES_AMOUNT];
        System.arraycopy(content, 0, this.content,0, content.length);
        this.category = category;
        this.totalTime = CourseDuration.of(content);
        this.completed = false;
        this.purchased = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Resource[] getContent() {
        return content;
    }

    public int getResourcesAmount() {
        return resourcesAmount;
    }
    public Category getCategory() {
        return category;
    }

    public CourseDuration getTotalTime() {
        return totalTime;
    }

    public void completeResource(Resource resourceToComplete) throws ResourceNotFoundException {
        if(resourceToComplete == null){
            throw new IllegalArgumentException("Invalid resource!");
        }

        for(int i = 0; i < resourcesAmount; i++){
            if(content[i] == null){
                continue;
            }
            if(content[i].getName().equals(resourceToComplete.getName())){
                content[i].complete();
                if(areAllResourcesCompleted()){
                    setCompleted();
                }
                return;
            }
        }
        throw new ResourceNotFoundException("Resource was not found in this course!");
    }

    private boolean areAllResourcesCompleted(){
        for(int i = 0; i < resourcesAmount; i++){
            if(!content[i].isCompleted()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public int getCompletionPercentage() {
        double sum = 0;
        for(int i = 0; i < resourcesAmount; i++){
            sum += content[i].getCompletionPercentage();
        }
        if(resourcesAmount == 0){
            return 0;
        }
        double completionPercentage = sum / resourcesAmount;
        return (int) Math.round(completionPercentage);
    }

    @Override
    public void purchase() {
        purchased = true;
    }

    @Override
    public boolean isPurchased() {
        return purchased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Double.compare(price, course.price) == 0
                && Objects.equals(name, course.name)
                && Objects.equals(description, course.description)
                && category == course.category
                && Objects.equals(totalTime, course.totalTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, description, price, category, totalTime, completed);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

}
