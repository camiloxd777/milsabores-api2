package com.milsabores.milsabores_api.product;

public final class ProductMapper {

    private ProductMapper() {}

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .descripcion(product.getDescripcion())
                .precio(product.getPrecio())
                .categoria(product.getCategoria())
                .activo(product.getActivo())
                .build();
    }
}