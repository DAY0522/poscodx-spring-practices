package hellospring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "/WEB-INF/views/hello.jsp";
    }

    @RequestMapping("/hello2")
    public String hello2(@RequestParam("name") String name) {
        System.out.println("name: " + name);
        return "/WEB-INF/views/hello.jsp";
    }

    @RequestMapping("/hello3")
    public ModelAndView hello3(@RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/views/hello3.jsp");
        mav.addObject("name", name);
        return mav;
    }

    // http://localhost:8080/hellospring/hello4?name=star
    @RequestMapping("/hello4")
    public String hello4(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "/WEB-INF/views/hello3.jsp";
    }

    @ResponseBody
    @RequestMapping("/hello5")
    public Object hello5(){
        return new HashMap<>();
    }

    @RequestMapping("/hello6")
    public String hello6(){
        return "redirect:/hello";
    }

    @RequestMapping("/hello7") // 이런 식으로 코드 짜지 말기.
    public void hello7(HttpServletRequest req,
                       HttpServletResponse res,
                       Writer out) throws IOException {
        String name = req.getParameter("name");
        System.out.println("name: " + name);
//        res.getWriter().print("<h1>hello World</h1>");
        out.write("<h1>hello World</h1>");
    }
}
