package guestbook.controller;

import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;
import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GuestBookController extends HttpServlet {

    private GuestbookRepository guestbookRepository;

    public GuestBookController(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    // index
    @RequestMapping("/")
    public String index(Model model) {

        List<GuestbookVo> list = guestbookRepository.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    // add
    @RequestMapping("/add")
    public String add(GuestbookVo vo) {
        guestbookRepository.insert(vo);
        return "redirect:/";
    }

    // delete(GET)
    @GetMapping("/delete/{id}")
    public String deleteform(@PathVariable("id") Long id) {
        return "delete";
    }

    // delete(POST)
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, @RequestParam("password") String password) {
        guestbookRepository.deleteByIdAndPassword(id, password);
        return "redirect:/";
    }
}
