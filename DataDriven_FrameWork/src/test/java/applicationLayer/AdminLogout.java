package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogout 
{
    @FindBy(id = "welcome")
    WebElement objWelcome;
    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement objLogout;
    public void verifyLogout() throws Throwable
    {
    	objWelcome.click();
    	Thread.sleep(2000);
    	objLogout.click();
    }
}
