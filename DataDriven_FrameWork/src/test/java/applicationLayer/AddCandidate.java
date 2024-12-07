package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddCandidate 
{
	WebDriver driver;
	public AddCandidate(WebDriver driver)
	{
		this.driver = driver;
	}
    @FindBy(xpath = "//b[normalize-space()='Recruitment']")
    WebElement objRecruitment;
    @FindBy(xpath = "//a[@id='menu_recruitment_viewCandidates']")
    WebElement objCandidate;
    @FindBy(name = "btnAdd")
    WebElement objAdd;
    @FindBy(name = "addCandidate[firstName]")
    WebElement objFirstName;
    @FindBy(name = "addCandidate[middleName]")
    WebElement objMiddleName;
    @FindBy(name = "addCandidate[lastName]")
    WebElement objLastName;
    @FindBy(name = "addCandidate[email]")
    WebElement objEmail;
    @FindBy(name = "addCandidate[contactNo]")
    WebElement objContact;
    @FindBy(name = "addCandidate[vacancy]")
    WebElement objJob;
    @FindBy(name = "addCandidate[keyWords]")
    WebElement objKeyWords;
    @FindBy(name = "addCandidate[comment]")
    WebElement objComment;
    @FindBy(name = "addCandidate[consentToKeepData]")
    WebElement objData;
    @FindBy(xpath = "//input[@id='btnSave']")
    WebElement objSave;
    @FindBy(xpath = "//table[@class='table hover']/tbody/tr[1]/td[2]")
    WebElement objDescription;
    
    public boolean verifyCandidate(String fname,String mname,String lname,String email,String contact,String keyboard,String comment)
    {
    	Actions act = new Actions(driver);
    	act.moveToElement(objRecruitment).click().perform();
    	act.moveToElement(objCandidate).click().perform();
    	objAdd.click();
    	objFirstName.sendKeys(fname);
    	objMiddleName.sendKeys(mname);
    	objLastName.sendKeys(lname);
    	objEmail.sendKeys(email);
    	objContact.sendKeys(contact);
    	objKeyWords.sendKeys(keyboard);
    	objComment.sendKeys(comment);
    	objData.click();
    	objSave.click();
    	String res = objDescription.getAttribute("Value");
    	if(res.contains(fname))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
