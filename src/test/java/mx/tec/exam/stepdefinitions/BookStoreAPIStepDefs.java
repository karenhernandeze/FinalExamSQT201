package mx.tec.exam.stepdefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
	
	@When("the client calls \\/Account\\/v1\\/GenerateToken with username {string} and password {string}")
	public void the_client_calls_account_v1_generate_token_with_username_and_password(String username, String password) {
		Map<String, String> body = new HashMap<>();
		body.put("userName", username);
		body.put("password", password);
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(body, headers);
		
		response = restTemplate.exchange("https://demoqa.com/Account/v1/GenerateToken",
				HttpMethod.POST, entity, String.class);
	}

	@Then("a {string} property is provided")
	public void a_property_is_provided(String property) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		assertFalse(root.path(property).isMissingNode());
	}
	
	@Then("a {string} property is {string}")
	public void a_property_is(String string, String property) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		assertTrue(root.path(property).isMissingNode());
	}
	
	@Given("an existing user with username {string} and password {string}")
	public void an_existing_user_with_username_and_password(String username, String password) {
		Map<String, String> body = new HashMap<>();
		body.put("userName", username);
		body.put("password", password);
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(body, headers);
		
		response = restTemplate.exchange("https://demoqa.com/Account/v1/GenerateToken",
				HttpMethod.POST, entity, String.class);
	}

	@When("the client calls \\/BookStore\\/v1\\/Book with ISBN {string}")
	public void the_client_calls_book_store_v1_book_with_isbn(String id) {
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(headers);
		id = "9781449325862";
		response = restTemplate.exchange("https://demoqa.com/BookStore/v1/Book?ISBN="+id,
				HttpMethod.GET, entity, String.class);
	}

	@Then("the client receives book title {string}")
	public void the_client_receives_book_title(String title) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		System.out.print(root);
		assertEquals(title, root.path("title").asText());
	}
}
