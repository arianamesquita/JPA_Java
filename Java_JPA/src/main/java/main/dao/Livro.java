package main.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
@NamedQuery(name = "Livro.findAll", query = "FROM Livro b")
@NamedQuery(name = "Livro.findByCodigo", query = "SELECT b FROM Livro b WHERE b.id = ?1")
@NamedQuery(name = "Livro.findByTitulo", query = "SELECT b FROM Livro b WHERE b.titulo = ?1")
@NamedQuery(name = "Livro.findByPreco", query = "SELECT b FROM Livro b WHERE b.autor = ?1")
public class Livro implements Serializable{

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int isbn;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "ano_publicacao")
    private int anoPub;

    @Basic(optional = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    @Basic(optional = false)
    @ManyToMany
    @JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    public Livro() {    }

    public Livro(int isbn, String titulo, int anoPub, Editora editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPub = anoPub;
        this.editora = editora;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(int anoPub) {
        this.anoPub = anoPub;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Autor> getAutores() {
        return this.autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override 
    public int hashCode() {  return Objects.hash(isbn, titulo, anoPub);  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return isbn == livro.isbn &&
                Objects.equals(titulo, livro.titulo);
    }

    
}