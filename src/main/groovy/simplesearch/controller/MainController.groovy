package simplesearch.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController {
    
    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", "index")
        return 'main'
    }
    
    @GetMapping("/test")
    String testPage(Model model) {
        model.addAttribute("content", "testPage")
        return 'main'
    }
    
    @GetMapping("/search")
    String search(Model model, @RequestParam String query) {
        model.addAttribute("query", query ? query : "nothing")
        model.addAttribute("content", "search")
        return 'main'
    }
}

