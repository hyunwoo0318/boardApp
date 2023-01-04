package Lim.boardApp.repository;

import Lim.boardApp.domain.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
    public List<Text> findByTitle(String title) ;
}
