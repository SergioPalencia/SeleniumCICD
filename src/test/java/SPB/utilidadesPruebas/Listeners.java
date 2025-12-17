package SPB.utilidadesPruebas;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import recursos.InicializacionExtentReports;

public class Listeners extends BasePruebas implements ITestListener {

	ExtentReports miReport = InicializacionExtentReports.configuracionExtentReports();
	ExtentTest miTest = null;
	ThreadLocal<ExtentTest> miTestThreadSafe = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		miTest = miReport.createTest(result.getName());
		miTestThreadSafe.set(miTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		miTestThreadSafe.get().log(Status.PASS, "TC Correcto");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver miWebDriver = null;
		
		try {
			miWebDriver = (WebDriver)result.getTestClass().getRealClass().getField("miWebDriver").get(result.getInstance());
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		miTestThreadSafe.get().fail(result.getThrowable());
		
		String rutaFoto = null;
		try {
			rutaFoto = tomarFoto(result.getName(), miWebDriver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		miTestThreadSafe.get().addScreenCaptureFromPath(rutaFoto, result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		miReport.flush();
	}

}
