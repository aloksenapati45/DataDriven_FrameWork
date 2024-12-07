package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelFileUtil;

public class AddEmployee 
{
	WebDriver driver;
	public AddEmployee(WebDriver driver)
	{
		this.driver = driver;
	}
    @FindBy(xpath = "//b[normalize-space()='PIM']")
    WebElement objPIM;
    @FindBy(id = "btnAdd")
    WebElement objAdd;
    @FindBy(name = "firstName")
    WebElement objFname;
    @FindBy(name = "middleName")
    WebElement objMname;
    @FindBy(name = "lastName")
    WebElement objLname;
    @FindBy(id = "employeeId")
    WebElement objgetID;
    @FindBy(id = "btnSave")
    WebElement objSave;
    @FindBy(xpath = "//input[@id='personal_txtEmployeeId']")
    WebElement objID;
    public boolean verifyEmp(String FirstName,String MiddleName,String LastName) throws Throwable
    {
    	Actions ac = new Actions(driver);
    	ac.moveToElement(objPIM).click().perform();
    	ac.moveToElement(objAdd).click().perform();
    	objFname.sendKeys(FirstName);
    	objMname.sendKeys(MiddleName);
    	objLname.sendKeys(LastName);
    	String id = objgetID.getAttribute("Value");
    	objSave.click();
    	String empid = objID.getAttribute("Value");
    	if(empid.equals(id))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
