package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.Loginpage;

public class BaseClass extends WebDriverUtility{	
	/*Create Object*/
	public	FileUtility fLib = new FileUtility();
	public 	ExcelUtility elib = new ExcelUtility();
	public	JavaUtility jlib = new JavaUtility();
	public	DataBaseUtility dbLib = new DataBaseUtility();
	public  WebDriverUtility wblib = new WebDriverUtility();
    public WebDriver driver = null;
    public static WebDriver sdriver = null;
    
	@BeforeSuite (groups = {"Smoke Testing","Regression Testing"})
	public void configBS() {
		System.out.println("=====Connect to DB, Report Config======");
		dbLib.getDbConnection();
	}
    //@Parameters ("BROWSER")
	@BeforeClass (groups = {"Smoke Testing","Regression Testing"})
	public void configBC(/*String browser*/) throws Throwable {
		System.out.println("==Launch the BROWSER==");
		//String BROWSER = browser;
		String BROWSER = System.getProperty("browser",fLib.getDataFromPropertiesFile("browser"));
		/*String BROWSER=fLib.getDataFromPropertiesFile("browser");*/
        if (BROWSER.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (BROWSER.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (BROWSER.equals("edge")) {
            driver = new EdgeDriver();
        }
        sdriver=driver;
	}
	@BeforeMethod (groups = {"Smoke Testing","Regression Testing"})
	public void configBM() throws Throwable {
		System.out.println("=login=");
		String URL = System.getProperty("url",fLib.getDataFromPropertiesFile("url"));
		String USERNAME = System.getProperty("username",fLib.getDataFromPropertiesFile("username"));
		String PASSWORD = System.getProperty("password",fLib.getDataFromPropertiesFile("password"));
		/*String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");*/
		Loginpage lp = new Loginpage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}
	@AfterMethod (groups = {"Smoke Testing","Regression Testing"})
	public void configAM() {
		System.out.println("=logout=");
	Homepage hp = new Homepage(driver);
	hp.logout();
	}
	@AfterClass (groups = {"Smoke Testing","Regression Testing"})
	public void configAC() {
		System.out.println("==Close the BROWSER==");
		driver.quit();
	}
	@AfterSuite (groups = {"Smoke Testing","Regression Testing"})
	public void configAS() {
		System.out.println("=====close Db, Report backup======");
		dbLib.closeDbconnection();
	}

}
