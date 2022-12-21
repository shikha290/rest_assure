package rest_assure;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import org.json.simple.JSONObject;


public class rest1 {

public String url = "https://reqres.in/api/users?page=2";
    @Test(enabled=false)
    public void tc1_get() 
    {
      Response rep = RestAssured.get(url);
      int actual = rep.statusCode();
      Assert.assertEquals(actual, 200);
    }
    @Test(enabled=true)
    public void tc2_get() {
        given().get(url).then().statusCode(200).log().all();
        given().get(url).then().statusCode(200).body("data.first_name", hasItems("Michael"));
        given().get(url).then().statusCode(200).body("data.last_name", hasItems("Lawson"));
    }
    @Test(enabled=false)
    public void tc3_get() {
        given().get(url).then().statusCode(200).body("data.id[0]", equalTo(7)).log().all();
    }
    @Test(enabled=true)
    public void tc4_post() {
        JSONObject js = new JSONObject();
        js.put("name", "vamsi");
        js.put("job", "tester1");
        given().body(js.toJSONString()).when().post("https://reqres.in/api/users").then().statusCode(201).log().all();
    }
    @Test(enabled=true)
    public void tc5_put() {
        JSONObject js = new JSONObject();
        js.put("name", "vamsi");
        js.put("job", "tester2");
        given().body(js.toJSONString()).when().put("https://reqres.in/api/users/2").then().statusCode(200).log().all();
    }
    @Test(enabled=true)
    public void tc6_patch() {
        JSONObject js = new JSONObject();
        js.put("name", "vamsi");
        js.put("job", "tester3");
        given().body(js.toJSONString()).when().patch("https://reqres.in/api/users/2").then().statusCode(200).log().all();
    }
    @Test(enabled=false)
    public void tc7_delete() {
        JSONObject js = new JSONObject();
        js.put("name", "Michael");
        js.put("job", "Lawson");
        given().body(js.toJSONString()).when().delete("https://reqres.in/api/users/2").then().statusCode(204).log().all();
    }
    @Test(enabled=true)
    public void tc8_post() {
        JSONObject js = new JSONObject();
        js.put("email", "eve.holt@reqres.in");
        js.put("password", "pistol");
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(js.toJSONString()).when().post("https://reqres.in/api/register").then().statusCode(200).log().all();
    }
    @Test(enabled=true)
    public void tc9_post() {
        JSONObject js = new JSONObject();
        js.put("email", "eve.holt@reqres.in");
        js.put("password", "pistol");
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(js.toJSONString()).when().post("https://reqres.in/api/register").then().statusCode(200).log().all();
    }
}



