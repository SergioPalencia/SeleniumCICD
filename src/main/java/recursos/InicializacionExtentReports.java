package recursos;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class InicializacionExtentReports {
	
	public static ExtentReports configuracionExtentReports() {
		String rutaFicheroReport = System.getProperty("user.dir") + "\\Reports\\index.html";
		File miFicheroReport = new File(rutaFicheroReport);
		ExtentSparkReporter miReporter = new ExtentSparkReporter(miFicheroReport);
		miReporter.config().setReportName("Ver resultados");
		miReporter.config().setDocumentTitle("Resultados informe de pruebas");
		
		ExtentReports miReport = new ExtentReports();
		miReport.attachReporter(miReporter);
		miReport.setSystemInfo("Tester", "SPB");
		return miReport;
	}
}
