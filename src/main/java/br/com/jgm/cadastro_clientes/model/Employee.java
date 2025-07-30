package br.com.jgm.cadastro_clientes.model;

import br.com.jgm.cadastro_clientes.model.common.Auditable;
import br.com.jgm.cadastro_clientes.model.enums.DepartmentType;
import br.com.jgm.cadastro_clientes.model.enums.HierarchyLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"company", "departments"})
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Formato de telefone inválido")
    private String phone;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    @Column(length = 11, unique = true)
    private String cpf;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HierarchyLevel hierarchyLevel;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ElementCollection(targetClass = DepartmentType.class)
    @CollectionTable(name = "employee_departments", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Set<DepartmentType> departments;
}

