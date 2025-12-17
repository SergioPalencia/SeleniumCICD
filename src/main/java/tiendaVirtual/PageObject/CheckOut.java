package tiendaVirtual.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tiendaVirtual.ComponentesAbstractos.ComponenteAbstracto;

public class CheckOut extends ComponenteAbstracto{

	WebDriver miWebDriver;
	
	public CheckOut(WebDriver miWebDriver) {
		// TODO Auto-generated constructor stub
		super(miWebDriver);
		this.miWebDriver = miWebDriver;	
		PageFactory.initElements(miWebDriver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	protected WebElement campoPais;
	
	@FindBy(xpath="(//span[contains(@class,'ng-star-inserted')])[2]")
	protected WebElement opcionPaisSeleccionado;
	
	@FindBy(css=".action__submit")
	protected WebElement botonEnviarOrden;
	
	protected By paisesDesplegados = By.cssSelector(".form-group span");
	
	public void seleccionarPais(String pais) {
		Actions a = new Actions(miWebDriver);
		a.sendKeys(campoPais, pais).build().perform();
		esperarVisibilidadElementos(paisesDesplegados);
		opcionPaisSeleccionado.click();		
	}
	
	public Confirmacion enviarOrden() {
		botonEnviarOrden.click();
		return new Confirmacion(miWebDriver);
	}

}
