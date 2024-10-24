package train.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import train.backend.common.JsonData;

@RestController
@RequestMapping("/admin/train/numberDetail")
public class TrainNumberDetailController {
  @RequestMapping("/list.page")
  public ModelAndView page() {
    return new ModelAndView("trainNumberDetail");
  }

  @RequestMapping("/list.json")
  public JsonData list() {
    return JsonData.success();
  }

  @RequestMapping("/save.json")
  public JsonData save() {
    return JsonData.success();
  }
}
