/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbook.repository;

import com.algaworks.socialbook.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author constantino
 */
public interface LivrosRepository extends JpaRepository<Livro, Long>{
    
}
