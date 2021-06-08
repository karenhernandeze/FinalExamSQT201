package mx.tec.exam.stepdefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookStoreAPIStepDefs {
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	ResponseEntity<String> response = null;	
	
	@Given("an existing user")
	public void an_existing_user() {
		// Do nothing
	}

	@When("the client calls \\/Account\\/v1\\/Authorized with username {string} and password {string}")
	public void the_client_calls_account_v1_authorized_with_username_and_password(String username, String password) {
		Map<String, String> body = new HashMap<>();
		body.put("userName", username);
		body.put("password", password);

		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(body, headers);
		
		response = restTemplate.exchange("https://demoqa.com/Account/v1/Authorized",
				HttpMethod.POST, entity, String.class);
	}

	@Then("the client receives status code of {int}")
	public void the_client_receives_status_code_of(int statusCode) {
		assertEquals(statusCode, response.getStatusCodeValue());
	}

	@And("the client receives response {string}")
	public void the_client_receives_response(String responseMessage) throws IOException {
		String body = response.getBody();
		assertEquals(responseMessage, body);
	}
}
