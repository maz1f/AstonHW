package main.second.dao.impl;

import main.second.dao.AbstractDao;
import main.second.dao.EmployeeDao;
import main.second.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {
    @Override
    public Optional<Employee> getEmployeeById(long id) {
        initSession();

        Employee employee = session.createQuery(
                "from Employee e join fetch e.jobTitle join fetch e.store where e.id = :id",
                        Employee.class
                ).setParameter("id", id)
                .getSingleResult();

        closeSession();
        return Optional.ofNullable(employee);
    }

    @Override
    public Optional<List<Employee>> getAllEmployees() {
        initSession();

        List<Employee> employees = session.createQuery(
                "from Employee e join fetch e.jobTitle join fetch e.store",
                Employee.class
        ).list();

        closeSession();
        return Optional.ofNullable(employees);
    }

    @Override
    public Optional<Employee> saveEmployee(Employee employee) {
        initSession();

        Employee savedEmp = session.merge(employee);

        closeSession();
        return Optional.ofNullable(savedEmp);
    }

    @Override
    public Optional<Employee> updateEmployee(Employee employee) {
        initSession();

        int res = session.createMutationQuery("update Employee e set e.name = :name where e.id = :id")
                .setParameter("name", employee.getName())
                .setParameter("id", employee.getId()).executeUpdate();

        Employee updatedEmployee = session.find(Employee.class, employee.getId());

        closeSession();
        return Optional.ofNullable(updatedEmployee);
    }

    @Override
    public Optional<Employee> deleteEmployee(long id) {
        initSession();

        Employee employee = session.find(Employee.class, id);

        session.remove(employee);

        closeSession();
        return Optional.ofNullable(employee);
    }
}
