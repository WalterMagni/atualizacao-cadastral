package br.com.jgm.cadastro_clientes.service;

import br.com.jgm.cadastro_clientes.dto.EmployeeDTO;
import br.com.jgm.cadastro_clientes.mapper.EmployeeMapper;
import br.com.jgm.cadastro_clientes.model.Company;
import br.com.jgm.cadastro_clientes.model.Department;
import br.com.jgm.cadastro_clientes.model.Employee;
import br.com.jgm.cadastro_clientes.repository.CompanyRepository;
import br.com.jgm.cadastro_clientes.repository.DepartmentRepository;
import br.com.jgm.cadastro_clientes.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper mapper;

    public List<EmployeeDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public EmployeeDTO findById(Long id) {
        Employee employee = resolveEmployee(id);
        return mapper.toDTO(employee);
    }

    public EmployeeDTO save(EmployeeDTO dto) {
        Employee existing = mapper.toEntity(dto);

        if(dto.getCompanyId() != null) {
            Company company = resolveCompany(dto.getCompanyId());
            existing.setCompany(company);
        }

        if (dto.getDepartmentIds() != null) {
            List<Department> departments = departmentRepository.findAllById(dto.getDepartmentIds());
            existing.setDepartments(departments);
        }

        return mapper.toDTO(repository.save(existing));
    }

    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee existing = resolveEmployee(id);
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());

        if(dto.getCompanyId() != null) {
            Company company = resolveCompany(dto.getCompanyId());
            existing.setCompany(company);
        } else {
            existing.setCompany(null);
        }

        if (dto.getDepartmentIds() != null) {
            List<Department> departments = departmentRepository.findAllById(dto.getDepartmentIds());
            existing.setDepartments(departments);
        }

        Employee saved = repository.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Colaborador com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    private Employee resolveEmployee(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Colaborador com ID " + id + " não encontrado"));
    }

    private Company resolveCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + id + " não encontrada"));
    }

}
