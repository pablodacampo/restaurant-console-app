package dev.presentation;

import java.util.Scanner;

import org.springframework.stereotype.Controller;

import dev.entite.Plat;
import dev.service.IPlatService;

@Controller
public class Ihm {

    private IPlatService iPlatService;
    private Scanner scanner;

    public Ihm(IPlatService iPlatService, Scanner scanner) {
        this.iPlatService = iPlatService;
        this.scanner = scanner;
    }

    
    public void demarrer() {
        System.out.println("Saisie : ");
        String saisie = this.scanner.next();

        this.iPlatService.ajouterPlat(saisie, null);

        System.out.println("Affichage de la liste");
        for (Plat texte : this.iPlatService.listerPlats()) {
            System.out.println(texte);
        }

        System.out.println("Les plats");
        for (Plat plat : this.iPlatService.listerPlats()) {
            System.out.println("prix="+plat.getPrixEnCentimesEuros()+ " nom=" + plat.getNom());
        }
    }
}
