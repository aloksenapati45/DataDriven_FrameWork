package driverFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.AddCandidate;
import applicationLayer.AddEmployee;
import config.Base;
import utilities.ExcelFileUtil;

public class AppTest extends Base
{
	String Candidateinput = "./FileInput/AddCandidate.xlsx";
	String Candidateoutput = "./FileOutput/AddCandidate_Reslut.xlsx";
	String inputpath = "./FileInput/AddEmployee.xlsx";
	String outputpath = "./FileOutput/AddEmployee_Result.xlsx";
	ExtentReports report;
	ExtentTest logger;
	String TCName = "addemployee";
	String CandidateSheet = "Candidate";
    @Test(priority = 1)
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
    @Test(priority = 0)
    public void verifyCandidate() throws Throwable
    {
    	report = new ExtentReports("./target/reports/AddCandidate.html");
    	ExcelFileUtil xl = new ExcelFileUtil(Candidateinput);
    	int rc = xl.getRow(CandidateSheet);
    	Reporter.log("count of row : ",rc,true);
    	for(int i =1;i<=rc;i++)
    	{
    		logger = report.startTest("Candidate test");
    		logger.assignAuthor("Alok");
    		String fname = xl.getCellData(CandidateSheet, i, 0);
    		String mname = xl.getCellData(CandidateSheet, i, 1);
    		String lname = xl.getCellData(CandidateSheet, i, 2);
    		String email = xl.getCellData(CandidateSheet, i, 3);
    		String contact = xl.getCellData(CandidateSheet, i, 4);
    		String keyboard = xl.getCellData(CandidateSheet, i, 5);
    		String comment = xl.getCellData(CandidateSheet, i, 6);
    		logger.log(LogStatus.INFO, fname+"  "+mname+"   "+lname+"    "+email+"    "+contact+"   "+keyboard+"    "+comment);
    		AddCandidate cand = PageFactory.initElements(driver, AddCandidate.class);
    		boolean res = cand.verifyCandidate(fname, mname, lname, email, contact, keyboard, comment);
    		if(res)
    		{
    			logger.log(LogStatus.PASS, "cadidate successfully uploaded");
    			xl.setCellData(CandidateSheet, i, 7, "Pass", Candidateoutput);
    		}
    		if(!res)
    		{
    			logger.log(LogStatus.FAIL, "candidate not updated");
    			xl.setCellData(CandidateSheet, i, 7, "fail", Candidateoutput);
    		}
    	}
    	report.endTest(logger);
    	report.flush();
    }
}
