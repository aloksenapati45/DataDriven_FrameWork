package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import applicationLayer.AdminLogin;
import applicationLayer.AdminLogout;

public class Base 
{
    public static WebDriver driver;
    public static Properties property;
    @BeforeTest
    public static void setUp() throws Throwable, IOException
    {
    	property = new Properties();
    	property.load(new FileInputStream("Property/DataDriver.properties"));
    	if(property.getProperty("browser").equalsIgnoreCase("chrome"))
    	{
    		driver = new ChromeDriver();
    		driver.manage().window().maximize();
    		driver.get(property.getProperty("url"));
    		AdminLogin login = PageFactory.initElements(driver, AdminLogin.class);
    		login.verifyLogin("Admin", "Qedge123!@#");
    	}
    	else if(property.getProperty("browser").equalsIgnoreCase("firefox"))
    	{
    		driver = new FirefoxDriver();
    		driver.manage().window().maximize();
    		driver.get(property.getProperty("url"));
    		AdminLogin login = PageFactory.initElements(driver, AdminLogin.class);
    		login.verifyLogin("Admin", "Qedge123!@#");
    	}
    	else
    	{
    		Reporter.log("please provide valid browser name",true);
    	}   	
    }
    @AfterTest
    public static void tearDown() throws Throwable
    {
    	AdminLogout logout = PageFactory.initElements(driver, AdminLogout.class);
    	logout.verifyLogout();
    	driver.quit();
    }
}
