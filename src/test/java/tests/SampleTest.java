package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SamplePage;

public class SampleTest extends BaseTest {
	private SamplePage walletPage;

	@BeforeMethod(dependsOnMethods = "initReports" ,alwaysRun = true)
	public void setUpPrerequisit() {
		walletPage = new SamplePage(driver);
	}

	@Test(description = "Verify that a wallet can be created successfully with a valid name")
	public void testCreateWalletSuccessfully() {
		// Input wallet name
		walletPage.clickWalletName("MyTestWallet");

		// Tap create wallet button
		walletPage.clickCreateWallet();

		// Assert success message
		String actualMessage = walletPage.getSuccessMessage();
		Assert.assertEquals(actualMessage, "Wallet created successfully", "Wallet creation failed!");
	}

	@Test(description = "Verify that wallet creation fails with an empty name")
	public void testSwiftWalletWithEmptyName() {
		// Enter empty wallet name
		walletPage.clickWalletName("Swift");

		// Try to create wallet
		walletPage.clickCreateWallet();

		// Assert error or no success message
		String actualMessage = walletPage.getSuccessMessage();
		Assert.assertNotEquals(actualMessage, "Wallet created successfully",
				"Wallet should not be created with Swift name.");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDownPrerequisits() {
		walletPage = null;
	}
}