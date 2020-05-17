package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.models.Title;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findByName(String titleName);

    @Query(value="Select name from title", nativeQuery = true)
    List<String> findAllTitleNames();




}
