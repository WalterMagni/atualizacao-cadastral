package br.com.jgm.cadastro_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long companyId;
    private List<Long> departmentIds;

}
