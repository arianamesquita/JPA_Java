package main.controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.dao.Livro;

public class LivroJPAController implements Serializable{

    private transient EntityManagerFactory emf = null ;

    public LivroJPAController (EntityManagerFactory emf) {  this.emf = emf;  }

    public EntityManager getEntityManager() {  return emf.createEntityManager();  }

    public void create (Livro livro) {
        EntityManager em = null;

        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } finally {
            if(em != null) {  em.close();  }
        }
    }

    public void read(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Livro livro = em.find(Livro.class, id);
            System.out.println(livro);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void update(Livro livro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(livro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void delete(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livro livro = em.find(Livro.class, id);
            if (livro != null) {
                em.remove(livro);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    
}
