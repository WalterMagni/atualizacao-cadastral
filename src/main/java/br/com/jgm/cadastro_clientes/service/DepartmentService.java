package br.com.jgm.cadastro_clientes.service;

import br.com.jgm.cadastro_clientes.model.Department;
import br.com.jgm.cadastro_clientes.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> findAll() { return repository.findAll(); }

    public Optional<Department> findById(Long id) { return repository.findById(id); }

    public Department save(Department department) { return repository.save(department); }

    public void delete(Long id) { repository.deleteById(id); }

}
