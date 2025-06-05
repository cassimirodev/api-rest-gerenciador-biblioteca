package com.backend.gerenciadorDeBiblioteca.controller;

import com.backend.gerenciadorDeBiblioteca.model.Livro;
import com.backend.gerenciadorDeBiblioteca.service.LivroService;
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
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
        return livroService.buscarPorId(id)
                .map(livroExistente -> {
                    livro.setIdLivro(id);
                    return ResponseEntity.ok(livroService.salvar(livro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (livroService.buscarPorId(id).isPresent()) {
            livroService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<Livro>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(livroService.buscarPorTitulo(titulo));
    }

    @GetMapping("/editora")
    public ResponseEntity<List<Livro>> buscarPorEditora(@RequestParam String editora) {
        return ResponseEntity.ok(livroService.buscarPorEditora(editora));
    }

    @GetMapping("/genero")
    public ResponseEntity<List<Livro>> buscarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(livroService.buscarPorGenero(genero));
    }

    @GetMapping("/publicados-apos")
    public ResponseEntity<List<Livro>> buscarPorDataPublicacaoApos(@RequestParam LocalDate data) {
        return ResponseEntity.ok(livroService.buscarPorDataPublicacaoApos(data));
    }

    @GetMapping("/autor/{idAutor}")
    public ResponseEntity<List<Livro>> buscarPorAutor(@PathVariable Long idAutor) {
        return ResponseEntity.ok(livroService.buscarPorAutor(idAutor));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Livro>> buscarPorQuantidadeDisponivel(@RequestParam(defaultValue = "0") int quantidade) {
        return ResponseEntity.ok(livroService.buscarPorQuantidadeDisponivel(quantidade));
    }
    @PostMapping("/batch")
    public ResponseEntity<List<Livro>> criarVarios(@Valid @RequestBody List<Livro> livros) {
        List<Livro> livrosSalvos = livros.stream()
                .map(livroService::salvar)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(livrosSalvos);
    }

    @PutMapping("/batch")
    public ResponseEntity<List<Livro>> atualizarVarios(@Valid @RequestBody List<Livro> livros) {
        List<Livro> livrosSalvos = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getIdLivro() == null ||
                    !livroService.buscarPorId(livro.getIdLivro()).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            livrosSalvos.add(livroService.salvar(livro));
        }
        return ResponseEntity.ok(livrosSalvos);
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            if (!livroService.buscarPorId(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
        }
        ids.forEach(livroService::excluir);
        return ResponseEntity.noContent().build();
    }
}