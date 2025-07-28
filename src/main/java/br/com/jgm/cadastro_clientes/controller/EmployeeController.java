package br.com.jgm.cadastro_clientes.controller;

import br.com.jgm.cadastro_clientes.dto.EmployeeDTO;
import br.com.jgm.cadastro_clientes.model.Employee;
import br.com.jgm.cadastro_clientes.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) {
        EmployeeDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO dto) {
        EmployeeDTO saved = service.save(dto);
        return ResponseEntity.status(201).body(saved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody @Valid EmployeeDTO dto) {
        EmployeeDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
