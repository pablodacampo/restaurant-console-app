package dev;

import dev.ihm.Menu;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class AppSpringXML {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config-fichier.xml");

        // récupération du bean Menu
        Menu menu = context.getBean(Menu.class);

        menu.afficher();

        // fermeture du Scanner
        context.getBean(Scanner.class).close();

        // fermeture du contexte Spring
        context.close();
    }
}
