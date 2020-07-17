package simplesearch.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import simplesearch.model.Website
import simplesearch.model.WebsiteRepository

@RestController
class AjaxController {
    
    @Autowired
    WebsiteRepository repository
    
    @GetMapping(value = "/automock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Object autoCompleteMock(@RequestParam String q) {
        def map = [query: q, suggestions: ['ala ma kota', 'baba z wozu', 'calineczka', 'dąb zupa dębowa', 'e=mc2']]
        
        return map
    }
    
    @GetMapping(value = "/autocomplete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Object autoComplete(@RequestParam String q) {
        def mtags = [:]
        def map = [query: q, suggestions : []]
        def response = repository.findByTagsLike(q.toLowerCase())
        response.each { row ->
            row.tags.each { tag ->
                if (tag.contains(q)) mtags.containsKey(tag) ? (mtags[tag] = mtags[tag] + 1) : (mtags[tag] = 1)
            }
        }
        mtags.sort { a,b -> b.value <=> a.value }
        def sliced = mtags.keySet().collate(5)
        if (sliced)
            map.suggestions = sliced[0].sort() // { a,b -> b <=> a }
        
        return map
    }
    
    @GetMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Object insertMockedData() {
        repository.save(new Website("Popular search", "google.com", 1, ['google', 'search', 'popular']))
        repository.save(new Website("Unpopular search", "bing.com", 2, ['bing', 'search', 'alternative']))
        repository.save(new Website("Java Framework website", "spring.io", 3, ['spring', 'boot', 'popular']))
        
        return [status: 'ok']
    }
}

