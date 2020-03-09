package org.demo.automapping.data.repositories;

import org.demo.automapping.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByFirstNameAndLastName(String fn, String ln);

    List<Employee> findAllByManagerIsNull();

    List<Employee> findAllByManagerId(long id);
}
