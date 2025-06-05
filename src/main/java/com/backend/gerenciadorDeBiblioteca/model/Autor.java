package com.backend.gerenciadorDeBiblioteca.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAutor;

    @NotNull
    @Size(max = 60)
    private String nome;

    @NotNull
    @Size(max = 30)
    private String nacionalidade;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    @Size(max = 500)
    private String biografia;

    @OneToMany(mappedBy = "autor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Livro> livros = new ArrayList<>();

}