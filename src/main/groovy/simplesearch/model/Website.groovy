package simplesearch.model

import org.springframework.data.annotation.Id

class Website {
    @Id String id
    String title
    String url
    Integer priority
    List<String> tags
    
    Website(title, url, priority, tags) {
        this.title = title
        this.url = url
        this.priority = priority
        this.tags = tags
    }
}

