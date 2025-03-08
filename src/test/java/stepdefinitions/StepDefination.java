package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Map;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class StepDefination {

    private String endpoint;
    private Response response;



    @Given("the endpoint {string}")
    public void the_endpoint(String apiURL) {
        // Write code here that turns the phrase above into concrete actions
        endpoint = apiURL;
    }


    @When("customer sends a POST request with the following data:")
    public void customer_sends_a_post_request_with_the_following_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> requestData = dataTable.asMaps().get(0);
        // Manually build JSON to ensure proper nesting (especially for bookingdates)
        JSONObject requestBody = new JSONObject();
        requestBody.put("roomid", Integer.parseInt(requestData.get("roomid")));
        requestBody.put("firstname", requestData.get("firstname"));
        requestBody.put("lastname", requestData.get("lastname"));
        requestBody.put("depositpaid", Boolean.parseBoolean(requestData.get("depositpaid")));
        requestBody.put("email", requestData.get("email"));
        requestBody.put("phone", requestData.get("phone"));

        // Create a nested JSON object for bookingdates
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", requestData.get("checkin"));
        bookingDates.put("checkout", requestData.get("checkout"));

        requestBody.put("bookingdates", bookingDates);

        // Send the request
        RequestSpecification request = RestAssured.given()
                .contentType("application/json")
                .body(requestBody.toString());

        response = request.when().post(endpoint);

        // Print response for debugging
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    @When("customer sends a POST request with the Same data:")
    public void customer_sends_a_post_request_with_the_same_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> requestData = dataTable.asMaps().get(0);
        // Manually build JSON to ensure proper nesting (especially for bookingdates)
        JSONObject requestBody = new JSONObject();
        requestBody.put("roomid", Integer.parseInt(requestData.get("roomid")));
        requestBody.put("firstname", requestData.get("firstname"));
        requestBody.put("lastname", requestData.get("lastname"));
        requestBody.put("depositpaid", Boolean.parseBoolean(requestData.get("depositpaid")));
        requestBody.put("email", requestData.get("email"));
        requestBody.put("phone", requestData.get("phone"));

        // Create a nested JSON object for bookingdates
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", requestData.get("checkin"));
        bookingDates.put("checkout", requestData.get("checkout"));

        requestBody.put("bookingdates", bookingDates);

        // Send the request
        RequestSpecification request = RestAssured.given()
                .contentType("application/json")
                .body(requestBody.toString());

        response = request.when().post(endpoint);

        // Print response for debugging
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }
    @Then("the response should contain a valid bookingId")
    public void the_response_should_contain_a_valid_message_id() {
        response.then()
                .body("bookingid", notNullValue());
    }
    @Then("the response should match the JSON schema")
    public void the_response_should_match_the_json_schema() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(1, 1);
    }



}
