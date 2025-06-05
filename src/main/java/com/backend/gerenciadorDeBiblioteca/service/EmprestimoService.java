package com.backend.gerenciadorDeBiblioteca.service;

import com.backend.gerenciadorDeBiblioteca.model.Emprestimo;
import com.backend.gerenciadorDeBiblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> buscarPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    public Emprestimo salvar(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public void excluir(Long id) {
        emprestimoRepository.deleteById(id);
    }

    public List<Emprestimo> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return emprestimoRepository.findByDataEmprestimoBetween(inicio, fim);
    }

    public List<Emprestimo> buscarPorDataDevolucaoAntes(LocalDate data) {
        return emprestimoRepository.findByDataDevolucaoBefore(data);
    }

    public List<Emprestimo> buscarPorUsuario(UUID idUsuario) {
        return emprestimoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Emprestimo> buscarPorLivro(Long idLivro) {
        return emprestimoRepository.findByLivroIdLivro(idLivro);
    }
}