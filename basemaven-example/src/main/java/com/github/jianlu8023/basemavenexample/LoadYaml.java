package com.github.jianlu8023.basemavenexample;

import org.yaml.snakeyaml.*;

import java.io.*;
import java.util.*;


public class LoadYaml {

    public static Map<String, Object> loadFromResources(String fileName) throws FileNotFoundException {
        return loadFromResources(LoadYaml.class, fileName);
    }

    public static Map<String, Object> loadFromResources(Class<?> context, String fileName) throws FileNotFoundException {
        InputStream input = context.getResourceAsStream("/" + fileName);
        if (input == null) {
            input = new FileInputStream(fileName);
        }
        Yaml yml = new Yaml();
        return yml.load(input);
    }

}
