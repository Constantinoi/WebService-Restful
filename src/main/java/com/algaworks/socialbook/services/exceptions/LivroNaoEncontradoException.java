/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.socialbook.services.exceptions;

/**
 *
 * @author constantino
 */
public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public LivroNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
