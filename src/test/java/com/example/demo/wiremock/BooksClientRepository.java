package com.example.demo.wiremock;

import com.example.demo.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Collections;

@RequiredArgsConstructor
public class BooksClientRepository {
  private final WireMockServer wireMockServer;
  private final ObjectMapper objectMapper;

  public void stubGet(String id) {
    String responseBody =
        "{ \"productId\": \"828bc3cb-52f0-482b-8247-d3db5c87c941\", \"name\": \"Phone\", \"stock\": "
            + id
            + "}";
    wireMockServer.stubFor(
        WireMock.get(WireMock.urlMatching("/products/" + id))
            .willReturn(WireMock.okJson(responseBody)));
  }

  public void stubCreate() {
    String responseBody =
        "{ \"productId\": \"828bc3cb-52f0-482b-8247-d3db5c87c941\", \"name\": \"Phone\", \"stock\": 3}";
    wireMockServer.stubFor(
        WireMock.post("/products")
            .withRequestBody(WireMock.equalToJson("{ \"name\": \"Phone\", \"initialStock\": 3}"))
            .willReturn(WireMock.okJson(responseBody)));
  }

  @SneakyThrows
  public void stublistBook() {
    wireMockServer.stubFor(
        WireMock.get("/books")
            .willReturn(
                WireMock.okJson(
                    objectMapper.writeValueAsString(
                        Collections.singletonList(new Book("title", "author"))))));
  }
}
