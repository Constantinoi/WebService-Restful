/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbook.services;

import com.algaworks.socialbook.domain.Livro;
import com.algaworks.socialbook.repository.LivrosRepository;
import com.algaworks.socialbook.services.exceptions.LivroNaoEncontradoException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author constantino
 */
@Service
public class LivrosService {

    @Autowired
    private LivrosRepository livrosRepository;//camada que faz acesso ao repositorio

    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    public Livro buscar(Long id) {
        Livro livro = livrosRepository.findById(id).orElse(null);

        if (livro == null) {
            throw new LivroNaoEncontradoException("O livro nao pode ser encontrado.");
        }
        return livro;
    }

    public Livro salvar(Livro livro) {
        livro.setId(null);
        return livro = livrosRepository.save(livro);

    }

    public void deletar(Long id) {
        try {
            livrosRepository.deleteById(id);
        } catch (LivroNaoEncontradoException e) {
            throw new LivroNaoEncontradoException("O livro nao pode ser encontrado.");
        }

    }

    public void atualizar(Livro livro) {
        verificarExistencia(livro);
        livrosRepository.save(livro);
    }

    private void verificarExistencia(Livro livro) {
        buscar(livro.getId());
    }

}
