package com.github.jianlu8023.example.integration.middleware.schedule;

import com.alibaba.fastjson.*;
import com.github.jianlu8023.example.integration.middleware.elasticsearch.entity.*;
import com.github.jianlu8023.example.integration.middleware.elasticsearch.service.*;
import com.github.jianlu8023.mock.generator.pojo.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ElasticSearchSchedule {
    private static final Logger L = LoggerFactory.getLogger(ElasticSearchSchedule.class);


    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private PojoGenerator<Product> pojoGenerator;

    @Autowired
    public void setPojoGenerator(PojoGenerator<Product> pojoGenerator) {
        this.pojoGenerator = pojoGenerator;
    }


    @Scheduled(cron = "0 0/2 * * * ?")
    void addProduct() {

        L.debug(" starting add product to elasticsearch ...");
        final Product generate = pojoGenerator.generate(Product.class);
        L.info("generate product {} ", JSONObject.toJSONString(generate));

        productService.saveProduct(generate);
        L.debug(" end add product to elasticsearch ...");

    }

    @Scheduled(cron = "0 0/5 * * * ?")
    void selectAllProduct() {
        L.debug(" starting select all product from elasticsearch ...");
        final List<Product> all = productService.findAll();
        all.forEach(o -> {
            L.info("product {} ", JSONObject.toJSONString(o));
        });
        L.debug(" end select all product from elasticsearch ...");
    }


}
