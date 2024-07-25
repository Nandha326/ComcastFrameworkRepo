package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void WaitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToTabonURL(WebDriver driver,String partialURL) {
		Set<String>set=driver.getWindowHandles();
        Iterator<String>it=set.iterator();
        while(it.hasNext()) {
        	String windowID = it.next();
        	driver.switchTo().window(windowID);
        	
        	String actUrl =driver.getCurrentUrl();
        	if (actUrl.contains(partialURL)) {
        		break;
        	}
        }
	}
	public void switchToTabonTitle(WebDriver driver,String partialTitle) {
		Set<String>set=driver.getWindowHandles();
        Iterator<String>it=set.iterator();
        while(it.hasNext()) {
        	String windowID = it.next();
        	driver.switchTo().window(windowID);
        	
        	String actUrl =driver.getCurrentUrl();
        	if (actUrl.contains(partialTitle)) {
        		break;
        	}
        }
	}
	public void SwitchtoFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	public void SwitchtoFrame(WebDriver driver,String nameID) {
		driver.switchTo().frame(nameID);
	}
	public void SwitchtoFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	public void SwitchtoAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void SwitchtoAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void select (WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void select (WebElement element,String Text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(Text);
	}
	public void MousemoveOnElement (WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void ClickAndHold(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();
	}
	public void RightClick(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	public void DoubleClick(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	public void Draganddrop(WebDriver driver,WebElement source,WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}
	public void ScrollToElement(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}
	public void ScrollbyValue(WebDriver driver,int x,int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}
	public static String takeScreenshot(WebDriver driver, String methdName) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot)driver;
		//Temporary location0
		File srcfile = ts.getScreenshotAs(OutputType.FILE);
		//Destination File
		File destfile=new File("./Screenshots/homepage"+methdName+".png");
		String Dest =destfile.getPath();
		FileHandler.copy(srcfile, destfile);
		return Dest;
	}
	public void JSExecutor(WebDriver driver,String key, String Obj) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(key,Obj);
	}
}
