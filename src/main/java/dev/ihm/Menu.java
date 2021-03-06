package dev.ihm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dev.exception.PlatException;
import dev.ihm.options.IOptionMenu;
import dev.service.IPlatService;


@Controller
public class Menu {

    private Map<Integer, IOptionMenu> actions = new HashMap<>();

    private String menu;
    private Scanner scanner;

    
//    public Menu(Scanner scanner, @Qualifier("platServiceVersion2") IPlatService service ) {
//        actions.put(1, new OptionListerPlats(service));
//        actions.put(2, new OptionAjouterPlat(scanner, service));
//        actions.put(99, new OptionTerminer());
//        this.scanner = scanner;
//    }
    
    @Autowired
    public Menu(Scanner scanner, IPlatService service, List<IOptionMenu> optionMenus) {

        optionMenus.sort(Comparator.comparing(IOptionMenu::getPoids)); // On tri par ordre croissant de poids, le poids le plus faible est afficher en premier

        int index = 1;
        for (IOptionMenu optionMenu: optionMenus) {
            actions.put(index, optionMenu);   
            index++;
        }

        this.scanner = scanner;
    }


    public void afficher() {

        boolean continuer = true;

        while (continuer) {

            System.out.println(getMenuTexte());

            int choix = this.scanner.nextInt();

            try {
                this.actions.get(choix).executer();
            } catch (PlatException e) {
                continuer = false;
                System.out.println(e.getMessage());
            }
        }
    }

    private String getMenuTexte() {
        if (menu == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("** Restaurant Console App **");
            sb.append("\n");
            this.actions.forEach((index, option) -> {
                sb.append(index);
                sb.append(". ");
                sb.append(option.getTitre());
                sb.append("\n");
            });
            this.menu = sb.toString();
        }
        return this.menu;
    }
}
