package com.github.jianlu8023.propertiesloadexample.config;

import org.springframework.beans.factory.config.*;
import org.springframework.core.env.*;
import org.springframework.core.io.support.*;

import java.io.*;
import java.util.*;

public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());

        Properties properties = factory.getObject();

        return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
    }

    // @Override
    // public PropertySource<?> createPropertySource(String sourceName, EncodedResource resource) throws IOException {
    //     Properties propertiesFromYaml = loadYaml(resource);
    //     if ("".equals(sourceName)) {
    //         sourceName = resource.getResource().getFilename();
    //     }
    //
    //     return new PropertiesPropertySource(sourceName, propertiesFromYaml);
    // }
    // private Properties loadYaml(EncodedResource resource) throws FileNotFoundException {
    //     try {
    //         YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
    //         factory.setResources(resource.getResource());
    //         factory.afterPropertiesSet();
    //         return factory.getObject();
    //     } catch (IllegalStateException e) {
    //         // for ignoreResourceNotFound
    //         Throwable cause = e.getCause();
    //         if (cause instanceof FileNotFoundException)
    //             throw (FileNotFoundException) e.getCause();
    //         throw e;
    //     }
    // }
}
