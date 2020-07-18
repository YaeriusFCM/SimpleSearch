package simplesearch.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import simplesearch.model.Movie
import simplesearch.model.MovieRepository

@RestController
class AjaxController {
    
    @Autowired
    MovieRepository repository
    
    @GetMapping(value = "/autocomplete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Object autoComplete(@RequestParam String q) {
        def mtags = [:]
        def map = [query: q, suggestions : []]
        def response = repository.findByStaffLike(q.toLowerCase())
        response.each { row ->
            row.staff.each { actor ->
                if (actor.contains(q)) mtags.containsKey(actor) ? (mtags[actor] = mtags[actor] + 1) : (mtags[actor] = 1)
            }
        }
        mtags.sort { a,b -> b.value <=> a.value }
        def sliced = mtags.keySet().collate(5)
        if (sliced)
            map.suggestions = sliced[0].sort() // { a,b -> b <=> a }
        
        return map
    }
}

