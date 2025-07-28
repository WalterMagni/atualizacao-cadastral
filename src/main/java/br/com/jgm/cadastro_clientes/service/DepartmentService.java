package br.com.jgm.cadastro_clientes.service;

import br.com.jgm.cadastro_clientes.dto.DepartmentDTO;
import br.com.jgm.cadastro_clientes.mapper.DepartmentMapper;
import br.com.jgm.cadastro_clientes.model.Department;
import br.com.jgm.cadastro_clientes.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public List<DepartmentDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO findById(Long id) {
        Department department = resolveDepartment(id);
        return mapper.toDTO(department);
    }

    public DepartmentDTO save(DepartmentDTO dto) {
        Department department = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(department));
    }

    public DepartmentDTO update(Long id, DepartmentDTO dto) {
        Department existing = resolveDepartment(id);
        existing.setDescription(dto.getDescription());
        Department saved = repository.save(existing);
        return mapper.toDTO(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Departamento com ID " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    private Department resolveDepartment(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Departamento com ID " + id + " não encontrado"));
    }
}
