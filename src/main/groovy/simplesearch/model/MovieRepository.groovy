package simplesearch.model

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface MovieRepository extends MongoRepository<Movie, String> {
    
    Movie findById(String id)
    List<Movie> findByTitle(String pattern)
    List<Movie> findByStaff(String staff)
    @Query(value = "{ staff: { \$regex: '?0', \$options: 'i' } }")
    List<Movie> findByStaffLike(String pattern)
    @Query(value = "{ staff: { \$regex: '?0', \$options: 'i' } }")
    Page<Movie> findByStaffPageable(String pattern, Pageable pageable)

}

