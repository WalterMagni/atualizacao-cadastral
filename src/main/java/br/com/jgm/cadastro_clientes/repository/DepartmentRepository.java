package br.com.jgm.cadastro_clientes.repository;

import br.com.jgm.cadastro_clientes.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
