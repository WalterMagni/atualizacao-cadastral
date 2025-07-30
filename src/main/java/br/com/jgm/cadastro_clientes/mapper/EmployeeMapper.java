package br.com.jgm.cadastro_clientes.mapper;

import br.com.jgm.cadastro_clientes.dto.EmployeeDTO;
import br.com.jgm.cadastro_clientes.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "company.id", target = "companyId")
    EmployeeDTO toDTO(Employee entity);

    @Mapping(source = "companyId", target = "company.id")
    Employee toEntity(EmployeeDTO dto);

}
