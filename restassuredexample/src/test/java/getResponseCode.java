import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.Assertion;


public class getResponseCode {


    @Test
    public void getResponse()
    {
       Response r1= RestAssured.get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        int statusCode= r1.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }
}


