package br.com.jgm.cadastro_clientes.config;

import br.com.jgm.cadastro_clientes.model.Company;
import br.com.jgm.cadastro_clientes.model.Employee;
import br.com.jgm.cadastro_clientes.model.enums.DepartmentType;
import br.com.jgm.cadastro_clientes.model.enums.HierarchyLevel;
import br.com.jgm.cadastro_clientes.repository.CompanyRepository;
import br.com.jgm.cadastro_clientes.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final Map<String, Company> companyCache = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/relatorio.xlsx");
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // pula cabeçalho

            // Colunas da empresa: A-D (0-3)
            String codigoInterno = getCellValueAsString(row.getCell(0));
            String razaoSocial = getCellValueAsString(row.getCell(1));
            String cnpj = getCellValueAsString(row.getCell(2));
            String nomeFantasia = getCellValueAsString(row.getCell(3));

            Company company = companyCache.computeIfAbsent(codigoInterno, code -> {
                return companyRepository.findByInternalCode(Long.parseLong(code))
                        .orElseGet(() -> {
                            Company newCompany = new Company();
                            newCompany.setInternalCode(Long.parseLong(code));
                            newCompany.setName(razaoSocial);
                            newCompany.setCnpj(cnpj);
                            newCompany.setFantasyName(nomeFantasia);
                            return companyRepository.save(newCompany);
                        });
            });

            // Colunas do colaborador: E em diane
            String nome = getCellValueAsString(row.getCell(4));
            String cpf = getCellValueAsString(row.getCell(5));
            String email = getCellValueAsString(row.getCell(6));

            String departamentosStr = getCellValueAsString(row.getCell(7));
            Set<DepartmentType> departamentos = new HashSet<>();
            if (departamentosStr.equalsIgnoreCase("Todos")) {
                departamentos = EnumSet.allOf(DepartmentType.class);
            } else {
                for (String dep : departamentosStr.split(",")) {
                    departamentos.add(DepartmentType.valueOf(dep.trim().toUpperCase()));
                }
            }

            String telefone = getCellValueAsString(row.getCell(8));
            Boolean isActive = Boolean.parseBoolean(getCellValueAsString(row.getCell(9)));

            Employee employee = new Employee();
            employee.setName(nome);
            employee.setEmail(email);
            employee.setPhone(telefone);
            employee.setCpf(cpf);
            employee.setIsActive(isActive);
            employee.setCompany(company);
            employee.setDepartments(departamentos);
            employee.setHierarchyLevel(HierarchyLevel.DEFAULT); // Ajuste conforme precisar



            employeeRepository.save(employee);
        }

        workbook.close();
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString();
                } else {
                    yield String.valueOf((long) cell.getNumericCellValue());
                }
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case BLANK, _NONE, ERROR -> "";
        };
    }
}
