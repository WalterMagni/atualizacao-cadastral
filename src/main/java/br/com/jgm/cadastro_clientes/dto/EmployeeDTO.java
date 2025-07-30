package br.com.jgm.cadastro_clientes.dto;

import br.com.jgm.cadastro_clientes.model.enums.DepartmentType;
import br.com.jgm.cadastro_clientes.model.enums.HierarchyLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    private String cpf;
    private Boolean isActive;
    private HierarchyLevel hierarchyLevel;
    private Long companyId;
    private Set<DepartmentType> departments;

}
