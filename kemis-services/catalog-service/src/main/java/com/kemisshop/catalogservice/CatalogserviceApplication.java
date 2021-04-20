package com.kemisshop.catalogservice;

import com.kemisshop.catalogservice.app.port.in.ModifyCategoryInPort;
import com.kemisshop.catalogservice.domain.Category;
import com.kemisshop.catalogservice.domain.ProductCategory;
import com.kemisshop.catalogservice.app.port.in.ModifyProductInPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@RefreshScope
public class CatalogserviceApplication {

	@Profile("!test")
	@Bean
	CommandLineRunner commandLineRunner (ModifyProductInPort modifyProductInPort,
										 ModifyCategoryInPort modifyCategoryInPort) {
		String UPLOAD_DIR = "kemis-services/product-images";

		return (strings) -> {
			Stream.of("kemis", "mekenet")
					.forEach(string -> modifyCategoryInPort
							.save(new ProductCategory(Category.findByLabel(string))));
			FileSystemUtils.deleteRecursively(new File(UPLOAD_DIR));
			Files.createDirectory(Paths.get(UPLOAD_DIR));

		};


	}
	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}

	@Bean
	@LoadBalanced // if this is not here the web client will not find the order service to make a call
	@Qualifier("WebClientLocal")
	WebClient.Builder getLoadBalancedWebClient() {
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogserviceApplication.class, args);
	}

}
