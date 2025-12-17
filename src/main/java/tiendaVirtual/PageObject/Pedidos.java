package tiendaVirtual.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tiendaVirtual.ComponentesAbstractos.ComponenteAbstracto;

public class Pedidos extends ComponenteAbstracto {

	WebDriver miWebDriver;
	
	public Pedidos(WebDriver miWebDriver) {
		super(miWebDriver);
		this.miWebDriver = miWebDriver;	
		PageFactory.initElements(miWebDriver, this);
	}
	
	@FindBy(xpath="//td[2]")
	protected List <WebElement> productosPedidos;
	
	public boolean existenciaPedido(String miProducto) {
		return productosPedidos.stream().anyMatch(producto -> producto.getText().equalsIgnoreCase(miProducto));
	}
}
