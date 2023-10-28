package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseAlreadyPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.InsufficientBalanceException;
import bg.sofia.uni.fmi.mjt.udemy.exception.MaxCourseCapacityReachedException;

public class EducationalAccount extends AccountBase {

    public EducationalAccount(String username, double balance) {
        super(username, balance);
    }

    @Override
    protected AccountType initAccountType() {
        return AccountType.EDUCATION;
    }

    @Override
    public void buyCourse(Course course) throws InsufficientBalanceException, CourseAlreadyPurchasedException, MaxCourseCapacityReachedException {
        super.buyCourse(course);
        if(canUseDiscount()){
            balance -= (1 - accountType.getDiscount()) * course.getPrice();
        }else{
            balance -= course.getPrice();
        }
        courses[coursesAmount] = course;
        courses[coursesAmount].purchase();
        coursesAmount++;
    }

    private boolean canUseDiscount() {
        return coursesAmount % 5 == 0 && getAverageGrade() >= 4.50;
    }
}
