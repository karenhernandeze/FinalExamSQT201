package mx.tec.exam.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookStoreWebStepDefs {
    private static WebDriver driver;
    
    static {
        System.setProperty("webdriver.chrome.driver", "/Users/karenhernandez/Downloads/chromedriver");
    }
	
	@Given("a registered user")
	public void a_registered_user() {
		// Do nothing
	}

	@When("the client calls \\/login page with username {string} and password {string}")
	public void the_client_calls_login_page_with_username_and_password(String username, String password) {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        WebElement usernameField = driver.findElement(By.id("userName"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.id("login"));
        submitButton.click();   
	}
	
	@Then("the client is redirected to the {string} page")
	public void the_client_is_redirected_to_the_page(String pageTitle) throws InterruptedException {
        // Since this is a One Single Page application. Use the sleep to wait for the page to be updated        
        Thread.sleep(5000);
        
        String title = driver.findElement(By.className("main-header")).getText();                
        
        assertEquals(pageTitle, title);
        driver.quit();
    }
	
	@Given("a not registered user")
	public void a_not_registered_user() {
		// Do nothing
	}
	
	@Then("the message {string} is displayed")
	public void the_message_is_displayed(String string) throws InterruptedException {
		// Since this is a One Single Page application. Use the sleep to wait for the page to be updated        
        Thread.sleep(5000);
        
        By.className("login-wrapper");
		By.id("userform");
		By.id("output");
		String title = driver.findElement(By.id("name")).getText();                
        
        assertEquals(string, title);
        driver.quit();
	}
	
	@Given("no credentials")
	public void no_credentials() {
		// Do nothing
	}

	@Then("the users sees a red border color")
	public void the_users_sees_a_red_border_color() throws InterruptedException {
		// Since this is a One Single Page application. Use the sleep to wait for the page to be updated        
        Thread.sleep(5000);
        
        By.className("login-wrapper");
		By.id("userform");
		By.id("userName-wrapper");
		String borderColor = driver.findElement(By.id("userName")).getCssValue("border-color");   

        assertEquals("rgb(220, 53, 69)", borderColor);
        driver.quit();
	}
}