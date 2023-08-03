package org.example;

import org.example.Kalender.Kalender;
import org.example.Kalender.Stundenplan.Stundenplan;
import org.example.Listen.Liste;

import java.util.Scanner;

/**
 * @author Hasan Abouorra, Markus Franzen, Maximilian Vollmer.
 * @version 1.0
 * @category planer
 * @description Dies ist eine Konsolen-App, mit der man Kalender, Listen, und Stundenpläne erstellen kann.
 */
public class Main {

    private static void availableCommands(){
        System.out.println("[0]Verlassen\n[1]Öffne Stundenplan\n[2]Öffne Liste\n[3]Öffne Kalender/Termine");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Stundenplan stundenplan = new Stundenplan();
        Kalender kalender = new Kalender();
        Liste liste = new Liste();

        outerloop:
        while (true) {
            System.out.println("Was willst du machen.");
            availableCommands();
            input = scanner.nextLine();
            switch(input){
                case "0" -> {break outerloop;}
                case "1" -> stundenplan.commandLoop();
                case "2" -> liste.main_func(liste);
                case "3" -> kalender.commandLoop();
                default -> System.out.println("Unbekannter Befehl");
            }

        }

    }
    //Help me
}