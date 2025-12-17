package SPB.casosPrueba;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SPB.utilidadesPruebas.BasePruebas;
import SPB.utilidadesPruebas.Reintento;
import tiendaVirtual.PageObject.Carrito;
import tiendaVirtual.PageObject.CatalogoProductos;

public class TC_Errores extends BasePruebas {
	@Test (groups = {"Sanity"}, retryAnalyzer=Reintento.class)
	public void tc_LoginError() throws IOException {
		// TODO Auto-generated method stub
		miLandingPage.login("elquartojinetee@hotmail.com", "PassIncorrecto");
		Assert.assertTrue(miLandingPage.obtenerMensajeError().equalsIgnoreCase("Incorrect email or password."));
	}
	
	
	@Test
	public void tc_CarritoError() throws IOException {
		// TODO Auto-generated method stub
		String nombreProducto = "ADIDAS ORIGINAL";
				
		CatalogoProductos miCatalogoProductos = miLandingPage.login("elquartojinetee@hotmail.com", "Seleniumpass1");
		
		miCatalogoProductos.anadirProducto(nombreProducto);
		Carrito miCarrito = miCatalogoProductos.accederCarrito();
		
		Assert.assertFalse(miCarrito.comprobarProducto("ProductoErroneo"));
	}
}
