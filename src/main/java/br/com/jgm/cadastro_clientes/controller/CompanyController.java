package br.com.jgm.cadastro_clientes.controller;

import br.com.jgm.cadastro_clientes.dto.CompanyDTO;
import br.com.jgm.cadastro_clientes.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @GetMapping
    public ResponseEntity<Page<CompanyDTO>> listAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getById(@PathVariable Long id) {
        CompanyDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/internal/{code}")
    public ResponseEntity<CompanyDTO> getByInternalCode(@PathVariable Long code) {
        CompanyDTO dto = service.findByInternalCode(code);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> create(@RequestBody @Valid CompanyDTO dto) {
        CompanyDTO saved = service.save(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable Long id, @RequestBody @Valid CompanyDTO dto) {
       CompanyDTO updated = service.update(id, dto);
       return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
