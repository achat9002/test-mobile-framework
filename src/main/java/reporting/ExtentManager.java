package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public static ExtentReports getInstance() {
        return extentReports;
    }

    public static void createReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Environment", "QA");
    }
}
