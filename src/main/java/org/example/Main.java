package org.example;


import Kalender.Kalender;
import Kalender.Stundenplan.Stundenplan;
import org.example.Listen.Liste;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static void availableCommands(){
        System.out.println("[0]Verlassen\n[1]Öffne Stundenplan\n[2]Öffne Liste\n[3]NICHT ÖFFNEN TEST");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
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
}