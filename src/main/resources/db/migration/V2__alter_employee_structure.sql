-- Adiciona novo campo CPF
ALTER TABLE employee ADD COLUMN cpf VARCHAR(14);

-- Adiciona campo de hierarquia (ENUM simulada como VARCHAR)
ALTER TABLE employee ADD COLUMN hierarchy_level VARCHAR(50) NOT NULL DEFAULT 'OPERACIONAL';

-- Adiciona campo ativo
ALTER TABLE employee ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

ALTER TABLE employee ADD COLUMN created_at TIMESTAMP;
ALTER TABLE employee ADD COLUMN updated_at TIMESTAMP;

alter table company add column created_at timestamp;
alter table company add column updated_at timestamp;


-- Criação da nova tabela de departamentos (como Set de Enum)
CREATE TABLE employee_departments (
    employee_id BIGINT NOT NULL,
    department VARCHAR(50) NOT NULL,
    CONSTRAINT fk_employee_departments_employee FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);
