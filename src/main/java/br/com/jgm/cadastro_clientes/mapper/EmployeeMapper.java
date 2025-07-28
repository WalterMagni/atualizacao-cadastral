package br.com.jgm.cadastro_clientes.mapper;

import br.com.jgm.cadastro_clientes.dto.EmployeeDTO;
import br.com.jgm.cadastro_clientes.model.Department;
import br.com.jgm.cadastro_clientes.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "departments", target = "departmentIds")
    EmployeeDTO toDTO(Employee entity);

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "departmentIds", target = "departments")
    Employee toEntity(EmployeeDTO dto);

    default List<Long> mapDepartmentsToIds(List<Department> departments) {
        if (departments == null) return null;
        return departments.stream().map(Department::getId).collect(Collectors.toList());
    }

    default List<Department> mapIdsToDepartments(List<Long> ids) {
        // Essa lógica será sobreposta no Service com o repository, para resolver dependências
        return null;
    }

}
