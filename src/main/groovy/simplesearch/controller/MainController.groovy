package simplesearch.controller

import java.util.stream.Collectors
import java.util.stream.IntStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import simplesearch.model.Movie
import simplesearch.model.MovieRepository

@Controller
class MainController {
    
    static int pageSize = 10
    
    @Autowired
    MovieRepository repository
    
    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("content", "index")
        return 'main'
    }
    
    @GetMapping("/info")
    String info(Model model) {
        model.addAttribute("content", "info")
        return 'main'
    }
    
    @GetMapping("/search")
    String search(Model model, @RequestParam String query, @RequestParam Optional<Integer> page) {
        
        if (query.size() > 1) {
            int currentPage = page.orElse(1)

            Page<Movie> response = repository.findByStaffPageable(query.toLowerCase(), PageRequest.of(currentPage - 1, pageSize))
            model.addAttribute("response", response)

            int totalPages = response.getTotalPages()
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList())
                model.addAttribute("pageNumbers", pageNumbers)
            }
        }
        else {
            model.addAttribute("response", Page.empty())
        }
        
        model.addAttribute("query", query)
        model.addAttribute("content", "search")
        return 'main'
    }
}

