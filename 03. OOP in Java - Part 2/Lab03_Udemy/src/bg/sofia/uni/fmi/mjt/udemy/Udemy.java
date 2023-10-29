package bg.sofia.uni.fmi.mjt.udemy;

import bg.sofia.uni.fmi.mjt.udemy.account.Account;
import bg.sofia.uni.fmi.mjt.udemy.course.Category;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.AccountNotFoundException;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotFoundException;

public class Udemy implements LearningPlatform {

    private static final int MAX_ACCOUNTS_AMOUNT = 200;
    private static final int MAX_COURSES_AMOUNT = 200;
    private Account[] accounts;
    private Course[] courses;
    private int accountsAmount;
    private int coursesAmount;

    public Udemy(Account[] accounts, Course[] courses) {
        this.accounts = new Account[MAX_ACCOUNTS_AMOUNT];
        this.courses = new Course[MAX_COURSES_AMOUNT];
        System.arraycopy(accounts, 0, this.accounts, 0, accounts.length);
        System.arraycopy(courses, 0, this.courses, 0, courses.length);
        this.accountsAmount = accounts.length;
        this.coursesAmount = courses.length;
    }

    @Override
    public Course findByName(String name) throws CourseNotFoundException {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Invalid name!");
        }

        for(int i = 0; i < coursesAmount; i++){
            if(courses[i].getName().equals(name)){
                return courses[i];
            }
        }
        throw new CourseNotFoundException("No such course was found!");
    }

    @Override
    public Course[] findByKeyword(String keyword) {
        if(keyword == null || keyword.isBlank() || !isKeywordValid(keyword)){
            throw new IllegalArgumentException("Invalid keyword!");
        }

        Course[] coursesWithKeyword = new Course[MAX_COURSES_AMOUNT];
        int counter = 0;
        for(int i = 0; i < coursesAmount; i++){
            if(courses[i].getDescription().contains(keyword) || courses[i].getName().contains(keyword)){
                coursesWithKeyword[counter++] = courses[i];
            }
        }
        Course[] result = new Course[counter];
        System.arraycopy(coursesWithKeyword, 0, result, 0, counter);

        return result;
    }

    private boolean isKeywordValid(String keyword){
        for(int i = 0; i < keyword.length(); i++){
            char symbol = keyword.charAt(i);
            if(!((symbol >= 'a' && symbol <= 'z') || (symbol >= 'A' && symbol <= 'Z') || symbol == ' ')) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Course[] getAllCoursesByCategory(Category category) {
        if(category == null){
            throw new IllegalArgumentException("Invalid category!");
        }

        Course[] coursesWithCategory = new Course[MAX_COURSES_AMOUNT];
        int counter = 0;
        for(int i = 0; i < coursesAmount; i++){
            if(courses[i].getCategory().equals(category)){
                coursesWithCategory[counter++] = courses[i];
            }
        }
        Course[] result = new Course[counter];
        System.arraycopy(coursesWithCategory, 0, result, 0, counter);
        return result;
    }

    @Override
    public Account getAccount(String name) throws AccountNotFoundException {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Invalid account name!");
        }

        for(int i = 0; i < accountsAmount; i++){
            if(accounts[i].getUsername().equals(name)){
                return accounts[i];
            }
        }
        throw new AccountNotFoundException("No account with this username was found!");
    }

    @Override
    public Course getLongestCourse() {
        if(coursesAmount == 0){
            return null;
        }

        Course longestCourse = courses[0];
        int longestTimeInMinutes = courses[0].getTotalTime().hours() * 60 + courses[0].getTotalTime().minutes();
        for(int i = 1; i < coursesAmount; i++){
            int time = courses[i].getTotalTime().hours() * 60 + courses[i].getTotalTime().minutes();
            if(time > longestTimeInMinutes){
                longestCourse = courses[i];
                longestTimeInMinutes = time;
            }
        }
        return longestCourse;
    }

    @Override
    public Course getCheapestByCategory(Category category) {
        if(category == null){
            throw new IllegalArgumentException("Invalid category!");
        }

        Course[] coursesByCategory = getAllCoursesByCategory(category);
        if(coursesByCategory.length == 0){
            return null;
        }
        Course cheapest = coursesByCategory[0];
        double cheapestPrice = courses[0].getPrice();
        for(int i = 1; i < coursesByCategory.length; i++){
            if(courses[i].getPrice() < cheapestPrice){
                cheapest = courses[i];
                cheapestPrice = courses[i].getPrice();
            }
        }
        return cheapest;
    }

}
