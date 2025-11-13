package controlador;

import org.junit.Test;
import static org.junit.Assert.*;

public class BodegaTest {

    @Test
    public void testAgregarStockSumaCorrecto() {
        Control control = new Control();
        control.crearProducto("Manzana", 7);

        boolean ok = control.agregarStock("Manzana", 5);

        assertTrue("agregarStock debe retornar true al sumar stock válido", ok);
        assertEquals("El stock debe sumar 7 + 5 = 12", 12, control.obtenerStock().get(0).intValue());
    }

    @Test
    public void testVenderProductoDescuentaCorrecto() {
        Control control = new Control();
        control.crearProducto("Pera", 10);

        boolean ok = control.restarStock("Pera", 3);

        assertTrue("restarStock debe retornar true cuando hay stock suficiente", ok);
        assertEquals("El stock debe quedar en 7 después de vender 3", 7, control.obtenerStock().get(0).intValue());
    }

    @Test
    public void testVenderNoPermiteNegativo() {
        Control control = new Control();
        control.crearProducto("Banana", 10);

        boolean ok = control.restarStock("Banana", 11);

        assertFalse("restarStock debe retornar false si se intenta vender más que el stock", ok);
        assertEquals("El stock no debe cambiar cuando la venta no es posible", 10, control.obtenerStock().get(0).intValue());
    }
}
