package SPB.casosPrueba;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SPB.utilidadesPruebas.BasePruebas;
import tiendaVirtual.PageObject.Carrito;
import tiendaVirtual.PageObject.CatalogoProductos;
import tiendaVirtual.PageObject.CheckOut;
import tiendaVirtual.PageObject.Confirmacion;
import tiendaVirtual.PageObject.Pedidos;

public class TC_Clientes extends BasePruebas {

	@Test(groups = {"Sanity"}, dataProvider = "obtenerDatosTCCompra")
	public void tc_CompraProducto(HashMap<String, String> datosCompra) throws IOException {
		// TODO Auto-generated method stub
						
		CatalogoProductos miCatalogoProductos = miLandingPage.login(datosCompra.get("login"), datosCompra.get("password"));
		
		miCatalogoProductos.anadirProducto(datosCompra.get("nombreProducto"));
		Carrito miCarrito = miCatalogoProductos.accederCarrito();
		
		Assert.assertTrue(miCarrito.comprobarProducto(datosCompra.get("nombreProducto")));
		CheckOut miCheckOut = miCarrito.accederCheckout();
		miCheckOut.seleccionarPais("india");
		Confirmacion miConfirmacion = miCheckOut.enviarOrden();
		
		Assert.assertTrue(miConfirmacion.obtenerMensajeConfirmacion().strip().equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"tc_CompraProducto"}, dataProvider = "obtenerDatosTCCompra")
	public void tc_HistorialPedidos(HashMap<String, String> datosCompra) {
		CatalogoProductos miCatalogoProductos = miLandingPage.login(datosCompra.get("login"), datosCompra.get("password"));
		Pedidos misPedidos = miCatalogoProductos.accederPedidos();
		Assert.assertTrue(misPedidos.existenciaPedido(datosCompra.get("nombreProducto")));
			
	}
	
	@DataProvider
	public Object[][] obtenerDatosTCCompra() throws IOException {
		List<HashMap<String, String>> datosTCCompra = leerDatosEntradaCompraJSON(System.getProperty("user.dir") + "\\src\\test\\java\\SPB\\datosPrueba\\datosEntradaCompra.json");	
		
		return new Object[][]{{datosTCCompra.get(0)}, {datosTCCompra.get(1)}};
	}

}
