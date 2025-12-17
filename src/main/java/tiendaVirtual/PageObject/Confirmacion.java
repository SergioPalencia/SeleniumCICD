package tiendaVirtual.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tiendaVirtual.ComponentesAbstractos.ComponenteAbstracto;

public class Confirmacion extends ComponenteAbstracto {

	WebDriver miWebDriver;
	
	public Confirmacion(WebDriver miWebDriver) {
		// TODO Auto-generated constructor stub
		super(miWebDriver);
		this.miWebDriver = miWebDriver;	
		PageFactory.initElements(miWebDriver, this);
	}
	
	@FindBy(css=".hero-primary")
	protected WebElement mensajeConfirmacion;
	
	public String obtenerMensajeConfirmacion( ) {
		return mensajeConfirmacion.getText();
	}
		
}
