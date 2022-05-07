package com.huaweicloud.samples;

import com.huaweicloud.samples.domain.Student;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProviderController {
  // a very simple service to echo the request parameter
  @GetMapping("/sayHello")
  public String sayHello(@RequestParam("name") String name) {
    return "Hello " + name;
  }
  @GetMapping("/sayHelloFeign")
  public String sayHelloFeign(@RequestParam("name") String name) {
    return "Hello " + name;
  }

  @PostMapping("/addStudent")
  public String addStudent(@RequestBody Student student){
    return student.toString();
  }
}
