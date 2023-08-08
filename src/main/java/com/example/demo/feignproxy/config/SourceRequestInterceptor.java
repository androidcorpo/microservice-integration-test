package com.example.demo.feignproxy.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class SourceRequestInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    template.header("X-Source", "demo-service");
  }
}
