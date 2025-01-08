package guestbook.controller;

import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;
import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GuestbookController extends HttpServlet {

    private GuestbookRepository guestbookRepository;

    public GuestbookController(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    // index
    @RequestMapping("/")
    public String index(Model model) {
/*
		ServletContext sc = request.getServletContext();
		Enumeration<String> e = sc.getAttributeNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name);
		}
		ApplicationContext ac1 = (ApplicationContext)sc.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		ApplicationContext ac2 = (ApplicationContext)sc.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.spring");
		GuestbookRepository repository = ac1.getBean(GuestbookRepository.class);
		System.out.println(repository);
		GuestbookController controller = ac2.getBean(GuestbookController.class);
		System.out.println(controller);
		System.out.println(ac1 == ac2);
		*/

        model.addAttribute("list", guestbookRepository.findAll());
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
