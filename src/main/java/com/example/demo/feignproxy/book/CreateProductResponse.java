package com.example.demo.feignproxy.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
  private UUID productId;
  private String name;
  private int stock;
}
