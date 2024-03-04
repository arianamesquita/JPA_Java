package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.controller.LivroJPAController;
import main.dao.Livro;

public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("PLivro");
        EntityManager manager = objFactory.createEntityManager();

        LivroJPAController livroJPA = new LivroJPAController(objFactory);

        Livro livro1 = new Livro();
        livro1.setIsbn(1);
        livro1.setTitulo("Java 17 SE");
        livroJPA.create(livro1);
        livroJPA.read(1);
    }
}
