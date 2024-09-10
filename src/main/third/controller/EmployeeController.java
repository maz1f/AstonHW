package main.third.controller;


import main.third.dto.CustomResponse;
import main.third.dto.EmployeeDto;
import main.third.exception.InvalidRequestBody;
import main.third.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public CustomResponse<?> getAllEmployees() {
        Optional<List<EmployeeDto>> allEmployees = employeeService.getAllEmployees();
        return CustomResponse.builder()
                .status(200)
                .body(allEmployees.get())
                .build();
    }

    @GetMapping("/{id}")
    public CustomResponse<?> getEmployeeById(@PathVariable("id") long id) {
        Optional<EmployeeDto> employee = employeeService.getEmployeeById(id);
        boolean present = employee.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 404)
                .body(List.of(employee.get()))
                .build();
    }

    @PostMapping
    public CustomResponse<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Optional<EmployeeDto> employee = employeeService.addEmployee(employeeDto);
        boolean present = employee.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 415)
                .body(List.of(employee.get()))
                .build();
    }

    @PutMapping
    public CustomResponse<?> updateEmployee(@RequestBody EmployeeDto employeeDto) throws InvalidRequestBody {
        Optional<EmployeeDto> employee = employeeService.updateEmployee(employeeDto);
        boolean present = employee.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 415)
                .body(List.of(employee.get()))
                .build();
    }

    @DeleteMapping("/{id}")
    public CustomResponse<?> deleteEmployee(@PathVariable("id") long id) {
        Optional<EmployeeDto> employee = employeeService.deleteEmployee(id);
        boolean present = employee.isPresent();
        return CustomResponse.builder()
                .status(present ? 200 : 415)
                .body(List.of(employee.get()))
                .build();
    }
}
