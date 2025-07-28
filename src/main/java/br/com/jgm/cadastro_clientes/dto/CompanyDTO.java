package br.com.jgm.cadastro_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Long id;
    private Long internalCode;
    private String name;
    private String fantasyName;
    private String cnpj;
}
