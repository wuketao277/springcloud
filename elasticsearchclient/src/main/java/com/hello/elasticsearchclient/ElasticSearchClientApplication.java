package com.hello.elasticsearchclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wuketao
 * @date 2024/6/20
 * @Description
 */
@EnableSwagger2
@SpringBootApplication
public class ElasticSearchClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchClientApplication.class, args);
    }
}
