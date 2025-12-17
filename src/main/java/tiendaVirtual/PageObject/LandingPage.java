package tiendaVirtual.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tiendaVirtual.ComponentesAbstractos.ComponenteAbstracto;

public class LandingPage extends ComponenteAbstracto {
	
	WebDriver miWebDriver;
	
	public LandingPage(WebDriver miWebDriver) {
		super(miWebDriver);
		this.miWebDriver = miWebDriver;	
		PageFactory.initElements(miWebDriver, this);
	}
	
	@FindBy(id="userEmail")
	protected WebElement userEmail;
	
	@FindBy(id="userPassword")
	protected WebElement userPassword;
	
	@FindBy(id="login")
	protected WebElement botonLogin;
	
	@FindBy(xpath="//div[contains(@aria-label, 'Incorrect')]")
	protected WebElement etiquetaError;
	
	public CatalogoProductos login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		botonLogin.click();
		return new CatalogoProductos(miWebDriver);
	}
	
	public void abrirURL() {
		miWebDriver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String obtenerMensajeError() {
		esperarVisibilidadElemento(etiquetaError);
		return etiquetaError.getText();
	}
}
