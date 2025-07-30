package br.com.jgm.cadastro_clientes.mapper;

import br.com.jgm.cadastro_clientes.dto.EmployeeDTO;
import br.com.jgm.cadastro_clientes.model.Company;
import br.com.jgm.cadastro_clientes.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee entity) {
        if (entity == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCpf(entity.getCpf());
        dto.setIsActive(entity.getIsActive());
        dto.setCompanyId(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setDepartments(entity.getDepartments());
        dto.setHierarchyLevel(entity.getHierarchyLevel());
        return dto;
    }

    public Employee toEntity(EmployeeDTO dto, Company company) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setCpf(dto.getCpf());
        employee.setIsActive(dto.getIsActive());
        employee.setDepartments(dto.getDepartments());
        employee.setHierarchyLevel(dto.getHierarchyLevel());
        employee.setCompany(company);
        return employee;
    }
}
