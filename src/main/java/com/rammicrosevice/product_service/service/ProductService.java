package com.rammicrosevice.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rammicrosevice.product_service.dto.ProductRequest;
import com.rammicrosevice.product_service.dto.ProductResponse;
import com.rammicrosevice.product_service.model.Product;
import com.rammicrosevice.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        // TODO Auto-generated method stub
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> maptoProductResponse(product)).toList();
    }

    private ProductResponse maptoProductResponse(Product product) {
        // TODO Auto-generated method stub
        return ProductResponse.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
