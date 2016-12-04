package bookmarks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bookmark {


    @Id
    @GeneratedValue
    private Long id;

    public String uri;
    
    public String description;
    
    Bookmark() { // jpa only
    }

    public Bookmark(String uri, String description) {
        this.uri = uri;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }
}