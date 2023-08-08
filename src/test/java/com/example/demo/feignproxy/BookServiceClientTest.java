package com.example.demo.feignproxy;

import com.example.demo.feignproxy.book.BooksClient;
import com.example.demo.feignproxy.book.CreateProductRequest;
import com.example.demo.feignproxy.book.CreateProductResponse;
import com.example.demo.model.Book;
import com.example.demo.wiremock.BooksClientRepository;
import com.example.demo.wiremock.WireMockConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = {WireMockConfig.class})
class BookServiceClientTest {
  @Autowired private BooksClient booksClient;
  @Autowired
  BooksClientRepository booksClientRepository;

  @BeforeEach
  void setup() {
    booksClientRepository.stubCreate();
    booksClientRepository.stubGet("676");
    booksClientRepository.stublistBook();
  }

  @Test
  void testInventoryServiceClientCreateProductWorksProperly() {

    CreateProductResponse response =
        booksClient.createProduct(new CreateProductRequest("Phone", 3));

    assertThat(response.getProductId()).isNotNull();
    assertThat(response.getName()).isEqualTo("Phone");
    assertThat(response.getStock()).isEqualTo(3);
  }

  @Test
  void testBookServiceClientgetList() {

    List<Book> response = booksClient.getBooks();

    assertThat(response).hasSize(1);
  }
  @Test
  void testBookServiceClientget() {

    CreateProductResponse product = booksClient.getProduct("676");

    assertThat(product.getStock()).isNotNull();
  }
}
