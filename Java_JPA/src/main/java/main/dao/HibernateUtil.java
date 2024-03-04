package main.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Crie o registro de serviço
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configuração a partir do arquivo hibernate.cfg.xml
                .build();

            // Construa a SessionFactory a partir do registro de serviço
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            // Se ocorrer um erro, imprima e lance uma exceção
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Erro ao criar a SessionFactory: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Feche a SessionFactory, liberando todos os recursos
        getSessionFactory().close();
    }

    private HibernateUtil() {    }
    
}
