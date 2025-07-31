package br.com.jgm.cadastro_clientes.mapper;

import br.com.jgm.cadastro_clientes.dto.CompanyDTO;
import br.com.jgm.cadastro_clientes.model.Company;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO toDTO(Company company);
    Company toEntity(CompanyDTO dto);
}
