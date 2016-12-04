package bookmarks;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
//    Collection<Bookmark> findByAccountUsername(String username);
}
