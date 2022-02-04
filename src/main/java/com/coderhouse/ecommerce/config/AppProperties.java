package com.coderhouse.ecommerce.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties
public class AppProperties {

    //size of n (128, 192, and 256) bits
    private int keySize = 128;
    private String algorithm = "AES/CBC/PKCS5Padding";

}
