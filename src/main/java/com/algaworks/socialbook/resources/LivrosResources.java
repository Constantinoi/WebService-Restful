/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbook.resources;

import com.algaworks.socialbook.domain.Livro;
import com.algaworks.socialbook.repository.LivrosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stirr
 */
@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosRepository livrosRepository;

    @RequestMapping(method = RequestMethod.GET)//obtem o recurso
    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)//criação de novos recursos
    public void salvar(@RequestBody Livro livro) {
        livrosRepository.save(livro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)//obtem o recurso de uma id especifica
    public Optional<Livro> buscar(@PathVariable("id") Long id) {
        return livrosRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)//deleta um recurso atraves do id
    public void deletar(@PathVariable("id") Long id) {
        livrosRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)//criação de novos recursos
    public void atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
        livro.setId(id);
        livrosRepository.save(livro);
    }
}
