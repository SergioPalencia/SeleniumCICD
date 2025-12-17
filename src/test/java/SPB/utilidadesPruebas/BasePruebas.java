package SPB.utilidadesPruebas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import tiendaVirtual.PageObject.LandingPage;

public class BasePruebas {
	
	public WebDriver miWebDriver = null;
	public LandingPage miLandingPage = null;
	
	public WebDriver inicializacionPruebas() throws IOException {
		Properties misProperties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\recursos\\tiendaVirtual.properties");
		misProperties.load(fis);
		
		String navegador = System.getProperty("Navegador")!= null ? System.getProperty("Navegador") : misProperties.getProperty("Navegador", "Chrome");
				
		WebDriver miWebDriver = null;
		if (navegador.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			miWebDriver = new FirefoxDriver();
			
		}
		else if (navegador.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			miWebDriver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			ChromeOptions opciones = new ChromeOptions();
			if (navegador.contains("headless")) {
				opciones.addArguments("--headless=new");
				opciones.addArguments("--window-size=1440,900");
				opciones.addArguments("--disable-gpu");
				opciones.addArguments("--force-device-scale-factor=1");
				opciones.addArguments("--no-sandbox");
			}
			miWebDriver = new ChromeDriver(opciones);
		}
		
		miWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		//miWebDriver.manage().window().maximize();
		return miWebDriver;
	}
	
	public String tomarFoto(String nombreTC, WebDriver miWebDriver) throws IOException {
		String rutaFoto =  System.getProperty("user.dir") + "\\src\\test\\java" + nombreTC + ".png";
		
		TakesScreenshot camaraFotos = (TakesScreenshot)miWebDriver;
		File foto = camaraFotos.getScreenshotAs(OutputType.FILE);
		File ficheroFoto = new File(rutaFoto);
		
		FileUtils.copyFile(foto, ficheroFoto);
		return rutaFoto;
	}
	
	public List<HashMap<String, String>> leerDatosEntradaCompraJSON(String rutaJSON) throws IOException {
		String datosEntrada = FileUtils.readFileToString(new File(rutaJSON), Charset.defaultCharset());
	
		ObjectMapper miMapper = new ObjectMapper();
		return miMapper.readValue(datosEntrada, new TypeReference<List<HashMap<String, String>>> () {});
	}
	
	public LandingPage accederURLTiendaVirtual() throws IOException {
		miWebDriver = inicializacionPruebas();
		LandingPage miLandingPage = new LandingPage(miWebDriver);
		miLandingPage.abrirURL();
		return miLandingPage;
	}
	
	@BeforeMethod (alwaysRun = true)
	public void accederLandingPage() throws IOException {
		miLandingPage = accederURLTiendaVirtual();
	}
	
	@AfterMethod (alwaysRun = true)
	public void cierreEjecucion() {
		miWebDriver.quit();
	}
	

}
