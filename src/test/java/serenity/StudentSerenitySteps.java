package serenity;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.StudentClass;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.ReusableSpecifications;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {

    @Step("Creating a student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
                                             List<String> courses){

        StudentClass student=new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

       return  SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post()
                .then();
    }

    @Step("Getting the student information with firstname: {0}")
    public HashMap<String, Object> getStudentsInfoByFirstName(String firstName){
        String p1="findAll{it.firstName=='";
        String p2="'}.get(0)";
        return SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);
    }

    @Step("Update the student with firstName:{1}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme, List<String>courses){
        StudentClass student =new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return  SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .log()
                .all()
                .when()
                .body(student)
                .put("/"+studentId)
                .then();
    }

    @Step("Deleting student with id:{0}")
    public void deleteStudent(int studentId){
        SerenityRest.rest()
                .given()
                .when()
                .delete("/"+studentId);
    }

    @Step("Check if student still exists with id:{0}")
    public ValidatableResponse getStudentById(int studentId){
        return SerenityRest.rest()
                .given()
                .when()
                .get("/"+studentId)
                .then();

    }

}
