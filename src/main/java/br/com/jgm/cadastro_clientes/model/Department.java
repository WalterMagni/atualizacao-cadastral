package br.com.jgm.cadastro_clientes.model;

import br.com.jgm.cadastro_clientes.model.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employees")
public class Department extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @ManyToMany(mappedBy = "departments")
    private List<Employee> employees;
}