package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}