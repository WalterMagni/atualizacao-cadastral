package br.com.jgm.cadastro_clientes.service;

import br.com.jgm.cadastro_clientes.model.Employee;
import br.com.jgm.cadastro_clientes.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmproyeeService {

    private final EmployeeRepository employeeRepository;

    public EmproyeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
