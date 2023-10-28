package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.course.Resource;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseAlreadyPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotCompletedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.InsufficientBalanceException;
import bg.sofia.uni.fmi.mjt.udemy.exception.MaxCourseCapacityReachedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.ResourceNotFoundException;

public abstract class AccountBase implements Account {

    protected static final int MAX_COURSES_CAPACITY = 100;
    private String username;
    protected double balance;
    protected Course[] courses;
    private double[] grades;
    private int gradesAmount;
    protected int coursesAmount;
    protected final AccountType accountType;
    public AccountBase(String username, double balance) {
        this.username = username;
        this.balance = balance;
        this.courses = new Course[MAX_COURSES_CAPACITY];
        this.coursesAmount = 0;
        this.grades = new double[MAX_COURSES_CAPACITY];
        this.gradesAmount = 0;
        this.accountType = initAccountType();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void addToBalance(double amount) {
        if(amount < 0){
            throw new IllegalArgumentException("Can't add negative amount to balance!");
        }
        balance += amount;
    }

    @Override
    public void completeResourcesFromCourse(Course course, Resource[] resourcesToComplete) throws CourseNotPurchasedException, ResourceNotFoundException {
        if(course == null || resourcesToComplete == null){
            throw new IllegalArgumentException("Invalid arguments! Course or resources are null!");
        }

        Course searchCourse = findCourse(course);
        if(searchCourse == null){
            throw new CourseNotPurchasedException("Course is not purchased!");
        }

        for(int i = 0; i < resourcesToComplete.length; i++){
            searchCourse.completeResource(resourcesToComplete[i]);
        }
    }

    @Override
    public void completeCourse(Course course, double grade) throws CourseNotPurchasedException, CourseNotCompletedException {
        if(grade < 2 || grade > 6){
            throw new IllegalArgumentException("Invalid grade!");
        }

        Course foundCourse = findCourse(course);
        if(foundCourse == null){
            throw new CourseNotPurchasedException("Course is not purchased!");
        }

        if(course.getCompletionPercentage() != 100){
            throw new CourseNotCompletedException("There is a resource in the course which is not completed!");
        }

        foundCourse.setCompleted();
        grades[gradesAmount++] = grade;
    }

    public double getAverageGrade() {
        double sum = 0.0;
        for(int i = 0; i < gradesAmount; i++){
            sum += grades[i];
        }
        return sum / gradesAmount;
    }

    @Override
    public Course getLeastCompletedCourse() {
        Course leastCompleted = null;
        int minCompletionPercentage = 100;

        for(int i = 0; i < coursesAmount; i++){
            int percentage = courses[i].getCompletionPercentage();
            if(percentage < minCompletionPercentage){
                minCompletionPercentage = percentage;
                leastCompleted = courses[i];
            }
        }
        return leastCompleted;
    }

    protected Course findCourse(Course course) {
        for(int i = 0; i < coursesAmount; i++){
            if(courses[i].equals(course)){
                return courses[i];
            }
        }
        return null;
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, CourseAlreadyPurchasedException, MaxCourseCapacityReachedException {
        if(balance < course.getPrice()){
            throw new InsufficientBalanceException("Not enough balance to buy this course!");
        }

        if(coursesAmount == MAX_COURSES_CAPACITY){
            throw new MaxCourseCapacityReachedException("Maximum course capacity already reached!");
        }

        if(findCourse(course) != null){
            throw new CourseAlreadyPurchasedException("Course is already purchased!");
        }
    }

    protected abstract AccountType initAccountType();
}
