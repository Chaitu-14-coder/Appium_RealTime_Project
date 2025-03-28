package utilities;
//Listener class used to generate extents reports
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.BaseTest;

public class Reporting extends TestListenerAdapter {
   public ExtentReports extent;
   public ExtentTest logger;
   public ExtentHtmlReporter htmlReporter;
  


    public void onStart(ITestContext context)
    {

//        try {
            System.out.println("*** Test Suite " + context.getName() + " started ***");
            String timestamp=new SimpleDateFormat("yyy.mm.dd.HH.mm.ss").format(new Date()); //timestamp


            String repName= "Test-Report-"+timestamp+".html";
           // System.out.println(System.getProperty("user.dir")+"\\test-output\\"+repName);
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/target/"+repName); //specify location
            htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");

            extent =new ExtentReports();

            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Host name","localhost");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("user", "chaitali");

            htmlReporter.config().setDocumentTitle("Appium Android Testing");//tile of report
            htmlReporter.config().setReportName("Functional Test Report");//name of the report
            // setTestViewChartLocation(ChartLocation.Top);//location of the chart
            htmlReporter.config().setTheme(Theme.DARK);

//        }catch(Exception e) {
//            e.printStackTrace();
//        }


    }

    public void onTestSuccess(ITestResult tr)
    {
        System.out.println(("*** Success " + tr.getMethod().getMethodName() + "..."));
        logger=extent.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passeed information
    }

    public void onTestFailure(ITestResult tr)
    {
        System.out.println(("*** Test Failed " + tr.getMethod().getMethodName() + "..."));
        logger=extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        //String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";//save the screenshots with screenshot name.png
        String screenshotDir = "C:\\Users\\DELL\\eclipse-workspace\\Appium_RealTime_Project\\Screenshots\\";
        String screenshotPath = screenshotDir + tr.getName() + ".png"; 
        File screenshotDirFile = new File(screenshotDir);
        if (!screenshotDirFile.exists()) {
            screenshotDirFile.mkdirs(); // Create the directory if it doesn't exist
        }
        BaseTest base=new BaseTest();
        File screenshot = ((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);
        if(destFile.exists()) {
            try {
            	FileUtils.copyFile(screenshot, destFile);
                logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
                System.out.println("Screenshot capture successfully");
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult tr)
    {
        System.out.println(("*** test Skipped " + tr.getMethod().getMethodName() + "..."));

        logger=extent.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));//send the passed information

    }

    public void onFinish(ITestContext context)
    {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        extent.flush();


    }





}