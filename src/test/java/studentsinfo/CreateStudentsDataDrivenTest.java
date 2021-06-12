package studentsinfo;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import serenity.StudentSerenitySteps;
import testbase.TestBase;
import utils.TestUtils;
import java.util.ArrayList;
@Concurrent(threads = "4x")
@UseTestDataFrom("testdata/studentInfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentsDataDrivenTest extends TestBase {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    private String firstName;
    private String lastName;
    private String programme;
    private String email;
    private String course;

    @Steps
    StudentSerenitySteps steps;
    @Title("DataDriven test for adding multiple students")
    @Test
    public void createMultipleStudents(){
        ArrayList<String> courses=new ArrayList<>();
        courses.add(course);
        steps.createStudent(firstName,lastName,email,programme,courses)
                .statusCode(201);
    }
}
