package upbiker.crm;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class Controlador {
    private final Inventario inventario = new Inventario();

    @PostMapping("/productos")
    public void agregarProducto(@RequestBody ProductoRequest request) {
        inventario.agregarProducto(request.getNombre(), request.getPrecio(), request.getCategoria());
    }

    @DeleteMapping("/productos")
    public void eliminarProducto(@RequestParam String nombre, @RequestParam String categoria) {
        inventario.eliminarProducto(nombre, categoria);
    }


    @GetMapping("/productos")
    public List<Inventario.Producto> listarProductos(@RequestParam String categoria) {
        return inventario.listarProductosPorCategoria(categoria);
    }

    @GetMapping("/valor-total")
    public double calcularValorTotal() {
        return inventario.calcularValorTotalInventario();
    }
}


class ProductoRequest {
    private String nombre;
    private double precio;
    private String categoria;

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }
}
