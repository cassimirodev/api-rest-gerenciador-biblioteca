package com.backend.gerenciadorDeBiblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UsuarioDTO {
    private Long idAutor;
    private String nome;
    private String nacionalidade;
    private LocalDate dataNascimento;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idAutor, String nome, String nacionalidade, LocalDate dataNascimento) {
        this.idAutor = idAutor;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
    }

}