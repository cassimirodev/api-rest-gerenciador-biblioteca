package com.backend.gerenciadorDeBiblioteca.controller;

import com.backend.gerenciadorDeBiblioteca.model.Usuario;
import com.backend.gerenciadorDeBiblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable UUID id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.buscarPorId(id)
                .map(usuarioExistente -> {
                    usuario.setIdUsuario(id);
                    return ResponseEntity.ok(usuarioService.salvar(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        if (usuarioService.buscarPorId(id).isPresent()) {
            usuarioService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cpf")
    public ResponseEntity<Usuario> buscarPorCpf(@RequestParam String cpf) {
        return usuarioService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(usuarioService.buscarPorNome(nome));
    }

    @GetMapping("/email")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email) {
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/endereco")
    public ResponseEntity<List<Usuario>> buscarPorEndereco(@RequestParam String endereco) {
        return ResponseEntity.ok(usuarioService.buscarPorEndereco(endereco));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Usuario>> criarVarios(@Valid @RequestBody List<Usuario> usuarios) {
        List<Usuario> usuariosSalvos = usuarios.stream()
                .map(usuarioService::salvar)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosSalvos);
    }

    @PutMapping("/batch")
    public ResponseEntity<List<Usuario>> atualizarVarios(@Valid @RequestBody List<Usuario> usuarios) {
        List<Usuario> usuariosSalvos = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == null ||
                    !usuarioService.buscarPorId(usuario.getIdUsuario()).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            usuariosSalvos.add(usuarioService.salvar(usuario));
        }
        return ResponseEntity.ok(usuariosSalvos);
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<UUID> ids) {
        for (UUID id : ids) {
            if (!usuarioService.buscarPorId(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
        }
        ids.forEach(usuarioService::excluir);
        return ResponseEntity.noContent().build();
    }
}