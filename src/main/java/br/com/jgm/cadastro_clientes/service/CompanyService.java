package br.com.jgm.cadastro_clientes.service;

import br.com.jgm.cadastro_clientes.model.Company;
import br.com.jgm.cadastro_clientes.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll() { return repository.findAll(); }

    public Optional<Company> findById(Long id) { return repository.findById(id); }

    public Company save(Company company) { return repository.save(company); }

    public void delete(Long id) { repository.deleteById(id); }
}
