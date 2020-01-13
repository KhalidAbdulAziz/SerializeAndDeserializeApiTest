import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthApi {

    public static void main(String [] args)
    {

      /*  System.setProperty("webdriver.chrome.driver","C:\\Users\\mkhalid\\Desktop\\chromedriver_win32.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("mkhalid3536");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        String url = driver.getCurrentUrl();
*/
      /*
      String url ="https://www.googleapis.com/oauth2/v4/token?code=4%2FvAFFj_STXnfNyqRk4ujyBLpsTKRuX05yUT1xomUcOmpQ9Cy2Q_SoVzXN71BNGy_aVlQzHI7TQ9ruGu9pRjOxkEE&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&client_secret=erZOWM9g3UtwNRj340YYaK_W&redirect_uri=https://rahulshettyacademy.com/getCourse.php&grant_type=authorization_code";
               String parCode = url.split("code=")[1];
              String code = parCode.split("&client_id=")[0];
                System.out.println(code);
       */

      String accessTokenResponse = given().urlEncodingEnabled(false).
                queryParam("code","4%2FvQHd2kU420K8ESiLGBlmQOat5yAy8KgEFUyW8Nryw_oQrYgeThtBHEPJJGhc9GsGNI_x3JtuOWdVSof2tai3zv8&").
                queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").
                queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
                queryParam("grant_type","authorization_code").
                when().
                post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath jp = new JsonPath(accessTokenResponse);
                String access_token = jp.getString("access_token");


        GetCourses gc  = given().queryParam("access_token",access_token).expect().defaultParser(Parser.JSON).
        when().
        get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
       // System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        List<WebAutomation> Webatm = gc.getCourses().getWebAutomation();
        for (int i=0; i<Webatm.size(); i++)
        {
            if (Webatm.get(i).getCourseTitle().equalsIgnoreCase("Selenium Webdriver Java"))
            {
                System.out.println(Webatm.get(i).getPrice());
            }

        }
/*
 HashMap<String, String> map = new HashMap<>();

                Iterator<String> iterator = json.keys();

                while (iterator.hasNext())
                 {
                    String key = iterator.next();
                    String value = json.getString(key);
                    map.put(key, value);
                 }

                return map;


 */



    }
}
