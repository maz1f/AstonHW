package main.second.dao;

import main.second.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface EmployeeDao {

    Optional<Employee> getEmployeeById(long id);
    Optional<List<Employee>> getAllEmployees();
    Optional<Employee> saveEmployee(Employee employee);
    Optional<Employee> updateEmployee(Employee employee);
    Optional<Employee> deleteEmployee(long id);

}
