package com.backend.gerenciadorDeBiblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID idUsuario;

    @NotNull
    @Column(unique = true)
    @CPF
    private String cpf;

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    @Email (regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE) // esse regex vai validar o email, ou seja, nn vai aceitar ex: Arun@Arun.com ou Arun@Arun
    private String email;

    @NotNull
    @Size(max = 15)
    private String telefone;

    @NotNull
    @Size(max = 255)
    private String endereco;

}
