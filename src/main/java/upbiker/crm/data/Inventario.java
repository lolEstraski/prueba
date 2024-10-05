package upbiker.crm.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Inventario {

    private Map<String, List<Producto>> productosPorCategoria = new HashMap<>();

    public Map<String, List<Producto>> getProductosPorCategoria() {
        return productosPorCategoria;
    }

    public Producto agregarProducto(String nombre, double precio, String categoria){
        crearCategoriaSiNoExiste(categoria);
        var productosPorCategoria = listarProductosPorCategoria(categoria);
        var nuevoProducto = new Producto(nombre, precio, categoria);
        productosPorCategoria.add(nuevoProducto);
        return nuevoProducto;
    }

    private void crearCategoriaSiNoExiste(String categoria) {
        if (!this.productosPorCategoria.containsKey(categoria)) {
            this.getProductosPorCategoria().put(categoria, new ArrayList<>());
        }
    }

    public void eliminarProducto(String nombre, String categoria){
        var productosPorCategoria = listarProductosPorCategoria(categoria);
        productosPorCategoria.stream()
        .filter(producto -> producto.getNombre().equals(nombre))
        .findFirst()
        .ifPresent(productosPorCategoria::remove);
    }

    public List<Producto> listarProductosPorCategoria(String categoria){
        return this.productosPorCategoria.getOrDefault(categoria, new ArrayList<>());
    }

    public Double calcularValorTotalInventario(){
        Double total = 0.0;
        for ( var productosPorCategoria : this.productosPorCategoria.entrySet()) {
            var totalPorCategoria = productosPorCategoria.getValue().stream()
            .map(Producto::getPrecio)
            .reduce(0.0, Double::sum);
            total += totalPorCategoria;
        }
        return total;
    }

    public class Producto {
        private String nombre;
        private Double precio;
        private String categoria;

        public Producto(String nombre, Double precio, String categoria) {
            this.nombre = nombre;
            this.precio = precio;
            this.categoria = categoria;
        }

        public String getNombre() {
            return nombre;
        }

        public Double getPrecio() {
            return precio;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
    }

}
