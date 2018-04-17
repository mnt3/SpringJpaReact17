package lt.akademija.jpaexam;

import lt.akademija.jpaexam.grader.Grade;
import lt.akademija.jpaexam.grader.Grader;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ExerciseTest.class
})
public class GradedSuite {

    @AfterClass
    public static void printGrades() {
        Grade g = Grader.getCurrentGrade();

        System.out.println(String.format("\n\n\n" +
        "Score:         %s/%s \n" +
        "Average grade: %s \n" +
        "Grade:         %s\n\n\n", g.getScore(), g.getTotal(), g.getAverageGrade(), g.getGrade()));
    }
}
