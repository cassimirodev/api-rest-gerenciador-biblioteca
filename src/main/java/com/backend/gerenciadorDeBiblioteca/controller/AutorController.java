package com.backend.gerenciadorDeBiblioteca.controller;

import com.backend.gerenciadorDeBiblioteca.model.Autor;
import com.backend.gerenciadorDeBiblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarTodos() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Autor> criar(@Valid @RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvar(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @Valid @RequestBody Autor autor) {
        return autorService.buscarPorId(id)
                .map(autorExistente -> {
                    autor.setIdAutor(id);
                    return ResponseEntity.ok(autorService.salvar(autor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (autorService.buscarPorId(id).isPresent()) {
            autorService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Autor>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(autorService.buscarPorNome(nome));
    }

    @GetMapping("/nacionalidade")
    public ResponseEntity<List<Autor>> buscarPorNacionalidade(@RequestParam String nacionalidade) {
        return ResponseEntity.ok(autorService.buscarPorNacionalidade(nacionalidade));
    }

    @GetMapping("/nascidos-antes")
    public ResponseEntity<List<Autor>> buscarPorDataNascimentoAntes(@RequestParam LocalDate data) {
        return ResponseEntity.ok(autorService.buscarPorDataNascimentoAntes(data));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Autor>> criarVarios(@Valid @RequestBody List<Autor> autores) {
        List<Autor> autoresSalvos = autores.stream()
                .map(autorService::salvar)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(autoresSalvos);
    }

    @PutMapping("/batch")
    public ResponseEntity<List<Autor>> atualizarVarios(@Valid @RequestBody List<Autor> autores) {
        List<Autor> autoresSalvos = new ArrayList<>();

        for (Autor autor : autores) {
            if (autor.getIdAutor() == null ||
                    !autorService.buscarPorId(autor.getIdAutor()).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            autoresSalvos.add(autorService.salvar(autor));
        }
        return ResponseEntity.ok(autoresSalvos);
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            if (!autorService.buscarPorId(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
        }
        ids.forEach(autorService::excluir);
        return ResponseEntity.noContent().build();
    }
}