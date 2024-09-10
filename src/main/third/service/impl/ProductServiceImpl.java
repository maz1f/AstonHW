package main.third.service.impl;

import main.second.dao.ProductDao;
import main.second.entity.Laptop;
import main.second.entity.Monitor;
import main.second.entity.Product;
import main.third.dto.*;
import main.third.exception.NotFoundEx;
import main.third.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> res = productDao.getAllProducts()
                .stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());

        return res;
    }

    @Override
    public ProductDto getProduct(long id) throws NotFoundEx {
        Product res = productDao.getProductById(id)
                .orElseThrow(() -> new NotFoundEx("User [id=" + id + "] not found"));

        return productToChild(res);
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {

        Product product = productRequestToProduct(productRequest);

        Product res = productDao.saveProduct(product);
        return productToChild(res);
    }

    @Override
    public ProductDto updateProduct(long id, ProductRequest productRequest) {
        ProductDto response = null;

        Optional<Product> productForUpdate = productDao.getProductById(id);

        if (productForUpdate.isEmpty()) {
            createProduct(productRequest);
        } else {
            Product product = productForUpdate.get();
            product.clone(productRequest.getName(), productRequest.getPrice());

            Product res = productDao.updateProduct(product);
            response = productToChild(res);
        }

        return response;
    }

    @Override
    public ProductDto deleteProduct(long id) throws NotFoundEx {
        Product productById = productDao
                .getProductById(id)
                .orElseThrow(() -> new NotFoundEx("Cant find user [id=" + id + "] to delete"));
        Product product = productDao.deleteProduct(productById);

        return productToChild(product);
    }

    private Product productRequestToProduct(ProductRequest productRequest) {
        Product product = null;
        switch (productRequest.getType()) {
            case LAPTOP: {
                product = new Laptop(
                        productRequest.getName(),
                        productRequest.getPrice(),
                        productRequest.getOs(),
                        productRequest.getModel()
                );
                break;
            }
            case MONITOR: {
                product = new Monitor(
                        productRequest.getName(),
                        productRequest.getPrice(),
                        productRequest.getManufacturer(),
                        productRequest.getGhz()
                );
                break;
            }
        }
        return product;
    }

    private ProductDto productToChild(Product product) {
        Class<? extends Product> pClass = product.getClass();

        ProductDto productDtoBlank = new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );

        ProductDto resDto = productDtoBlank;

        switch (pClass.getSimpleName()) {
            case "Laptop": {
                resDto = LaptopDto.builder()
                        .productDto(productDtoBlank)
                        .os(((Laptop) product).getOs())
                        .model(((Laptop) product).getModel())
                        .build();
                break;
            }
            case "Monitor": {
                resDto = MonitorDto.builder()
                        .productDto(productDtoBlank)
                        .ghz(((Monitor) product).getGhz())
                        .manufacturer(((Monitor) product).getManufacturer())
                        .build();
                break;
            }
        }
        return resDto;
    }
}
