package main.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import main.dao.Autor;

public class AutorManager {

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

    public void read() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        // Supondo que a classe Livro esteja mapeada como uma entidade do Hibernate
        List<Autor> autores = session.createQuery("FROM Autor", Autor.class).getResultList();
        for (Autor autor : autores) {
            System.out.println(autor); // Aqui você pode fazer o que quiser com os livros lidos
        }
        session.getTransaction().commit();
        session.close();
    }

    public void update(Autor autor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(autor); // Supondo que o livro seja uma instância gerenciada pelo Hibernate
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Autor autor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(autor); // Supondo que o livro seja uma instância gerenciada pelo Hibernate
        session.getTransaction().commit();
        session.close();
    }

    public void exit() {
        sessionFactory.close();
    }
    
}
