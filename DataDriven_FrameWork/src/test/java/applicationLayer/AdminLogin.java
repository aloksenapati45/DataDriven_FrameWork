package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogin 
{
     @FindBy(name = "txtUsername")
     WebElement objUser;
     @FindBy(name = "txtPassword")
     WebElement objPass;
     @FindBy(id = "btnLogin")
     WebElement objLogin;
     public void verifyLogin(String username,String Password)
     {
    	 objUser.sendKeys(username);
    	 objPass.sendKeys(Password);
    	 objLogin.click();
     }
}
