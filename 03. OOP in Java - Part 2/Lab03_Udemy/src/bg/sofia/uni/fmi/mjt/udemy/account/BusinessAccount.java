package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
import bg.sofia.uni.fmi.mjt.udemy.course.Category;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseAlreadyPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.InsufficientBalanceException;
import bg.sofia.uni.fmi.mjt.udemy.exception.MaxCourseCapacityReachedException;

public class BusinessAccount extends AccountBase {

    private final Category[] allowedCategories;
    public BusinessAccount(String username, double balance, Category[] allowedCategories) {
        super(username, balance);
        this.allowedCategories = allowedCategories;
    }

    @Override
    protected AccountType initAccountType() {
        return AccountType.BUSINESS;
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, CourseAlreadyPurchasedException, MaxCourseCapacityReachedException {
        super.buyCourse(course);

        if(!isCategoryAllowed(course.getCategory())){
            throw new IllegalArgumentException("Category not allowed!");
        }

        balance -= (1 - accountType.getDiscount()) * course.getPrice();
        courses[coursesAmount] = course;
        courses[coursesAmount].purchase();
        coursesAmount++;
    }

    private boolean isCategoryAllowed(Category category) {
        for(int i = 0; i < allowedCategories.length; i++){
            if(allowedCategories[i].equals(category)){
                return true;
            }
        }
        return false;
    }

}
