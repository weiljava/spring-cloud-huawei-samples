package com.huaweicloud.samples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class ConsumerController {

  @Value("${consumer.config}")
  private String configValue;

  @GetMapping("/getConfig2")
  public String getConfig2() {
    return configValue;
  }

}
