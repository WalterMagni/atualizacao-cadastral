package br.com.jgm.cadastro_clientes.repository;

import br.com.jgm.cadastro_clientes.dto.CompanyContactIssueDTO;
import br.com.jgm.cadastro_clientes.dto.EmployeeMissingDataDTO;
import br.com.jgm.cadastro_clientes.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("""
    SELECT new br.com.jgm.cadastro_clientes.dto.EmployeeMissingDataDTO(
        e.name,
        e.phone,
        e.email,
        e.isActive
    )
    FROM Employee e
    WHERE e.company.internalCode = :code
    AND (
        e.phone IS NULL OR e.phone = ''
        OR e.email IS NULL OR e.email = ''
        OR e.isActive = false
    )
""")
    List<EmployeeMissingDataDTO> findEmployeesWithMissingDataByCompanyCode(@Param("code") String code);



}
