package test.SampleProj;

import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
//import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

//import java.util.HashMap;

public class testCases {
	ResponseSpecification res;
	@BeforeClass
	public void setSpecification( ) {
		res = RestAssured.expect();
		res.statusLine("HTTP/1.1 200 OK");
		res.contentType(ContentType.JSON);
		
		ExtentReportManager.createReport();

		
	}
	
	@Test
	public void testFetchUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("getUsers", "Fetch User");
		RestAssured.baseURI = "https://reqres.in/";
		ExtentReportManager.test.log(LogStatus.PASS, "Configured the base URL", RestAssured.baseURI);
		given().when().get("api/users?page=2").then().statusCode(200);
	}
	
	@Test
	public void testCreateUser() {
		RestAssured.baseURI = "https://reqres.in/";
		JSONObject params = new JSONObject();
		params.put("name", "Aditya");
		params.put("job", "QA");

		/*HashMap<String,String> params = new HashMap();
		params.put("name", "Aditya");
		params.put("job", "QA"); */
		given().when().contentType("application/json").post("api/users").then().assertThat().body("name", IsEqual.equalTo("Aditya"));
	}
	
	@Test
	public void testUserDetail() {
		RestAssured.baseURI = "https://reqres.in/";
		given().when().get("api/users/2").then().assertThat().body("data.id", IsEqual.equalTo(2));
	}
	
	@AfterClass
    public void endReport()
    {
        ExtentReportManager.reports.flush();
    }

}
