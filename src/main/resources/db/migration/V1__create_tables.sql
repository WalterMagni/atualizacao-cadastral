CREATE TABLE company (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    internal_code BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    fantasy_name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20)
);

CREATE TABLE department (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    company_id BIGINT,
    CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES company(id)
);

