package upbiker.crm.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class InventarioTest {

    private final static String PRIMER_PRODUCTO = "Bicicleta Chimano";
    private final static Double PRECIO_PRIMER_PRODUCTO = 1354.0;
    private final static String CATEGORIA_CIUDAD = "ciudad";
    private Inventario inventario;


    @BeforeEach
    public void reiniciarInventario(){
        inventario = new Inventario();
    }

    @Test
    void debeAgregarProducto(){
        var productoAgregado = inventario.agregarProducto(PRIMER_PRODUCTO, PRECIO_PRIMER_PRODUCTO,CATEGORIA_CIUDAD);
        Assertions.assertEquals(PRIMER_PRODUCTO, productoAgregado.getNombre());
        Assertions.assertEquals(PRECIO_PRIMER_PRODUCTO, productoAgregado.getPrecio());
        Assertions.assertEquals(CATEGORIA_CIUDAD, productoAgregado.getCategoria());
    }

    @Test
    void debeListarProductosPorCategoria(){
        inventario.agregarProducto(PRIMER_PRODUCTO, PRECIO_PRIMER_PRODUCTO,CATEGORIA_CIUDAD);
        var productos = inventario.listarProductosPorCategoria(CATEGORIA_CIUDAD);
        Assertions.assertEquals(1, productos.size());
        var producto = productos.get(0);
        Assertions.assertEquals(PRIMER_PRODUCTO, producto.getNombre());
        Assertions.assertEquals(PRECIO_PRIMER_PRODUCTO, producto.getPrecio());
        Assertions.assertEquals(CATEGORIA_CIUDAD, producto.getCategoria());
    }

    @Test
    void debeEliminarProductosPorCategoria(){
        inventario.agregarProducto(PRIMER_PRODUCTO, PRECIO_PRIMER_PRODUCTO,CATEGORIA_CIUDAD);
        inventario.eliminarProducto(PRIMER_PRODUCTO, CATEGORIA_CIUDAD);
        var productos = inventario.listarProductosPorCategoria(CATEGORIA_CIUDAD);
        Assertions.assertEquals(0, productos.size());
    }

}
