package main.third.service;

import main.third.dto.EmployeeDto;
import main.third.exception.InvalidRequestBody;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
        Optional<EmployeeDto> getEmployeeById(long id);
        Optional<List<EmployeeDto>> getAllEmployees();
        Optional<EmployeeDto> addEmployee(EmployeeDto employeeDto);
        Optional<EmployeeDto> updateEmployee(EmployeeDto employeeDto) throws InvalidRequestBody;
        Optional<EmployeeDto> deleteEmployee(long id);
}
