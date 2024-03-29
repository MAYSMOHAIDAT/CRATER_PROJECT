package steps_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Access_control_page;
import utils.BrowserUtils;
import utils.DataReader;
import utils.Driver;

public class Access_control_steps {
	
	
	Access_control_page acp=new Access_control_page();
	BrowserUtils utils=new BrowserUtils();
	
	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		Driver.getDriver().get(DataReader.getProperty("app_url"));
	  
	}
	
	
	
	@Given("login page components exist")
	public void login_page_components_exist() {
		Assert.assertTrue(acp.login_username.isDisplayed());
		Assert.assertTrue(acp.login_password.isDisplayed());
		Assert.assertTrue(acp.login_loginBtn.isDisplayed());
		Assert.assertTrue(acp.login_forgot_password_link.isDisplayed());
	   
	}
	
	
	
	
	
	@When("I enter valid username and valid password")
	public void i_enter_valid_username_and_valid_password() {
		
		utils.sendkeysWithActionsClass(acp.login_username, DataReader.getProperty("username"));
		utils.sendkeysWithActionsClass(acp.login_password, DataReader.getProperty("password"));
	}
	  
	
		

		
	
	@When("I click login button")
	public void i_click_login_button() {
		 utils.clickWithActionsClass(acp.login_loginBtn);
	}
	
	
	
	@Then("I should be on the Dashboard page")
	public void i_should_be_on_the_dashboard_page() {
		
		 utils.waitUntilUrlContains("dashboard");
		 //first_way
		   String currentUrl = Driver.getDriver().getCurrentUrl();
		   Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", currentUrl);
		 //second_way
		   Assert.assertTrue(currentUrl.contains("dashboard"));
		
		
		
		
	}
	@Then("the success message displays")
	public void the_success_message_displays() {
		  Assert.assertTrue(acp.login_success_message.isDisplayed());

	}

	@Then("I should be on the {string} page")
	public void i_should_be_on_the_page(String menu_item) {
	String classValue=Driver.getDriver().findElement(By.xpath("//a[text()='"+menu_item+"']")).getAttribute("class");
	Assert.assertTrue(classValue.contains("active"));
	
	Assert.fail();
		
		
		
	}
	@Then("{string} message displays")
	public void message_displays(String successMessage) {
	Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//p[text()='"+successMessage+"']")).isDisplayed());
		
	
	}

	
	
	//invalid_times:
	
	@When("i enter invalid username {string} and password {string}")
	public void i_enter_invalid_username_and_password(String username, String password) {
	
		utils.sendkeysWithActionsClass(acp.login_username,(username));
		utils.sendkeysWithActionsClass(acp.login_password,(password));
	}  


	@Then("user should not be logged in")
	public void user_should_not_be_logged_in() {
		Assert.assertTrue(acp.login_username.isDisplayed());
		Assert.assertTrue(acp.login_password.isDisplayed());
		Assert.assertTrue(acp.login_loginBtn.isDisplayed());
	 
	}

	

}



