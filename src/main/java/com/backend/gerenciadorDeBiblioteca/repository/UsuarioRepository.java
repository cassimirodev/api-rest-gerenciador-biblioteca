package com.backend.gerenciadorDeBiblioteca.repository;

import com.backend.gerenciadorDeBiblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByCpf(String cpf);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByEnderecoContainingIgnoreCase(String endereco);
}