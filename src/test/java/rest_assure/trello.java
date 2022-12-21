package rest_assure;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

	public class trello{
		public String url="https://api.trello.com/";
		public String keys="5e53fd912475ee8c4912e0f6eceb110c";
		public String token="ATTA2ab780e7739d13eafc56c7f9ad9bae45a9c337be8327374509d3912e44fe1c17153CB375";
		public String id;
			
		@Test(enabled = true)
		public void create_trello_board() {
			//JSONObject jr=new JSONObject();
			RestAssured.baseURI=url;
			Response response=given().queryParam("name", "iniesta")
			.queryParam("key", keys)
			.queryParam("token", token).header("Content-Type", "application/json")
			.when().contentType(ContentType.JSON).accept(ContentType.JSON).post("/1/boards/").then().assertThat().statusCode(200)
			.contentType(ContentType.JSON)
			.extract().response();
			String jsonresponse = response.asString();
	        JsonPath js= new JsonPath(jsonresponse);
			id = js.get("id");
			System.out.println(id);	
			
		}
		@Test(enabled = true)
		public void delete_board() {
			RestAssured.baseURI=url;
			given().queryParam("name", "iniesta").queryParam("key", keys)
			.queryParam("token", token)
			.when().contentType(ContentType.JSON).accept(ContentType.JSON).delete("/1/boards/"+id).then().statusCode(200).log().all();
		}
	}
