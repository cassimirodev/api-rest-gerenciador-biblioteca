package com.backend.gerenciadorDeBiblioteca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLivro;

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
    private LocalDate dataPublicacao;

    @NotNull
    private int quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAutor")
    @JsonBackReference
    private Autor autor;

}