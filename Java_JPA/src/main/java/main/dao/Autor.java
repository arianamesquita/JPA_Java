package main.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "autor")
@NamedQuery(name = "Autor.findAll", query = "FROM Autor b")
@NamedQuery(name = "Autor.findByCodigo", query = "SELECT b FROM Autor b WHERE b.id = ?1")
@NamedQuery(name = "Autor.findByTitulo", query = "SELECT b FROM Autor b WHERE b.titulo = ?1")
@NamedQuery(name = "Autor.findByPreco", query = "SELECT b FROM Autor b WHERE b.autor = ?1")
public class Autor implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;

    @Basic(optional = false)
    @ManyToMany(mappedBy = "autores")
    private List<Livro> livro;

    public Autor ( ) {  }

    public Autor (int id)  { this.id = id; }

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Autor(int id, String nome, List<Livro> livro) {
        this.id = id;
        this.nome = nome;
        this.livro = livro;
    }

    public int getId() {  return this.id;  }

    public void setId(int id) {  this.id = id;   }

    public String getNome() {  return this.nome;  }

    public void setNome(String nome) {  this.nome = nome;   }

    public List<Livro> getLivro() {  return this.livro;   }

    public void setLivro(List<Livro> livro) {   this.livro = livro;    }

    @Override
    public int hashCode() {  return Objects.hash(id, nome);   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id &&
                Objects.equals(nome, autor.nome);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", livro='" + getLivro() + "'" +
            "}";
    }

}
