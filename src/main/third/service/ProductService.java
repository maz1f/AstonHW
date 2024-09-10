package main.third.service;

import main.third.dto.ProductDto;
import main.third.dto.ProductRequest;
import main.third.exception.NotFoundEx;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProduct(long id) throws NotFoundEx;
    ProductDto createProduct(ProductRequest productRequest);
    ProductDto updateProduct(long id, ProductRequest productRequest);
    ProductDto deleteProduct(long id) throws NotFoundEx;

}
