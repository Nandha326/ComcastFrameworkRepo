package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.Creating_New_Organization;
import com.comcast.crm.objectrepositoryutility.Homepage;
import com.comcast.crm.objectrepositoryutility.OraganizationInfopage;
import com.comcast.crm.objectrepositoryutility.Organizationspage;

import junit.framework.Assert;

public class CreateContact_Test extends BaseClass {
	@Test(groups = "Smoke Testing")
	public void CreateContactTest() throws Throwable {
		// Read testScript data from Excel file
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// Step 2 : navigate to Contact module
		Homepage hp = new Homepage(driver);
		hp.getContactlink().click();

		// Step 3 : click on "create Contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContbtn().click();

		// Step 4 : enter all the details & create new contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact1(lastName);

		// Verify Header phone Number info Expected Result
		ContactInfoPage cif = new ContactInfoPage(driver);
		String actHeader = cif.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);
		soft.assertAll();

	}

	@Test(groups = "Regression Testing")
	public void createContactWithsupportDateTest() throws Throwable {

		// Read the TestScript data from Excel file
		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// Step 2 : navigate to Contact module
		Homepage hp = new Homepage(driver);
		Thread.sleep(2000);
		hp.getContactlink().click();
		// Step 3 : Click on "Create Contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContbtn().click();
		// Step 4 : Enter all the details & create new Contact
		String endDate = jlib.getRequiredDateYYYYDDMM(31);
		String startDate = jlib.getSystemDateYYYYDDMM();
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(lastName, startDate, endDate);

		System.out.println(startDate);
		System.out.println(endDate);

		// verify actual Name info Expected Result
		ContactInfoPage cif = new ContactInfoPage(driver);
		String actName = cif.getContactTitleText().getText();
		if (actName.trim().contains(lastName)) {
			System.out.println(lastName + " LastName is Verified == PASS");
		} else {
			System.out.println(lastName + " LastName is not Verified == FAIL");
		}
		// verify start date info Expected Result
		String start = cif.getSupStrtDate().getText();
		System.out.println(start);
		if (start.trim().equals(startDate)) {
			System.out.println(startDate + " Startdate is Verified == PASS");
		} else {
			System.out.println(startDate + " Startdate is not Verified == FAIL");
		}
		// verify End date info Expected Result
		String end = cif.getSupEndDate().getText();
		System.out.println(end);
		if (end.equals(endDate)) {
			System.out.println(endDate + " End date is Verified == PASS");
		} else {
			System.out.println(endDate + " End date is not Verified == FAIL");
		}

	}

	@Test(groups = "Regression Testing")
	public void createcontactwithOrgTest() throws Throwable {
		// Read the Test Script data from Excel file

		String OrgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		// Step 2 : navigate to Organisation module
		Homepage hp = new Homepage(driver);
		hp.getOrglink().click();
		// Step 3 : click on "Create Organisation" Button
		Organizationspage cnp = new Organizationspage(driver);
		cnp.getCreateNewOrgBtn().click();
		// Step 4 : enter all the details & create new Oraganization
		Creating_New_Organization cnop = new Creating_New_Organization(driver);
		cnop.createOrg(OrgName);

		// verify Header Message Expected Result
		OraganizationInfopage oip = new OraganizationInfopage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.trim().contains(OrgName)) {
			System.out.println(OrgName + " is created ==PASS");
		} else {
			System.out.println(OrgName + " is created ==FAIL");
		}
		// step 5: navigate to Contact module
		hp.getContactlink().click();
		// step 6 : click on "create Contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContbtn().click();
		// step 7 : enter all the details & create new Contact
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContact(contactLastName, OrgName);
		// verify Header Msg Expected Result
		ContactInfoPage cif = new ContactInfoPage(driver);
		String actName = cif.getContactTitleText().getText();
		if (actName.trim().contains(contactLastName)) {
			System.out.println(contactLastName + " header verified==PASS");
		} else {
			System.out.println(contactLastName + " header verified==FAIL");
		}
		// verify Header OrgName info Expected Result
		String actOrgName1 = cif.getMouseAreaorg().getText();
		if (actOrgName1.trim().equals(OrgName)) {
			System.out.println(OrgName + " Information is Created ==PASS");
		} else {
			System.out.println(OrgName + " Information is not Created  ==FAIL");
		}

	}
	//comment
	//comment1
	//comment3

}
