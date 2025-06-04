package com.backend.gerenciadorDeBiblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Autor {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID autor_id;

    @NotNull
    @Size(max = 60)
    private String nome;

    @NotNull
    @Size(max = 30)
    private String nacionalidade;

    @NotNull
    private LocalDate data_nascimento;

    @NotNull
    @Size(max = 500)
    private String biografia;


    public Autor(String nome, String nacionalidade, LocalDate data_nascimento, String biografia) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.data_nascimento = data_nascimento;
        this.biografia = biografia;
    }
}