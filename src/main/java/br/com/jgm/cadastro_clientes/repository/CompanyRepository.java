package br.com.jgm.cadastro_clientes.repository;

import br.com.jgm.cadastro_clientes.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
