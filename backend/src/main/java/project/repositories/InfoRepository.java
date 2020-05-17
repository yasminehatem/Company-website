package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Info;

public interface InfoRepository extends JpaRepository<Info, Long> {
}
