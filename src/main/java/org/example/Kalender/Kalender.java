package org.example.Kalender;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import org.example.Events.Events;
import org.example.Kalender.KalenderElements.KalenderElement;

public class Kalender{
    private ArrayList<KalenderElement> kalenderList = new ArrayList<KalenderElement>();
    private final Scanner scanner = new Scanner(System.in);

    public void addElement(){
        kalenderList.add(Events.create_Event());
    }

    private void showElements(){
            String input = "";
            commandloop:
            while(true){
                System.out.println("\nWelches Element?\n[0]Zurück");
                kalenderList.forEach(v -> {
                    System.out.println("["+(kalenderList.indexOf(v)+1)+"]"+((Events)v).event_name);
                });
                input = scanner.nextLine();
                if(Objects.equals(input, "0")){ break commandloop;}
                elementCommands(Integer.parseInt(input)-1);
            }
    }

    private void elementCommands(int index){
        if(index < kalenderList.size()) {
            commandloop:
            while (true) {
                System.out.println("[0]Zurück\n[1]Anzeigen\n[2]Bearbeiten\n[3]Löschen");
                switch (scanner.nextLine()) {
                    case "0" -> {
                        break commandloop;
                    }
                    case "1" -> Events.event_info(((Events)kalenderList.get(index)));
                    case "2" -> {
                        System.out.println("Welchen Parameter möchtest du ändern?");
                        String attribute = scanner.nextLine();
                        System.out.println("Zu welchen Wert?");
                        String parameter = scanner.nextLine();
                        ((Events)kalenderList.get(index)).update_Event(attribute, parameter);
                    }
                    case "3" -> {
                        kalenderList.remove(index);
                        break commandloop;
                    }
                }
            }
        }
        else{
            System.out.println("Eintrag gibt es nicht!");
        }
    }

    private void allCommands(){
        System.out.println("[0]Zurück\n[1]Neuer Termin\n[2]Zeige Termine");
    }

    public void commandLoop(){
        outerloop:
        while(true){
            allCommands();
            switch(scanner.nextLine()){
                case "0" -> {break outerloop;}
                case "1" -> addElement();
                case "2" -> showElements();
            }
        }
    }

}