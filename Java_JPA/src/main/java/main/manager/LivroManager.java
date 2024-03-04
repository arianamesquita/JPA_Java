package main.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import main.dao.Livro;

public class LivroManager {
    
    protected SessionFactory sessionFactory;

    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        session.close();
    }

    public void create(Livro livro) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(livro);
        session.getTransaction().commit();
        session.close();

    }

    public void read(int bookId) {

        Session session = sessionFactory.openSession();

        Livro livro = session.get(Livro.class, bookId);
        
        System.out.println("Title: " + livro.getTitulo());
        System.out.println("Autores: " + livro.getAutores());
        System.out.println("Ano Publicação : " + livro.getAnoPub());

        session.close();    

    }

    public void readList() {

        Session session = sessionFactory.openSession();
        @SuppressWarnings("rawtypes")
        Query q = session.createNamedQuery("Livro.findByCodigo");
        q.setParameter(1, "9");

        @SuppressWarnings("unchecked")
        List<Livro> livros = q.getResultList();
        for (Livro livro : livros) {
            System.out.println(livro); 
        }
        session.getTransaction().commit();
        session.close();

    }

    public void update(Livro livro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(livro); 
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Livro livro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(livro); 
        session.getTransaction().commit();
        session.close();
    }

    public void exit() {
        sessionFactory.close();
    }
    
}
