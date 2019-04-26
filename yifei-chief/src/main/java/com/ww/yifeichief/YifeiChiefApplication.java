package com.ww.yifeichief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(scanBasePackages = "com.ww")
@EnableDiscoveryClient
public class YifeiChiefApplication {

	public static void main(String[] args) {
		SpringApplication.run(YifeiChiefApplication.class, args);
	}

}
