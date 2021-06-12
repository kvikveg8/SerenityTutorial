package studentsinfo;

import io.restassured.http.ContentType;
import model.StudentClass;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import serenity.StudentSerenitySteps;
import testbase.TestBase;
import utils.ReusableSpecifications;
import utils.TestUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static com.openhtmltopdf.util.Configuration.hasValue;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCrudTest extends TestBase {

    static  String firstName="SMOKEUSER" + TestUtils.getRandomValue();
    static  String lastName="SMOKEUSER" + TestUtils.getRandomValue();
    static  String programme="ComputerScience";
    static  String email= TestUtils.getRandomValue()+"xyz@gmail.com";
    static int studentId;

    @Steps
    StudentSerenitySteps steps;

    @Title("This test will create a student")
    @Test
    public void test001(){
        ArrayList<String> courses=new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C#");
        steps.createStudent(firstName,lastName,email,programme,courses)
                .statusCode(201)
        .spec(ReusableSpecifications.getGenericResponseSpec());
    }
    @Title("Verify if student was added")
    @Test
    public void test002(){
        HashMap<String, Object> value = steps.getStudentsInfoByFirstName(firstName);
        assertTrue(value.containsValue(firstName));

        studentId=(int)value.get("id");
    }

    @Title("Update student info and verify")
    @Test
    public void test003(){
        ArrayList<String> courses=new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C#");
        firstName=firstName+"_Updated";

        steps.updateStudent(studentId,firstName,lastName,email,programme,courses);

        HashMap<String, Object> value = steps.getStudentsInfoByFirstName(firstName);
        assertTrue(value.containsValue(firstName));
    }

    @Title("Delete student and verify")
    @Test
    public void test004(){
       steps.deleteStudent(studentId);
       steps.getStudentById(studentId).statusCode(404);


    }


}
