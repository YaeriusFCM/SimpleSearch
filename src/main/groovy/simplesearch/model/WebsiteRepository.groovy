package simplesearch.model

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface WebsiteRepository extends MongoRepository<Website, String> {
    
    Website findById(String id)
    List<Website> findByTitle(String pattern)
    List<Website> findByTags(String tag)
    @Query("{tags: { \$regex: '?0'}}")
    List<Website> findByTagsLike(String pattern)

}

