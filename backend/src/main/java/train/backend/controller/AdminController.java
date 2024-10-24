package train.backend.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import train.common.model.SysUser;

// for page, api ends with .page
@RestController
public class AdminController {

  @RequestMapping("/")
  public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // Is login
    Object object = request.getSession().getAttribute("user");
    if (object == null) {
      response.sendRedirect("/login.page");
    } else {
      response.sendRedirect("/admin/index.page");
    }
  }

  @GetMapping("/login.page")
  public ModelAndView login() {
    return new ModelAndView("login");
  }

  @RequestMapping("/logout.page")
  public void logout(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getSession().invalidate();
    String path = "/login.page";
    response.sendRedirect(path);
  }

  @RequestMapping("/mockLogin.page")
  public void mockLogin(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    SysUser sysUser = SysUser.builder().id(1).username("admin").build();
    request.getSession().setAttribute("user", sysUser);
    response.sendRedirect("/admin/index.page");
  }

  @RequestMapping("/admin/index.page")
  public ModelAndView index() {
    return new ModelAndView("admin");
  }

  @GetMapping("/welcome.page")
  public String welcome() {
    return "welcome";
  }
}
