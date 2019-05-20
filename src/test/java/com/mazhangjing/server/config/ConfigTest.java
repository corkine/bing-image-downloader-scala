package com.mazhangjing.server.config;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

public class ConfigTest {
    @Test public void test() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Config config = yaml.loadAs(new FileReader(Paths.get("/Users/corkine/Nutstore Files/WorkFolder/helper/src/main/resources/config.yml").toFile()), Config.class);
        System.out.println("config = " + config);
    }
}