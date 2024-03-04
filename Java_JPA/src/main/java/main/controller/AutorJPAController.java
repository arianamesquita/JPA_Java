package main.controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.dao.Autor;

public class AutorJPAController implements Serializable {

    private transient EntityManagerFactory emf = null ;

    public AutorJPAController (EntityManagerFactory emf) {  this.emf = emf;  }

    public EntityManager getEntityManager() {  return emf.createEntityManager();  }

    public void create (Autor autor) {
        EntityManager em = null;

        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } finally {
            if(em != null) {  em.close();  }
        }
    }

    public void read(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Autor autor = em.find(Autor.class, id);
            System.out.println(autor);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void update(Autor autor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(autor);
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
            Autor autor = em.find(Autor.class, id);
            if (autor != null) {
                em.remove(autor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
