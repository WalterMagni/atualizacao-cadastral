package br.com.jgm.cadastro_clientes.service;

import br.com.jgm.cadastro_clientes.dto.EmployeeDTO;
import br.com.jgm.cadastro_clientes.mapper.EmployeeMapper;
import br.com.jgm.cadastro_clientes.model.Company;
import br.com.jgm.cadastro_clientes.model.Employee;
import br.com.jgm.cadastro_clientes.repository.CompanyRepository;
import br.com.jgm.cadastro_clientes.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final CompanyRepository companyRepository;
    private final EmployeeMapper mapper;

    public Page<EmployeeDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public List<EmployeeDTO> findAllActive() {
        return repository.findAll().stream()
                .filter(Employee::getIsActive)
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO findById(Long id) {
        Employee employee = resolveEmployee(id);
        return mapper.toDTO(employee);
    }

    public EmployeeDTO save(EmployeeDTO dto) {
        Company company = resolveCompany(dto.getCompanyId());
        Employee employee = mapper.toEntity(dto, company);
        return mapper.toDTO(repository.save(employee));
    }

    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee existing = resolveEmployee(id);

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setCpf(dto.getCpf());
        existing.setIsActive(dto.getIsActive());
        existing.setDepartments(dto.getDepartments());
        existing.setHierarchyLevel(dto.getHierarchyLevel());

        if (dto.getCompanyId() != null) {
            existing.setCompany(resolveCompany(dto.getCompanyId()));
        } else {
            existing.setCompany(null);
        }

        return mapper.toDTO(repository.save(existing));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Colaborador com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    private Employee resolveEmployee(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador com ID " + id + " não encontrado"));
    }

    private Company resolveCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + id + " não encontrada"));
    }
}
