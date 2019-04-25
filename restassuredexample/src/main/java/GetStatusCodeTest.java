import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetStatusCodeTest {

    @BeforeClass
    public void setBaseUri () {

        RestAssured.baseURI = "https://maps.googleapis.com";
    }

    @Test
    public void testStatusCode () {

        Response res =
                given()
                        .param ("query", "restaurants in mumbai")
                        .param ("key", "Xyz")
                        .when()
                        .get ("/maps/api/place/textsearch/json");

        Assert.assertEquals (res.statusCode (), 200);
    }

    @Test
    public void testStatusCodeRestAssured () {

        given ().param ("query", "restaurants in mumbai")
                .param ("key", "Xyz")
                .when()
                .get ("/maps/api/place/textsearch/json")
                .then ()
                .assertThat ().statusCode (200);

    }
}