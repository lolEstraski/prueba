package upbiker.crm.controllador;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import upbiker.crm.data.Inventario;


@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final Inventario inventario;

    public InventarioController(Inventario inventario) {
        this.inventario = inventario;
    }

    @PostMapping("/producto")
    public Inventario.Producto agregarProducto(@RequestBody  ProductoRequest request) {
        return inventario.agregarProducto(request.nombre(), request.precio(), request.categoria());
    }

    @DeleteMapping("/producto")
    public void eliminarProducto(@RequestParam (name= "nombre") String nombre, @RequestParam String categoria) {
        inventario.eliminarProducto(nombre, categoria);
    }


    @GetMapping("/producto")
    public List<Inventario.Producto> listarProductos(@RequestParam(name="categoria") String categoria) {
        return inventario.listarProductosPorCategoria(categoria);
    }

    @GetMapping("/total")
    public Double calcularValorTotal() {
        return inventario.calcularValorTotalInventario();
    }

}
