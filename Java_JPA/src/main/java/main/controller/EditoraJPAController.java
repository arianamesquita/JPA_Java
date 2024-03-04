package main.controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import main.dao.Editora;

public class EditoraJPAController implements Serializable{

    private transient EntityManagerFactory emf = null ;

    public EditoraJPAController (EntityManagerFactory emf) {  this.emf = emf;  }

    public EntityManager getEntityManager() {  return emf.createEntityManager();  }

    public void create (Editora editora) {
        EntityManager em = null;

        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(editora);
            em.getTransaction().commit();
        } finally {
            if(em != null) {  em.close();  }
        }
    }

    public void read(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Editora editora = em.find(Editora.class, id);
            System.out.println(editora);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void update(Editora editora) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(editora);
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
            Editora editora = em.find(Editora.class, id);
            if (editora != null) {
                em.remove(editora);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
