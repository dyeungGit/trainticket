package train.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import train.backend.common.JsonData;

@RestController
public class TestController {

  @RequestMapping("/test")
  public JsonData test() {
    return JsonData.success();
  }
}
