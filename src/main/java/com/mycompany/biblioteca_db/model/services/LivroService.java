/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_db.model.services;

import com.mycompany.biblioteca_db.exceptions.CadastroException;
import com.mycompany.biblioteca_db.model.dao.AutorDao;
import com.mycompany.biblioteca_db.model.dao.LivroDao;
import com.mycompany.biblioteca_db.model.domain.Livro;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wendell
 */
public class LivroService {

    private final LivroDao livroDao = new LivroDao();

    public void salvarLivro(Livro livro) throws CadastroException {
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new CadastroException("O livro não possui um título!");
        }

        // validar se a livro esta com cidade e uf preenchido:
//        Livro livroCidade = livroDao.buscarLivroPorCodigoLivro(livro.getCidade());
//        Livro livroUf = livroDao.buscarLivroPorCodigoLivro(livro.getUf());
        if (livro.getGenero() == null || livro.getGenero().isBlank()) {
            throw new CadastroException("O livro não possui gênero!");
        }

        livroDao.inserirLivro(livro);
    }

    public List<Livro> buscarLivro() {
        return livroDao.listarTodosLivros();
    }

    public static void main(String[] args) {
        LivroService livroService = new LivroService();

        var AutorDao = new AutorDao().buscarAutorPorId(1l);

        Livro livro = new Livro(null, "Percy Jackson", "Ação", AutorDao);

        try {
            livroService.salvarLivro(livro);
        } catch (CadastroException ex) {
            Logger.getLogger(LivroService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}