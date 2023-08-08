package com.example.demo.feignproxy.book;

import com.example.demo.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("${books-service}")
public interface BooksClient {

  @RequestMapping("/books")
  List<Book> getBooks();

  @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
  CreateProductResponse createProduct(CreateProductRequest request);

  @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  CreateProductResponse getProduct(@PathVariable("id") String id);
}
