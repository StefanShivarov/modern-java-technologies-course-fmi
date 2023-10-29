package bg.sofia.uni.fmi.mjt.udemy.account;

import bg.sofia.uni.fmi.mjt.udemy.account.type.AccountType;
import bg.sofia.uni.fmi.mjt.udemy.course.Course;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseAlreadyPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotCompletedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.CourseNotPurchasedException;
import bg.sofia.uni.fmi.mjt.udemy.exception.InsufficientBalanceException;
import bg.sofia.uni.fmi.mjt.udemy.exception.MaxCourseCapacityReachedException;

public class EducationalAccount extends AccountBase {

    private int consecutiveCoursesCompleted;
    private boolean discountApplied;

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
        if(canUseDiscount() && !discountApplied){
            balance -= (1 - accountType.getDiscount()) * course.getPrice();
            discountApplied = true;
        }else{
            balance -= course.getPrice();
        }
        courses[coursesAmount] = course;
        courses[coursesAmount].purchase();
        coursesAmount++;
    }

    @Override
    public void completeCourse(Course course, double grade) throws CourseNotPurchasedException, CourseNotCompletedException {
        super.completeCourse(course, grade);
        if(grade >= 4.50){
            consecutiveCoursesCompleted++;
        }else{
            consecutiveCoursesCompleted = 0;
        }
    }

    private boolean canUseDiscount() {

        if(gradesAmount == 5 && getAverageGrade() >= 4.50){
            consecutiveCoursesCompleted = 0;
            return true;
        }
        return consecutiveCoursesCompleted % 5 == 0 && consecutiveCoursesCompleted != 0;
    }
}
