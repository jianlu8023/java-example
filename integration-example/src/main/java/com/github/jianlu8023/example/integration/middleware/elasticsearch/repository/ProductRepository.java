package com.github.jianlu8023.example.integration.middleware.elasticsearch.repository;

import com.github.jianlu8023.example.integration.middleware.elasticsearch.entity.*;
import org.springframework.data.elasticsearch.repository.*;

import java.util.*;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByName(String name);
}
