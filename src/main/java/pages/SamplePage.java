package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SamplePage extends BasePage {

	public SamplePage(AppiumDriver driver) {
		super(driver);
	}

	// UI Elements using cross-platform locators

	@AndroidFindBy(id = "com.example.app:id/walletNameInput")
	@iOSXCUITFindBy(accessibility = "walletNameInput")
	private List<WebElement> walletName;

	@AndroidFindBy(id = "com.example.app:id/createButton")
	@iOSXCUITFindBy(accessibility = "createButton")
	private WebElement createWalletButton;

	@AndroidFindBy(id = "com.example.app:id/successMessage")
	@iOSXCUITFindBy(accessibility = "successMessage")
	private WebElement successMessage;

	// Actions

	public void clickWalletName(String name) {
		for (WebElement webElement : walletName) {
			if (webElement.getText().equals(name)) {
				webElement.click();
				break;
			}
		}
	}

	public void clickCreateWallet() {
		createWalletButton.click();
	}

	public String getSuccessMessage() {
		return successMessage.getText();
	}

}
