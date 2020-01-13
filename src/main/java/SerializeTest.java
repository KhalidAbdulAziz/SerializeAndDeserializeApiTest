import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SerializeTest {

    public static void main(String[] args)
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        SerializeApi addplace = new SerializeApi();
        addplace.setName("Frontline house");
        addplace.setAccuracy(50);
        addplace.setAddress("29, side layout, cohen 09");
        addplace.setWebsite("http://google.com");
        addplace.setLanguage("French-IN");
        addplace.setPhone_no("(+91) 983 893 3937");
        List<String> mylist = new ArrayList<String>();
        mylist.add("shoe park");
        mylist.add("shop");
        addplace.setTypes(mylist);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        addplace.setLocation(l);

       Response res = given().
                queryParam("key","qaclick123").
                body(addplace).
                when().
                post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).extract().response();
                String resp = res.asString();
                System.out.println(resp);
    }
}
