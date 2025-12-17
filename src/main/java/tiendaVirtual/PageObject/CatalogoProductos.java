package tiendaVirtual.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tiendaVirtual.ComponentesAbstractos.ComponenteAbstracto;

public class CatalogoProductos extends ComponenteAbstracto {
	
	WebDriver miWebDriver;
	
	public CatalogoProductos(WebDriver miWebDriver) {
		super(miWebDriver);
		this.miWebDriver = miWebDriver;	
		PageFactory.initElements(miWebDriver, this);
	}
		
	@FindBy(css=".card-body")
	protected List<WebElement> listaProductos;
	
	@FindBy(css=".ng-animating")
	protected WebElement spinner;
	
	protected By localizadorNombreProducto = By.cssSelector("b");
	protected By localizadorBotonProducto = By.cssSelector(".card-body button:last-of-type");
	protected By localizadorConfirmacion = By.cssSelector("#toast-container");
		
	public void anadirProducto(String nombreProducto) {
		WebElement productoSeleccionado = listaProductos.stream().filter(producto -> producto.findElement(localizadorNombreProducto).getText().equalsIgnoreCase(nombreProducto))
				.findFirst().orElse(null);	
		productoSeleccionado.findElement(localizadorBotonProducto).click();
		esperarVisibilidadElemento(localizadorConfirmacion);
		esperarInvisibilidadElemento(spinner);
	}
	
}
