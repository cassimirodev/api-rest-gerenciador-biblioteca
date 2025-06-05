package com.backend.gerenciadorDeBiblioteca.service;

import com.backend.gerenciadorDeBiblioteca.model.Autor;
import com.backend.gerenciadorDeBiblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }

    public List<Autor> buscarPorNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Autor> buscarPorNacionalidade(String nacionalidade) {
        return autorRepository.findByNacionalidadeContainingIgnoreCase(nacionalidade);
    }

    public List<Autor> buscarPorDataNascimentoAntes(LocalDate data) {
        return autorRepository.findByDataNascimentoBefore(data);
    }
}