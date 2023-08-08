package com.example.demo.wiremock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WireMockConfig {

  @Bean
  public ServiceInstanceListSupplier serviceInstanceListSupplier() {
    return new TestServiceInstanceListSupplier("books-service", 8081);
  }

  @Bean(initMethod = "start", destroyMethod = "stop")
  public WireMockServer mockBooksService() {
    return new WireMockServer(8081);
  }

  @Bean
  public BooksClientRepository booksClientRepository(
      WireMockServer wireMockServer, ObjectMapper objectMapper) {
    return new BooksClientRepository(wireMockServer, objectMapper);
  }
}
