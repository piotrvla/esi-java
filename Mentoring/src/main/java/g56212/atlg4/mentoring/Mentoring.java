package g56212.atlg4.mentoring;

import g56212.atlg4.mentoring.config.ConfigManager;
import g56212.atlg4.mentoring.dto.StudentDto;
import g56212.atlg4.mentoring.repository.RepositoryException;
import g56212.atlg4.mentoring.repository.Repository;
import g56212.atlg4.mentoring.repository.StudentRepository;

import java.io.File;
import java.io.IOException;

public class Mentoring {

    public Mentoring() {

    }

    public static void main(String[] args) {
        Mentoring mentoring = new Mentoring();
        mentoring.checkPath();
        try {
            ConfigManager.getInstance().load();
        } catch (IOException e) {
            System.out.println("Chargement de la configuration impossible " + e.getMessage());
        }

        String author = ConfigManager.getInstance().getProperties("app.author");
        String subscribed = ConfigManager.getInstance().getProperties("file.url");
        System.out.println("Auteur : " + author);
        System.out.println("Subscribed : " + subscribed);

        Repository<Integer, StudentDto> repository = new StudentRepository();
        verification(repository);

    }

    public void checkPath() {
        System.out.println("Chemin courant \t" + new File(".").getAbsolutePath());
        System.out.println("Chemin classe \t" + this.getClass().getResource(".").getPath());
        System.out.println("Chemin jar \t" + new File(getClass().getClassLoader().getResource(".").getFile()));
    }

    private static void verification(Repository<Integer, StudentDto> repository) {
        try {
            System.out.println(repository.getAll() + "\n");
            System.out.println(repository.get(38827));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}
