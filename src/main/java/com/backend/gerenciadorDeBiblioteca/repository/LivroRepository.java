package com.backend.gerenciadorDeBiblioteca.repository;

import com.backend.gerenciadorDeBiblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByEditoraContainingIgnoreCase(String editora);
    List<Livro> findByGeneroContainingIgnoreCase(String genero);
    List<Livro> findByDataPublicacaoAfter(LocalDate data);
    List<Livro> findByAutorIdAutor(Long idAutor);
    List<Livro> findByQuantidadeGreaterThan(int quantidade);
}