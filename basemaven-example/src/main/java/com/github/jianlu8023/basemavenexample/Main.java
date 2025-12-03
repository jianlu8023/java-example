package com.github.jianlu8023.basemavenexample;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");
        final Map<String, Object> config = LoadYaml.loadFromResources("config.yaml");
        config.forEach((k, v) -> System.out.println(k + " : " + v));
        final Map<String, Object> a = (Map<String, Object>) config.get("a");
        a.forEach((k, v) -> System.out.println(k + " : " + v));
        final Map<String, Object> b = (Map<String, Object>) a.get("b");
        b.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
