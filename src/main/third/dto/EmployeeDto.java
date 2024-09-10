package main.third.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import main.second.entity.Employee;
import main.second.entity.JobTitle;
import main.second.entity.Store;
import org.springframework.lang.Nullable;

@Getter @Setter
public class EmployeeDto {
    @Nullable
    private Long id;
    private String name;
    private JobTitle jobTitle;
    private StoreDto store;

    public static EmployeeDto fromEmployee(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setJobTitle(employee.getJobTitle());
        dto.setStore(StoreDto.fromEntity(employee.getStore()));
        return dto;
    }

    public static Employee toEmployee(EmployeeDto dto) {
        Employee employee = new Employee();
        if (dto.getId() != null)
            employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setJobTitle(dto.getJobTitle());
        employee.setStore(StoreDto.fromDto(dto.getStore()));
        return employee;
    }

}
