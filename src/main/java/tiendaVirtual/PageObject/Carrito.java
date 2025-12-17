package tiendaVirtual.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tiendaVirtual.ComponentesAbstractos.ComponenteAbstracto;

public class Carrito extends ComponenteAbstracto {
	
	WebDriver miWebDriver;
	
	public Carrito(WebDriver miWebDriver) {
		super(miWebDriver);
		this.miWebDriver = miWebDriver;	
		PageFactory.initElements(miWebDriver, this);
	}
	
	@FindBy(css=".cartSection h3")
	protected List<WebElement> listaProductos;
	
	@FindBy(css=".totalRow button")
	protected WebElement botonCheckout;
		
	public Boolean comprobarProducto(String nombreProducto) {
		return listaProductos.stream().anyMatch(producto -> producto.getText().equalsIgnoreCase(nombreProducto));
	}
	
	public CheckOut accederCheckout() {
		botonCheckout.click();
		return new CheckOut(miWebDriver);
	}
	
}
