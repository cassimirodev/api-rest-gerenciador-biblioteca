package com.backend.gerenciadorDeBiblioteca.service;

import com.backend.gerenciadorDeBiblioteca.model.Livro;
import com.backend.gerenciadorDeBiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorEditora(String editora) {
        return livroRepository.findByEditoraContainingIgnoreCase(editora);
    }

    public List<Livro> buscarPorGenero(String genero) {
        return livroRepository.findByGeneroContainingIgnoreCase(genero);
    }

    public List<Livro> buscarPorDataPublicacaoApos(LocalDate data) {
        return livroRepository.findByDataPublicacaoAfter(data);
    }

    public List<Livro> buscarPorAutor(Long idAutor) {
        return livroRepository.findByAutorIdAutor(idAutor);
    }

    public List<Livro> buscarPorQuantidadeDisponivel(int quantidade) {
        return livroRepository.findByQuantidadeGreaterThan(quantidade);
    }
}