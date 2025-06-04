package com.backend.gerenciadorDeBiblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id_emprestimo;

    @NotNull
    private LocalDate data_emprestimo;

    @NotNull
    private LocalDate data_devolucao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro")
    private Livro livro;

    public Emprestimo(LocalDate data_emprestimo, LocalDate data_devolucao, Usuario usuario, Livro livro) {
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
        this.usuario = usuario;
        this.livro = livro;
    }
}