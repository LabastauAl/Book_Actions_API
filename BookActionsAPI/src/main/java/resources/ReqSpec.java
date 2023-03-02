package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ReqSpec {
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder().setBaseUri("http://216.10.245.166").setContentType(ContentType.JSON).build();
    }
}
