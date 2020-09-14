/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbook.resources;

import com.algaworks.socialbook.domain.Livro;
import com.algaworks.socialbook.repository.LivrosRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)//criação de novos recursos
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
        livro = livrosRepository.save(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)//obtem o recurso de uma id especifica
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Optional<Livro> livro;
        livro = livrosRepository.findById(id);
        if (id == null) {//nao funcionou
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(livro);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)//deleta um recurso atraves do id
    public ResponseEntity <Void> deletar(@PathVariable("id") Long id) {

        try {
            livrosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)//criação de novos recursos
    public void atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
        livro.setId(id);
        livrosRepository.save(livro);
    }
}
