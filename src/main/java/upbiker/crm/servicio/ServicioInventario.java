package upbiker.crm.servicio;

import upbiker.crm.Inventario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServicioInventario {

    public void agregarProducto(String nombre, double precio, String categoria) {
        Inventario.Producto producto = new Inventario.Producto(nombre, precio, categoria);
        productosPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(producto);
    }

    public void crearProducto(String nombre, double precio, String categoria) {
        Producto productoExistente = productoRepository.findByNombre(nombre);
        if (productoExistente == null) {
            Producto producto = new Inventario.Producto(nombre, precio, categoria);
            productoRepositorio.save(producto);
        } else {
            throw new IllegalArgumentException("El producto ya existe.");
        }
    }


    public void eliminarProducto(String nombre, String categoria) {
        List<Inventario.Producto> productos = productosPorCategoria.get(categoria);
        if (productos != null) {
            productos.removeIf(producto -> producto.getNombre().equals(nombre));
        }
    }

    public List<Inventario.Producto> listarProductosPorCategoria(String categoria) {
        return productosPorCategoria.getOrDefault(categoria, Collections.emptyList());
    }

    public double calcularValorTotalInventario() {
        return productosPorCategoria.values().stream()
                .flatMap(List::stream)
                .mapToDouble(Inventario.Producto::getPrecio)
                .sum();
    }
}
