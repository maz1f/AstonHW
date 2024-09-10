package main.third.service.impl;

import main.second.dao.EmployeeDao;
import main.second.entity.Employee;
import main.third.dto.EmployeeDto;
import main.third.exception.InvalidRequestBody;
import main.third.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Optional<EmployeeDto> getEmployeeById(long id) {
        Optional<Employee> employee = employeeDao.getEmployeeById(id);
        return employee.map(EmployeeDto::fromEmployee);
    }

    @Override
    public Optional<List<EmployeeDto>> getAllEmployees() {
        Optional<List<Employee>> allEmployees = employeeDao.getAllEmployees();
        List<EmployeeDto> res = allEmployees.stream()
                .flatMap(List::stream)
                .map(EmployeeDto::fromEmployee)
                .collect(Collectors.toList());
        return Optional.of(res);
    }

    @Override
    public Optional<EmployeeDto> addEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeDao.saveEmployee(EmployeeDto.toEmployee(employeeDto));
        return employee.map(EmployeeDto::fromEmployee);
    }

    @Override
    public Optional<EmployeeDto> updateEmployee(EmployeeDto employeeDto) throws InvalidRequestBody {

        Optional<Employee> employee = employeeDao.updateEmployee(EmployeeDto.toEmployee(employeeDto));
        if (employee.isEmpty())
            throw new InvalidRequestBody("Invalid request body");
        return employee.map(EmployeeDto::fromEmployee);

    }

    @Override
    public Optional<EmployeeDto> deleteEmployee(long id) {
        Optional<Employee> employee = employeeDao.deleteEmployee(id);
        return employee.map(EmployeeDto::fromEmployee);
    }
}
