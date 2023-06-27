package org.example.Listen;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.undo.UndoableEdit;

import org.example.Listen.Listentypen.Aufgaben;
import org.example.Listen.Listentypen.Einkaufsitems;
import org.example.Listen.Listentypen.Kontakte;
import org.example.Listen.Listentypen.Notizen;

public class Liste{

    public ArrayList<Einkaufsitems> Einkaufliste; 
    public ArrayList<Aufgaben> Aufgabenliste;
    public ArrayList<Kontakte> Kontaktbuch;
    public ArrayList<Notizen> Notizbuch;

    Liste(){
        //Hier muss er aus der JSON-Datei die Notizen, Aufgaben, usw. auslesen und dann in die jeweilige Variable einspeichern
    }

    // Kann man evt. noch mit ner switch case und den Objectkeys vereinfachen.
    public void newItem(String name, int amount, String description, String category){
        Einkaufsitems item = new Einkaufsitems(name, amount, description, category);
        this.Einkaufliste.add(item);
    }

    public void newTask(String name, int importance, String description){
        Aufgaben item = new Aufgaben(importance, name, description);
        this.Aufgabenliste.add(item);
    }

    public void newContact(String first_name, String last_name, int tel_number){
        Kontakte item = new Kontakte(first_name, last_name, tel_number);
        this.Kontaktbuch.add(item);
    }

    public void newNote(String name, String description){
        Notizen item = new Notizen(name, description);
        this.Notizbuch.add(item);
    }

    public void deleteNote(int index){
        this.Notizbuch.remove(index-1);
    }

    public void deleteContact(int index){
        this.Kontaktbuch.remove(index-1);
    }

    public void deleteTask(int index){
        this.Aufgabenliste.remove(index-1);
    }

    public void deleteItem(int index){
        this.Einkaufliste.remove(index-1);
    }

    public void scanner_case(Liste listen, ArrayList<Einkaufsitems> list){
        if(list != null && list.size()!=0){
            for(int index = 0; index<=list.size(); index++){
                System.out.println("["+index+1+"]"+list.get(index).get_informations());
            }
            System.out.println("Wählen sie das Item aus, um genauere Informationen zu sehen, es zu bearbeiten, oder zu löschen.");
            Scanner scan_one = new Scanner(System.in);
            String action_one = scan_one.nextLine();
            System.out.println(list.get(Integer.parseInt(String.valueOf(action_one)))); 
            scan_one.close();
        }
        else{
            System.out.println("Sie haben nichts in ihrer Einkaufsliste.");
            System.out.println("[1] Einen neuen Gegenstand der Liste hinzufügen.");
            System.out.println("[2] Zurück");
            Scanner scan_one = new Scanner(System.in);
            String action_one = scan_one.nextLine();

            switch(String.valueOf(action_one)){
                case "1":
                    // Scanner add_item = new Scanner(System.in);
                    // String action_add_item = add_item.nextLine();
                    // Hier muss funktion hin für Eintragen von Name und Beschreibung 
                    // add_item.close();
                    break;
                case "2":
                    listen.main_func(listen);
                    break;
            }
            scan_one.close();
        }
    }

    public void main_func(Liste list){
        System.out.println("[1] Öffne die Einkaufsliste\n[2] Öffne Aufgabenliste\n[3] Öffne Kontaktbuch \n[4] Öffne Notizbuch");

        Scanner new_scan = new Scanner(System.in);
        String new_action = new_scan.nextLine();
        switch(String.valueOf(new_action)){
            case "1":
                list.scanner_case(list, list.Einkaufliste);
                break;
            case"2":
                System.out.println(list.Aufgabenliste);
                break;
            case"3":
                System.out.println(list.Kontaktbuch);
                break;
            case"4":
                System.out.println(list.Notizbuch);
                break;
            default:
                System.out.println("Bitte geben sie nur einen der oben genannten Werte ein.");
        }
        new_scan.close();
    } 

    public static void main(String[] args) {
        Liste list = new Liste();
        list.main_func(list);
    }
}
