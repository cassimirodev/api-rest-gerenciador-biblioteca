package com.backend.gerenciadorDeBiblioteca.repository;

import com.backend.gerenciadorDeBiblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByDataEmprestimoBetween(LocalDate inicio, LocalDate fim);
    List<Emprestimo> findByDataDevolucaoBefore(LocalDate data);
    List<Emprestimo> findByUsuarioIdUsuario(UUID idUsuario);
    List<Emprestimo> findByLivroIdLivro(Long idLivro);
}