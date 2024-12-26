package emaillist03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmaillistController {

    @RequestMapping("/")
    public String index() {
        return "/WEB-ONF/views/index.jsp";
    }

    // form


    // add
}
