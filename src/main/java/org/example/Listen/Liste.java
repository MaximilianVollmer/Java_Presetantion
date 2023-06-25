package org.example.Listen;
import java.util.ArrayList;
import java.util.Scanner;

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
    void newItem(String name, int amount, String description, String category){
        Einkaufsitems item = new Einkaufsitems(name, amount, description, category);
        this.Einkaufliste.add(item);
    }

    void newTask(String name, int importance, String description){
        Aufgaben item = new Aufgaben(importance, name, description);
        this.Aufgabenliste.add(item);
    }

    void newContact(String first_name, String last_name, int tel_number){
        Kontakte item = new Kontakte(first_name, last_name, tel_number);
        this.Kontaktbuch.add(item);
    }

    void newNote(String name, String description){
        Notizen item = new Notizen(name, description);
        this.Notizbuch.add(item);
    }

    void deleteNote(int index){
        this.Notizbuch.remove(index-1);
    }

    void deleteContact(int index){
        this.Kontaktbuch.remove(index-1);
    }

    void deleteTask(int index){
        this.Aufgabenliste.remove(index-1);
    }

    void deleteItem(int index){
        this.Einkaufliste.remove(index-1);
    }

    public static void main(String[] args) {
        Liste list = new Liste();

        System.out.println("[1] Öffne die Einkaufsliste\n[2] Öffne Aufgabenliste\n[3] Öffne Kontaktbuch \n[4] Öffne Notizbuch");

        Scanner new_scan = new Scanner(System.in);
        String new_action = new_scan.nextLine();
        switch(String.valueOf(new_action)){
            case "1":
                System.out.println(list.Einkaufliste);
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
}
