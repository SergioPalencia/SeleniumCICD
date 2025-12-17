package tiendaVirtual.ComponentesAbstractos;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tiendaVirtual.PageObject.Carrito;
import tiendaVirtual.PageObject.Pedidos;

public class ComponenteAbstracto {
	
	public WebDriver miWebDriver;	
	
	public ComponenteAbstracto(WebDriver miWebDriver) {
			this.miWebDriver = miWebDriver;
			PageFactory.initElements(miWebDriver, this);
		}
	
	@FindBy(css="[routerlink*='cart']")
	protected WebElement botonAccederCarrito;
	
	@FindBy(xpath="//button[contains(@routerlink, 'myorders')]")
	protected WebElement botonAccederPedidos;
	
	@FindBy(css=".ng-animating")
	protected WebElement spinner;
	
	protected void esperarVisibilidadElemento(By localizador) {
		WebDriverWait wait = new WebDriverWait(miWebDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
	}
	
	protected void esperarVisibilidadElemento(WebElement miWebElement) {
		WebDriverWait wait = new WebDriverWait(miWebDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(miWebElement));
	}
	
	protected void esperarInvisibilidadElemento(WebElement spinner) {
		WebDriverWait wait = new WebDriverWait(miWebDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));		
	}
	
	protected void esperarVisibilidadElementos(By localizador) {
		WebDriverWait wait = new WebDriverWait(miWebDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(localizador));	
	}
	
	protected void esperarAccesibilidadElemento(WebElement botonAccederCarrito) {
		WebDriverWait wait = new WebDriverWait(miWebDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(botonAccederCarrito));	
	}
	
	public Carrito accederCarrito() {
		esperarInvisibilidadElemento(spinner);
		botonAccederCarrito.click();
		return new Carrito(miWebDriver);
	}
	
	public Pedidos accederPedidos() {
		//ojo
		esperarInvisibilidadElemento(spinner);
		botonAccederPedidos.click();
		return new Pedidos(miWebDriver);
	}
		
}
	
