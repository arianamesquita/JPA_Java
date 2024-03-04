package main;

import java.util.ArrayList;
import java.util.List;

import main.dao.Autor;
import main.dao.Editora;
import main.dao.Livro;
import main.manager.LivroManager;

public class App2 {
    public static void main(String[] args) {

        LivroManager livroManager = new LivroManager();
        livroManager.setup();

        //livroManager.create();
        //livroManager.read();
        //livroManager.update();
        //livroManager.delete();

        //livroManager.exit();

        Livro livro = new Livro();
        livro.setTitulo("Python");
        livro.setIsbn(100100);

        Editora editora = new Editora();
        Long id = 2L;
        editora.setId(id);
        editora.setNome("SoPython");
        livro.setEditora(editora);

        Editora editora2 = new Editora();
        Long id2 = 1L;
        editora2.setId(id2);
        editora2.setNome("SoPython");

        List<Autor> autores = new ArrayList<>();
        Autor autor = new Autor();
        autor.setId(1);
        autor.setNome("Ana Java");

        autores.add(autor);

        livro.setAutores(autores);

        livroManager.create(livro);

        livroManager.read(1);

        Livro livroUpdate = new Livro();
        livroUpdate.setTitulo("Python para dummies");
        livro.setIsbn(100110);
        livro.setEditora(editora2);

        livroManager.update(livroUpdate);
        livroManager.create(livro);

        livroManager.delete(livro);


    }
    
}
