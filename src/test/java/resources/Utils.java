package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		//new FileOutputStream will create file at run time
		//addFilter is to log everything like log().all()
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
//Removed as it is added to global
		//		RestAssured.baseURI="https://rahulshettyacademy.com";
		 req =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
	    		.setContentType(ContentType.JSON).build();
		 return req;

	}
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\CucumberBDDPracticeSec17\\src\\test\\java\\resources\\gloabal.properties");
		//load file or integrate
		prop.load(fis);
	return prop.getProperty(key);
		
	}
}
