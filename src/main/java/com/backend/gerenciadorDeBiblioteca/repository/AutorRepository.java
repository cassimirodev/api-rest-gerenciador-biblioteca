package com.backend.gerenciadorDeBiblioteca.repository;

import com.backend.gerenciadorDeBiblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNomeContainingIgnoreCase(String nome);
    List<Autor> findByNacionalidadeContainingIgnoreCase(String nacionalidade);
    List<Autor> findByDataNascimentoBefore(LocalDate data);
}