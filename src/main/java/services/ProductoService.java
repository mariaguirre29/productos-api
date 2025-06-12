package services;

import models.venta;
import dto.ProductoDTO;
import repository.ventaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ventaRepository productoRepository;

    private ProductoDTO toDTO(venta producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioUnitario(),
                producto.getCategoria(),
                producto.getActivo()
        );
    }

    private venta toEntity(ProductoDTO dto) {
        venta producto = new venta();
        producto.setId(dto.getId()); // importante para actualizar
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(dto.getActivo());
        return producto;
    }

    public ProductoDTO crear(ProductoDTO dto) {
        venta producto = toEntity(dto);
        return toDTO(productoRepository.save(producto));
    }

    public List<ProductoDTO> listar() {
        return productoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Integer id) {
        venta producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(producto);
    }

    public ProductoDTO actualizar(Integer id, ProductoDTO dto) {
        venta existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        existente.setCategoria(dto.getCategoria());
        existente.setActivo(dto.getActivo());

        return toDTO(productoRepository.save(existente));
    }

    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
}