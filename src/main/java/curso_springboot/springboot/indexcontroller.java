package curso_springboot.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexcontroller {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
}
