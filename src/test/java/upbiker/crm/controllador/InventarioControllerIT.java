package upbiker.crm.controllador;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class InventarioControllerIT {

    private final String CATEGORIA = "BMX";

    @Autowired
    private InventarioController inventarioController;

    @Order(1)
    @Test
    void debeAgregarProductoACategoria(){
        var request = new ProductoRequest("Bicicleta Chimano", 150320.0, CATEGORIA);
        var resultado = inventarioController.agregarProducto(request);
        Assertions.assertEquals(CATEGORIA, resultado.getCategoria());
        Assertions.assertEquals("Bicicleta Chimano", resultado.getNombre());
        Assertions.assertEquals(150320.0, resultado.getPrecio());
    }

    @Order(2)
    @Test
    void debeRetornarProductosAgregadosPorCategoria(){
        var productos = inventarioController.listarProductos(CATEGORIA);
        var resultado = productos.get(0);
        Assertions.assertEquals(CATEGORIA, resultado.getCategoria());
        Assertions.assertEquals("Bicicleta Chimano", resultado.getNombre());
        Assertions.assertEquals(150320.0, resultado.getPrecio());
    }

    @Order(3)
    @Test
    void debeElminarProductosPorCategoria(){
        
        inventarioController.eliminarProducto("Bicicleta Chimano", CATEGORIA);

        var productos = inventarioController.listarProductos(CATEGORIA);
       
        Assertions.assertEquals(0, productos.size());
    }

    @Order(4)
    @Test
    void debeCalcularValorTotalDelInventario(){
        
        var request = new ProductoRequest("Bicicleta Chimano", 150320.0, CATEGORIA);
        var request2 = new ProductoRequest("Bicicleta ABX", 500.0, CATEGORIA);
        var request3 = new ProductoRequest("Freno de disco", 200.0, CATEGORIA);
        inventarioController.agregarProducto(request);
        inventarioController.agregarProducto(request2);
        inventarioController.agregarProducto(request3);

        var resultado = inventarioController.calcularValorTotal();
       
        Assertions.assertEquals(151020.0, resultado);
    }
}
