package com.github.jianlu8023.example.integration.middleware.elasticsearch.service;

import com.github.jianlu8023.example.integration.middleware.elasticsearch.entity.*;

import java.util.*;

public interface ProductService {


    void saveProduct(Product product);

    List<Product> findByName(String name);

    List<Product> findAll();


}
