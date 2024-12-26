package emaillist03.controller;

import emaillist03.repository.EmaillistRepository;
import emaillist03.vo.EmaillistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmaillistController {

    //    @Autowired
    private EmaillistRepository emaillistRepository;

    public EmaillistController(EmaillistRepository emaillistRepository) {
        this.emaillistRepository = emaillistRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<EmaillistVo> list = emaillistRepository.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    // form
    @RequestMapping("/form")
    public String form() {
        return "form";
    }

    // add
    @RequestMapping("/add")
    public String add(EmaillistVo vo) {

        System.out.println(vo);

        emaillistRepository.insert(vo);
        return "redirect:/";
    }
}
