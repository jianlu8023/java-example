package com.github.jianlu8023.example.integration.middleware.elasticsearch.service.impl;

import com.github.jianlu8023.example.integration.middleware.elasticsearch.entity.*;
import com.github.jianlu8023.example.integration.middleware.elasticsearch.repository.*;
import com.github.jianlu8023.example.integration.middleware.elasticsearch.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAll() {
        final Iterable<Product> all = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        all.forEach(products::add);
        return products;
    }
}
