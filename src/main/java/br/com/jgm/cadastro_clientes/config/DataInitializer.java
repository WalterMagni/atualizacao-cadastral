package br.com.jgm.cadastro_clientes.config;

import br.com.jgm.cadastro_clientes.model.Company;
import br.com.jgm.cadastro_clientes.model.Employee;
import br.com.jgm.cadastro_clientes.repository.CompanyRepository;
import br.com.jgm.cadastro_clientes.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            CompanyRepository companyRepo,
            EmployeeRepository employeeRepo,
            DepartmentRepository departmentRepo
    ) {
        return args -> {

            // Departamentos
            Department d1 = new Department(null, "TI", null);
            Department d2 = new Department(null, "RH", null);
            departmentRepo.saveAll(List.of(d1, d2));

            // Empresa
            Company empresa = new Company(null, 1001L, "JGM Ltda", "JGM", "68.890.722/0001-00", null);
            companyRepo.save(empresa);

            // Colaboradores
            Employee e1 = new Employee(null, "João Silva", "joao@email.com", "11999999999", empresa, List.of(d1));
            Employee e2 = new Employee(null, "Maria Souza", "maria@email.com", "11888888888", empresa, List.of(d1, d2));
            employeeRepo.saveAll(List.of(e1, e2));

            // Associar colaboradores à empresa
            empresa.setEmployees(List.of(e1, e2));
            companyRepo.save(empresa);

            System.out.println("✅ Dados de teste inseridos com sucesso.");
        };
    }
}
