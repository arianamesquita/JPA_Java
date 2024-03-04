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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "editora")
@NamedQuery(name = "Editora.findAll", query = "FROM Editora e")
@NamedQuery(name = "Editora.findByCodigo", query = "SELECT e FROM Editora e WHERE e.id = ?1")
@NamedQuery(name = "Editora.findByNome", query = "SELECT e FROM Editora e WHERE e.nome = ?1")
public class Editora implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Editora() {  }

    public Editora(Long id) { this.id = id; }

    public Editora(Long id, String nome, List<Livro> livros) {
        this.id = id;
        this.nome = nome;
        this.livros = livros;
    }

    public Long getId() {   return this.id;   }

    public void setId(Long id) {   this.id = id;   }

    public String getNome() {  return this.nome;   }

    public void setNome(String nome) {  this.nome = nome;   }

    public List<Livro> getLivros() {  return this.livros;   }

    public void setLivros(List<Livro> livros) {  this.livros = livros;   }

    @Override
    public int hashCode() {  return Objects.hash(id, nome);   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editora editora = (Editora) o;
        return id == editora.id &&
                Objects.equals(nome, editora.nome);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", livros='" + getLivros() + "'" +
            "}";
    }
   
}
