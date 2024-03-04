package main.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import main.dao.Editora;

public class EditoraManager {

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
        List<Editora> editoras = session.createQuery("FROM Editora", Editora.class).getResultList();
        for (Editora editora : editoras) {
            System.out.println(editora); // Aqui você pode fazer o que quiser com os livros lidos
        }
        session.getTransaction().commit();
        session.close();
    }

    public void update(Editora editora) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(editora); // Supondo que o livro seja uma instância gerenciada pelo Hibernate
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Editora editora) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(editora); // Supondo que o livro seja uma instância gerenciada pelo Hibernate
        session.getTransaction().commit();
        session.close();
    }

    public void exit() {
        sessionFactory.close();
    }
    
}
