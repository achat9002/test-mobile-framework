# Sample Mobile Framework
This automation suite tests the **Generate Passcode Screen for New Wallet Creation** flow in a mobile application using **Appium**, **TestNG**, **Java**, and **Extent Reports**. The test covers various scenarios including passcode validation, biometric prompt handling, onboarding pop-ups, and wallet selection.

---

## 🗂 Project Structure

```
|-- src/
|   |-- base/                   # Base classes like BaseTest, BasePage
|   |-- pages/                  # Page Object classes (PasscodePage, BiometricPopup, etc.)
|   |-- tests/                  # TestNG test classes
|   |-- utils/                  # Utilities for driver, reporting, etc.
|-- test-output/               # Test results and Extent reports
|-- app/                       # Application binaries (APK/IPA)
|-- data/                      # Optional data files (Excel/JSON)
|-- pom.xml                    # Maven dependencies
|-- README.md                  # Project documentation
```

---

## ✅ Prerequisites

- Java 11 or above
- Maven 3.6+
- Node.js & Appium (`npm install -g appium`)
- Android Studio or Xcode setup
- Device/emulator/simulator available

Use `appium-doctor` to verify system setup.

---

## 🚀 How to Start Appium Server

You can start Appium with a custom port using:

```bash
appium server -p 4725 -a 127.0.0.1 -pa /wd/hub
```

Or with default settings:

```bash
appium
```

---

## 🧪 How to Execute Tests

Run the full suite with Maven:

```bash
mvn clean test -Dplatform=Android -DdeviceName=emulator-5554
```

Replace `platform` and `deviceName` as needed:

```bash
-Dplatform=iOS -DdeviceName="iPhone 14" -Dudid=auto
```

These values are accessed using `System.getProperty("platform")`, etc.

---

## 📜 Test Scenarios Covered

- Passcode screen display and validation
- Biometric popup after passcode setup
- "Keep up with the market!" pop-up after biometric denial
- Navigating onboarding flows (Enable/Skip)
- Viewing and selecting wallet tiles (Secret Phrase, Swift Beta)
- Creating wallet and navigating to home page

---

## 🧾 Extent Reports

Extent reports are automatically generated after each run:

```
/test-output/ExtentReport.html
```

Includes:
- Pass/Fail logs
- Screenshots on failure
- Test method names and steps

---

## 🧵 Parallel Execution Support

Use `ThreadLocal<AppiumDriver>` in driver utils for thread-safe parallel test execution. Modify your `testng.xml` as needed:

```xml
<suite name="Parallel Suite" parallel="methods" thread-count="2">
  <test name="Wallet Tests">
    <classes>
      <class name="tests.NewWalletTestSuite"/>
    </classes>
  </test>
</suite>
```

---

## 👨‍💻 Author

Maintained by: Abhishek Chatterjee 

---

## 📬 Need Help?

If you're running into issues, make sure:
- Appium server is running
- Device/emulator is connected and unlocked
- Correct capabilities are configured
- App path and version are valid