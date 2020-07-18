package simplesearch.model

import org.springframework.data.annotation.Id

class Movie {
    @Id String id
    String title
    Integer rank
    List<String> staff
    
    Movie(title, rank, staff) {
        this.title = title
        this.rank = rank
        this.staff = staff
    }
    
    String staffline() {
        def s = staff.join(", ").split(" ").collect{it.capitalize()}.join(" ")
        s = s.split("-").collect{it.capitalize()}.join("-")
        s.split("'").collect{it.capitalize()}.join("'")
    }
}

