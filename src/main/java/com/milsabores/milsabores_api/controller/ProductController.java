package com.milsabores.milsabores_api.controller;

import com.milsabores.milsabores_api.product.CreateOrUpdateProductRequest;
import com.milsabores.milsabores_api.product.ProductDto;
import com.milsabores.milsabores_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    // Para cliente (app): solo productos activos
    @GetMapping
    public List<ProductDto> getActiveProducts() {
        return productService.getAllActive();
    }

    // Para admin: ver todos
    @GetMapping("/admin")
    public List<ProductDto> getAllProducts() {
        return productService.getAllAdmin();
    }

    @PostMapping
    public ProductDto create(@RequestBody CreateOrUpdateProductRequest req) {
        return productService.create(req);
    }

    @PutMapping("/{id}")
    public ProductDto update(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateProductRequest req
    ) {
        return productService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}