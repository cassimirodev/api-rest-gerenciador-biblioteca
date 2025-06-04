package com.backend.gerenciadorDeBiblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import jakarta.validation.constraints.Size;
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
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id_livro;

    @NotNull
    @Size(max = 100)
    private String titulo;

    @NotNull
    @Size(max = 50)
    private String editora;

    @NotNull
    @Size(max = 30)
    private String genero;

    @NotNull
    private LocalDate data_publicacao;

    @NotNull
    private int quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro(String titulo, String editora, String genero, LocalDate data_publicacao, int quantidade) {
        this.titulo = titulo;
        this.editora = editora;
        this.genero = genero;
        this.data_publicacao = data_publicacao;
        this.quantidade = quantidade;
    }




}