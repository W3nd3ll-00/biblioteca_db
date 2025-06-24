/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_db.model.dao;

import com.mycompany.biblioteca_db.configurations.MySQL;
import com.mycompany.biblioteca_db.model.domain.Autor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wendell
 */
public class AutorDao {

    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO autor(nome, nacionalidade, data_nascimento) VALUES (?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.setDate(3, Date.valueOf(autor.getData_nascimento()));
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Autor> buscarTodosAutores() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var autor = construirAutorSql(rs);
                autores.add(autor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autores;
    }

    public Autor buscarAutorPorId(Long idAutor) {
        String sql = "SELECT * FROM autores WHERE id_autor = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idAutor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirAutorSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Autor buscarAutorPorNacionalidade(String nacionalidade) {
        String sql = "SELECT * FROM autores WHERE nacionalidade = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nacionalidade);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirAutorSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Autor buscarAutorPorDataNascimento(String data_nascimento) {
        String sql = "SELECT * FROM autores WHERE data_nascimento = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, data_nascimento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirAutorSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Autor construirAutorSql(ResultSet rs) throws SQLException {
        Autor autor = new Autor();
        autor.setIdAutor(rs.getLong("id_autores"));
        autor.setNome(rs.getString("nome"));
        autor.setNacionalidade(rs.getString("nacionalidade"));
        autor.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
        return autor;
    }

    public static void main(String[] args) {
//        Autor autores = new Autor(null, "Matheus Torremo", "21324623", LocalDate.now(),
//                "torremo@unincor.edu.br", "45646124897", "389102312749128903dasda");
        AutorDao autorDao = new AutorDao();
//        var autores = autoresDao.buscarTodosAutors();
//        autores.forEach(c -> System.out.println("Id: " + c.getIdAutor()
//                + " Nome: " + c.getNome()));
        var a = autorDao.buscarAutorPorId(1l);
        System.out.println("Id: " + a.getIdAutor() 
                + " Nome: " + a.getNome());
    }
}