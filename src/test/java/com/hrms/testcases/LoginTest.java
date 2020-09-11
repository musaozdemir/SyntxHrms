package com.hrms.testcases;



import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

public class LoginTest extends CommonMethods {

	@Test(groups = "smoke")
	public void validLogin() {
		sendText(login.userNametextBox, ConfigsReader.getPropValue("username"));
		sendText(login.passwordTextBox, ConfigsReader.getPropValue("password"));
		click(login.btnLogin);
		Assert.assertTrue(dash.welcome.isDisplayed());
	}

	@Test(groups="regression", dataProvider="invalidCredentials")
	public void invalidLogin(String username, String password, String errorMessage) {
		sendText(login.userNametextBox, username);
		sendText(login.passwordTextBox, password);
		click(login.btnLogin);
		Assert.assertEquals(login.spanMessage.getText(), errorMessage);
	}
	
	@DataProvider
	public String[][] invalidCredentials() {
		String[][] data = { 
				{ "", "Hum@nhrm123", "Username cannot be empty" },
				{ "Admin", "", "Password cannot be empty" }, 
				{ "Admin", "Hum@nhrm12", "Invalid credentials" }, 
				};
		return data;
	}
	
	
	
}
