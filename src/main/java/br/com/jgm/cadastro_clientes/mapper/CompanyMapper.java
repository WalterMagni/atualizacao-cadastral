package br.com.jgm.cadastro_clientes.mapper;

import br.com.jgm.cadastro_clientes.dto.CompanyDTO;
import br.com.jgm.cadastro_clientes.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toDTO(Company entity);
    Company toEntity(CompanyDTO dto);
}
