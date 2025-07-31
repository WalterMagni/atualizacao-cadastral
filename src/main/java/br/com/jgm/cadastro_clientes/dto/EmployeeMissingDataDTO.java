package br.com.jgm.cadastro_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMissingDataDTO {
    private String name;
    private String phone;
    private String email;
    private boolean isActive;
}
