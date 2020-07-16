package simplesearch.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

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
}

