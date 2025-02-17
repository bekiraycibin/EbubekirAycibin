package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetTests {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static int petId = 12345;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void testCreatePet() {
        String petJson = "{" +
                "\"id\": " + petId + "," +
                "\"name\": \"Buddy\"," +
                "\"status\": \"available\"" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(petJson)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Buddy"));
    }

    @Test(dependsOnMethods = "testCreatePet",priority = 2)
    public void testUploadPetImage() {
        File file = new File("src/test/java/Tests/pet.jpg");

        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file)
                .pathParam("petId", petId)
                .when()
                .post("/pet/{petId}/uploadImage")
                .then()
                .statusCode(200)
                .body("message", containsString("uploaded"));
    }

    @Test(dependsOnMethods = "testCreatePet",priority = 3)
    public void testGetPetById() {
        given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(petId));
    }

    @Test(priority = 4)
    public void testFindPetsByStatus() {
        given()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("status", everyItem(equalTo("available")));
    }
    
    @Test (priority = 5)
    public void testGetNonExistingPet() {//Negative scenario
        given()
                .pathParam("petId", 9999999) // Geçersiz ID
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404);
    }

    @Test(dependsOnMethods = "testCreatePet", priority = 6)
    public void testUpdatePet() {
        String updatedPetJson = "{" +
                "\"id\": " + petId + "," +
                "\"name\": \"Max\"," +
                "\"status\": \"sold\"" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(updatedPetJson)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Max"))
                .body("status", equalTo("sold"));
    }

    @Test(dependsOnMethods = "testCreatePet",priority = 7)
    public void testUpdatePetWithForm() {
        given()
                .contentType(ContentType.URLENC)
                .pathParam("petId", petId)
                .formParam("name", "Charlie")
                .formParam("status", "pending")
                .when()
                .post("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(petId)));
    }

    @Test(dependsOnMethods = "testCreatePet",priority = 8)
    public void testDeletePet() {
        given()
                .pathParam("petId", petId)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "testCreatePet",priority = 9)
    public void DeleteNonExistingPet() { //Negative scenario
        given()
                .pathParam("petId", 9999999)// Geçersiz ID
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(404);
    }
}
