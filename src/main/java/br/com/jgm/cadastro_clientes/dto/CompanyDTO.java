package br.com.jgm.cadastro_clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Long id;
    @NotNull(message = "O Codigo Interno (JGM) é obrigatorio")
    private Long internalCode;
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "Nome fantasia é obrigatório")
    private String fantasyName;
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;
}
