package com.extensionrepository;

import com.extensionrepository.entity.Extension;
import com.extensionrepository.entity.Role;
import com.extensionrepository.entity.Tag;
import com.extensionrepository.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;

public class DatabaseTest {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Extension.class)
                .addAnnotatedClass(Tag.class)
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        Extension e = session.get(Extension.class, 15);
        System.out.println(e.getName() + " " + e.getRepositoryLink());

        session.getTransaction().commit();
        session.close();

        /*
        try {
            GitHub gitHub = GitHub.connect();
            GHRepository ghRepository = gitHub.getRepository("PenyoKolev/Extension-Repository")
        } catch (IOException e) {
            e.printStackTrace();
        }

*/
    }
}