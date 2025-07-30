package br.com.jgm.cadastro_clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "O campo 'name' é obrigatório")
    private String name;
    @Email(message = "Formato de email inválido")
    private String email;
    private String phone;
    private Long companyId;
    private List<Long> departmentIds;

}
