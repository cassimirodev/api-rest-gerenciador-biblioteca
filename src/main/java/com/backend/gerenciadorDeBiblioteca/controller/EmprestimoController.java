package com.backend.gerenciadorDeBiblioteca.controller;

import com.backend.gerenciadorDeBiblioteca.model.Emprestimo;
import com.backend.gerenciadorDeBiblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
        return emprestimoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Emprestimo> criar(@Valid @RequestBody Emprestimo emprestimo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.salvar(emprestimo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizar(@PathVariable Long id, @Valid @RequestBody Emprestimo emprestimo) {
        return emprestimoService.buscarPorId(id)
                .map(emprestimoExistente -> {
                    emprestimo.setIdEmprestimo(id);
                    return ResponseEntity.ok(emprestimoService.salvar(emprestimo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (emprestimoService.buscarPorId(id).isPresent()) {
            emprestimoService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Emprestimo>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(emprestimoService.buscarPorPeriodo(inicio, fim));
    }

    @GetMapping("/devolucao-antes")
    public ResponseEntity<List<Emprestimo>> buscarPorDataDevolucaoAntes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(emprestimoService.buscarPorDataDevolucaoAntes(data));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Emprestimo>> buscarPorUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(emprestimoService.buscarPorUsuario(idUsuario));
    }

    @GetMapping("/livro/{idLivro}")
    public ResponseEntity<List<Emprestimo>> buscarPorLivro(@PathVariable Long idLivro) {
        return ResponseEntity.ok(emprestimoService.buscarPorLivro(idLivro));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Emprestimo>> criarVarios(@Valid @RequestBody List<Emprestimo> emprestimos) {
        List<Emprestimo> emprestimosSalvos = emprestimos.stream()
                .map(emprestimoService::salvar)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimosSalvos);
    }

    @PutMapping("/batch")
    public ResponseEntity<List<Emprestimo>> atualizarVarios(@Valid @RequestBody List<Emprestimo> emprestimos) {
        List<Emprestimo> emprestimosSalvos = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getIdEmprestimo() == null ||
                    !emprestimoService.buscarPorId(emprestimo.getIdEmprestimo()).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            emprestimosSalvos.add(emprestimoService.salvar(emprestimo));
        }
        return ResponseEntity.ok(emprestimosSalvos);
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            if (!emprestimoService.buscarPorId(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
        }
        ids.forEach(emprestimoService::excluir);
        return ResponseEntity.noContent().build();
    }
}