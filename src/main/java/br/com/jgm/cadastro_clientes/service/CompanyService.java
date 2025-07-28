package br.com.jgm.cadastro_clientes.service;

import br.com.jgm.cadastro_clientes.dto.CompanyDTO;
import br.com.jgm.cadastro_clientes.mapper.CompanyMapper;
import br.com.jgm.cadastro_clientes.model.Company;
import br.com.jgm.cadastro_clientes.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    public List<CompanyDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public CompanyDTO findById(Long id) {
        Company company = resolveCompany(id);
        return mapper.toDTO(company);
    }

    public CompanyDTO findByInternalCode(Long internalCode) {
        Company company = repository.findByInternalCode(internalCode)
                .orElseThrow(() -> new EntityNotFoundException("Empresa com código interno " + internalCode + " não encontrada"));
        return mapper.toDTO(company);
    }

    public CompanyDTO save(CompanyDTO dto) {
        Company company = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(company));
    }

    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company existing = resolveCompany(id);

        existing.setName(dto.getName());
        existing.setFantasyName(dto.getFantasyName());
        existing.setCnpj(dto.getCnpj());
        existing.setInternalCode(dto.getInternalCode());

        Company saved = repository.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Empresa com ID " + id + " não encontrada");
        }
        repository.deleteById(id);
    }

    private Company resolveCompany(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + id + " não encontrada"));
    }


}
