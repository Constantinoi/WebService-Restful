/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbook.resources;

import com.algaworks.socialbook.domain.Livro;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author stirr
 */
@RestController
public class LivrosResources {

    @RequestMapping(value = "/livros", method = RequestMethod.GET)
    public List<Livro> listar() {
        
        Livro l1 = new Livro("Rest aplicado");
        Livro l2 = new Livro("Git passo a passo");
        
        Livro[] livros = {l1, l2};
        
        return Arrays.asList(livros);
    }
}
