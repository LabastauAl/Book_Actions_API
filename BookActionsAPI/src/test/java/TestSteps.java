import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payload.InputDataHashMap;
import payload.InputDataJson;
import resources.ExcelDataDriven;
import resources.ReqSpec;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class TestSteps {
/*
    @Test
    public void addAndCheckBook(){
        String name = "StainedClass";
        String isbn = "heavyMetal";
        String aisle = "1978";
        String author = "JudasPriest";
        RequestSpecification request = given().log().all().spec(ReqSpec.requestSpecification()).header("Content-Type", "application/json").
                //body(InputDataJson.addBookData(name, isbn, aisle, author));  //Передача значения в джейсоне строкой
                body(InputDataHashMap.addBookData(name, isbn, aisle, author)); //Передача значений в хэшмапе

        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Response response = request.when().post("/Library/Addbook.php").then().log().all().spec(responseSpec).extract().response();
        String responseString = response.asString();
        System.out.println(responseString);


        JsonPath js = new JsonPath(responseString);
        String id = js.getString("ID");
        System.out.println(id);

        request = given().log().all().spec(ReqSpec.requestSpecification()).queryParam("ID", id);
        response = request.when().get("/Library/GetBook.php").then().log().all().spec(responseSpec).extract().response();
        responseString = response.asString();
        System.out.println(responseString);

    }

    @Test
    public void addBookExcelData() throws IOException {
        ExcelDataDriven test = new ExcelDataDriven();
        ArrayList<String> testData = test.getTestData("AddBook");

        //for (int i = 1; i < testData.size(); i++) {
            //System.out.println(testData.get(i));
        //}
        String name = testData.get(1);
        String isbn = testData.get(2);
        String aisle = testData.get(3);
        String author = testData.get(4);

        RequestSpecification request = given().log().all().spec(ReqSpec.requestSpecification()).header("Content-Type", "application/json").
                        body(InputDataHashMap.addBookData(name, isbn, aisle, author)); //Передача значений в хэшмапе

        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Response response = request.when().post("/Library/Addbook.php").then().log().all().spec(responseSpec).extract().response();
        String responseString = response.asString();
        System.out.println(responseString);


        JsonPath js = new JsonPath(responseString);
        String id = js.getString("ID");
        System.out.println(id);

        request = given().log().all().spec(ReqSpec.requestSpecification()).queryParam("ID", id);
        response = request.when().get("/Library/GetBook.php").then().log().all().spec(responseSpec).extract().response();
        responseString = response.asString();
        System.out.println(responseString);
    }
*/
    @Test (dataProvider = "BooksData")
    public void addCheckDeleteBooks(String name, String isbn, String aisle, String author){
        RequestSpecification request = given().log().all().spec(ReqSpec.requestSpecification()).header("Content-Type", "application/json").
                body(InputDataJson.addBookData(name, isbn, aisle, author));  //Передача значения в джейсоне строкой

        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Response response = request.when().post("/Library/Addbook.php").then().log().all().spec(responseSpec).extract().response();
        String responseString = response.asString();
        System.out.println(responseString);

        JsonPath js = new JsonPath(responseString);
        String id = js.getString("ID");
        System.out.println(id);

        request = given().log().all().spec(ReqSpec.requestSpecification()).queryParam("ID", id);
        response = request.when().get("/Library/GetBook.php").then().log().all().spec(responseSpec).extract().response();
        responseString = response.asString();

        request = given().log().all().spec(ReqSpec.requestSpecification()).header("Content-Type", "application/json").
                body("{\n" +
                        "    \"ID\": \"" + id + "\"\n" +
                        "}");
        response = request.when().delete("/Library/DeleteBook.php").then().log().all().spec(responseSpec).extract().response();
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][]{
                {"Book_1", "Isbn_1", "111", "Author_1"}, {"Book_2", "Isbn_2", "222", "Author_2"}, {"Book_3", "Isbn_3", "333", "Author_3"}
        };
    }
}
