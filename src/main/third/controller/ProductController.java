package main.third.controller;

import main.third.dto.ProductDto;
import main.third.dto.ProductRequest;
import main.third.dto.CustomResponse;
import main.third.exception.NotFoundEx;
import main.third.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public CustomResponse<?> getProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();

        return CustomResponse.builder()
                .status(200)
                .body(allProducts)
                .build();
    }

    @GetMapping("/{id}")
    public CustomResponse<?> getProduct(@PathVariable("id") long id) throws NotFoundEx {
        ProductDto product = productService.getProduct(id);

        return CustomResponse.builder()
                .status(200)
                .body(List.of(product))
                .build();
    }

    @PostMapping
    public CustomResponse<?> createProduct(@RequestBody ProductRequest productRequest) {
        ProductDto product = productService.createProduct(productRequest);

        return CustomResponse.builder()
                .status(200)
                .body(List.of(product))
                .build();
    }

    @PutMapping("/{id}")
    public CustomResponse<?> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest productRequest) {
        ProductDto productDto = productService.updateProduct(id, productRequest);
        return CustomResponse.builder()
                .status(200)
                .body(List.of(productDto))
                .build();

    }

    @DeleteMapping("/{id}")
    public CustomResponse<?> deleteProduct(@PathVariable("id") long id) throws NotFoundEx {
        ProductDto productDto = productService.deleteProduct(id);
        return CustomResponse.builder()
                .status(200)
                .body(List.of(productDto))
                .build();

    }

}
