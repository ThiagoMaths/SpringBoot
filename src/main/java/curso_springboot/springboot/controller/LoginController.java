package curso_springboot.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

@RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login() {
        return "login";
    }

}
