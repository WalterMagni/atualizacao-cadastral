package br.com.jgm.cadastro_clientes.mapper;

import br.com.jgm.cadastro_clientes.dto.DepartmentDTO;
import br.com.jgm.cadastro_clientes.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDTO(Department entity);
    Department toEntity(DepartmentDTO dto);
}