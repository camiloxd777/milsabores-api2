package com.milsabores.milsabores_api.service;

import com.milsabores.milsabores_api.product.*;
import com.milsabores.milsabores_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAllActive() {
        return productRepository.findByActivoTrue()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    public List<ProductDto> getAllAdmin() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Transactional
    public ProductDto create(CreateOrUpdateProductRequest request) {
        Product product = Product.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .categoria(request.getCategoria())
                .activo(request.getActivo())
                .build();

        return ProductMapper.toDto(productRepository.save(product));
    }

    @Transactional
    public ProductDto update(Long id, CreateOrUpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto " + id + " no encontrado"));

        product.setNombre(request.getNombre());
        product.setDescripcion(request.getDescripcion());
        product.setPrecio(request.getPrecio());
        product.setCategoria(request.getCategoria());
        product.setActivo(request.getActivo());

        return ProductMapper.toDto(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto " + id + " no existe");
        }
        productRepository.deleteById(id);
    }
}
