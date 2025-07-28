package br.com.jgm.cadastro_clientes.model;

import br.com.jgm.cadastro_clientes.model.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employees")
public class Company extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long internalCode;

    @NotNull
    private String name;

    @NotNull
    private String fantasyName;

    @CNPJ(message = "CNPJ Inválido")
    @Column(length = 20)
    private String cnpj;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;
}



