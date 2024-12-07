package driverFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import applicationLayer.AddEmployee;
import config.Base;
import utilities.ExcelFileUtil;

public class AppTest extends Base
{
	String inputpath = "./FileInput/AddEmployee.xlsx";
	String outputpath = "./FileOutput/AddEmployee_Result.xlsx";
	ExtentReports report;
	ExtentTest logger;
	String TCName = "addemployee";
    @Test
    public void startTest() throws Throwable
    {
    	report = new ExtentReports("./target/reports/AddEmp.html");
    	//create reference object ExcelUtil class
    	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
    	int rc = xl.getRow(TCName);
    	Reporter.log("Number of rows : "+rc,true);
    	for(int i = 1;i<=rc;i++)
    	{
    		logger = report.startTest("Validate add employee");
    		logger.assignAuthor("Alok");
    		String fname = xl.getCellData(TCName, i, 0);
    		String mname = xl.getCellData(TCName, i, 1);
    		String lname = xl.getCellData(TCName, i, 2);
    		logger.log(LogStatus.INFO, fname+"   "+mname+"    "+lname);
    		AddEmployee add = PageFactory.initElements(driver, AddEmployee.class);
    		boolean res = add.verifyEmp(fname, mname, lname);
    		if(res)
    		{
    			logger.log(LogStatus.PASS, "both id are pass");
    			xl.setCellData(TCName, i, 3, "pass", outputpath);
    		}
    		else
    		{
    			logger.log(LogStatus.FAIL, "Both id's are not same");
    			xl.setCellData(TCName, i, 3, "fail", outputpath);
    		}
    	}
    	report.endTest(logger);
    	report.flush();
    }
}
