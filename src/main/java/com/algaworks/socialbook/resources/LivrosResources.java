/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbook.resources;

import com.algaworks.socialbook.domain.Livro;
import com.algaworks.socialbook.services.LivrosService;
import com.algaworks.socialbook.services.exceptions.LivroNaoEncontradoException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LivrosService livrosService;//camada que faz acesso ao repositorio

    @RequestMapping(method = RequestMethod.GET)//obtem o recurso
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)//criação de novos recursos
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
        livro = livrosService.salvar(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)//obtem o recurso de uma id especifica
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Livro livro = null;

        livro = livrosService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(livro);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)//deleta um recurso atraves do id
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {

        livrosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)//criação de novos recursos
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
        livro.setId(id);

        livrosService.atualizar(livro);
        return ResponseEntity.noContent().build();

    }
}
