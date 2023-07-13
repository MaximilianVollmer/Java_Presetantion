package org.example.Listen;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.Listen.Listentypen.Listentyp;
import org.example.Listen.Listentypen.Aufgaben;
import org.example.Listen.Listentypen.Einkaufsitems;
import org.example.Listen.Listentypen.Kontakte;
import org.example.Listen.Listentypen.Notizen;

public class Liste{

    public ArrayList<Listentyp> Einkaufsliste = new ArrayList<Listentyp>(); 
    public ArrayList<Listentyp> Aufgabenliste = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Kontaktbuch = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Notizbuch = new ArrayList<Listentyp>();

    Liste(){
        Listentyp test1 = new Einkaufsitems("Test1", 1, "Das ist ein Test", "Test");
        Listentyp test2 = new Einkaufsitems("Test2", 2, "Das ist noch ein Test", "Test");
        this.Einkaufsliste.add(test1);
        this.Einkaufsliste.add(test2);
        //Hier muss er aus der JSON-Datei die Notizen, Aufgaben, usw. auslesen und dann in die jeweilige Variable einspeichern
    }
    
    public void newItem(String name, int amount, String description, String category){
        Listentyp item = new Einkaufsitems(name, amount, description, category);
        this.Einkaufsliste.add(item);
    }

    public void newTask(String name, int importance, String description){
        Listentyp item = new Aufgaben(importance, name, description);
        this.Aufgabenliste.add(item);
    }

    public void newContact(String first_name, String last_name, int tel_number){
        Listentyp item = new Kontakte(first_name, last_name, tel_number);
        this.Kontaktbuch.add(item);
    }

    public void newNote(String name, String description){
        Listentyp item = new Notizen(name, description);
        this.Notizbuch.add(item);
    }

    public void update_entry(Liste listen, ArrayList<Listentyp> list, int index, String listname){
        System.out.println("[0] Zurück\n[1] Löschen\n[2] Bearbeiten");
        System.out.println(list.get(index).all_informations());
        Scanner scan = new Scanner(System.in);
        String scan_action = scan.nextLine();
        switch(String.valueOf(scan_action)){
            case"0":
                listen.scanner_case(listen, list, listname);
                break;
            case"1":
                list.remove(index);
                listen.scanner_case(listen, list, listname);
                break;
            case"2":
                //Muss ich noch gucken, wie ich das mache
                listen.update_entry(listen, list, index, listname);

        }
        
    }

    public void scanner_case(Liste listen, ArrayList<Listentyp> list, String listname){
        if(list != null && list.size()!=0){
            System.out.println("[0] Zurück");
            for(int index = 0; index<list.size(); index++){
                int schowable_index = index+1;
                System.out.println("["+schowable_index+"]"+list.get(index).get_informations());
            }
            System.out.println("Wählen sie den Eintrag aus, um genauere Informationen zu sehen, ihn zu bearbeiten, oder zu löschen.");
            Scanner scan_one = new Scanner(System.in);
            String action_one = scan_one.nextLine();
            Integer index = Integer.parseInt(String.valueOf(action_one))-1;
            switch(index){
                case -1:              
                    listen.main_func(listen);      
                    break;
            }
            listen.update_entry(listen, list, index, listname);             
        }
        else{
            System.out.println("In dieser Liste sind keine Einträge.");
            System.out.println("[0] Zurück\n[1] Einen neuen Eintrag der Liste hinzufügen.");
            Scanner scan_one = new Scanner(System.in);
            String action_one = scan_one.nextLine();

            switch(String.valueOf(action_one)){
                case "0":
                    listen.main_func(listen);
                    break;
                case "1":
                    Scanner add_item = new Scanner(System.in);

                    switch(listname){
                        case "Einkaufsliste":
                            System.out.println("Name: ");
                            String item_name = add_item.next();

                            System.out.println("Anzahl: ");
                            Integer item_amount = add_item.nextInt();

                            System.out.println("Beschreibung: ");
                            String item_description = add_item.next();

                            System.out.println("Kategorie: ");
                            String item_category = add_item.next();

                            listen.newItem(item_name, item_amount, item_description, item_category);
                            break;
                        case "Aufgabenliste":
                            System.out.println("Name: ");
                            String task_name = add_item.next();

                            System.out.println("Wichtigkeit: ");
                            Integer task_importance = add_item.nextInt();

                            System.out.println("Beschreibung: ");
                            String task_description = add_item.next();
                            
                            listen.newTask(task_name, task_importance, task_description);
                            break;
                        case "Kontaktbuch":
                            System.out.println("Vorname: ");
                            String contact_firstname = add_item.next();

                            System.out.println("Nachname: ");
                            String contact_lastname = add_item.next();

                            System.out.println("Telefonnummer: ");
                            Integer contact_number = add_item.nextInt();
                            
                            listen.newContact(contact_firstname, contact_lastname, contact_number);
                            break;
                        case "Notizbuch":
                            System.out.println("Name: ");
                            String note_name = add_item.next();

                            System.out.println("Beschreibung: ");
                            String note_description = add_item.next();
                            
                            listen.newNote(note_name, note_description);
                            break;
                        default:
                            System.out.println("Das sollte eigentlich nie passieren.");
                            listen.scanner_case(listen, list, listname);
                    }                    
                    break;
                default:
                    System.out.println("Sie müssen schon eine der gennanten Nummern eingeben. Ich schreib die nicht aus Spaß!");
                    listen.scanner_case(listen, list, listname);
            }
            listen.main_func(listen);            
        }
    }

    public void saveChanges(Liste listen){
        System.out.println("So, jetzt sollte der Stuff von den Listen in die JSON-Datei reingepackt werden");
        //Hier muss dann der ganze shit wieder in die JSON-Datei gespeichert werden.
        //Am Besten auch, in dem man nur die Änderungen speichert, aber das wäre wohl nur etwas zusätzliches,
        //Wenn wir noch Zeit dazu haben. Kommt aber darauf an, was die API von dem Ding von Max hergibt. 
    }

    public void main_func(Liste list){
        System.out.println("[0] Zurück\n[1] Öffne die Einkaufsliste\n[2] Öffne Aufgabenliste\n[3] Öffne Kontaktbuch \n[4] Öffne Notizbuch");

        Scanner new_scan = new Scanner(System.in);
        try{
            while(new_scan.hasNextLine()){ 
                String new_action = new_scan.nextLine();
                switch(String.valueOf(new_action)){
                    case "0":
                        new_scan.close();
                        break;
                    case "1":
                        System.out.println(list.Einkaufsliste.getClass().getName());
                        list.scanner_case(list, list.Einkaufsliste,"Einkaufsliste");
                        break;
                    case"2":
                        list.scanner_case(list, list.Aufgabenliste,"Aufgabenliste");
                        break;
                    case"3":
                        list.scanner_case(list, list.Kontaktbuch,"Kontaktbuch");
                        break;
                    case"4":
                        list.scanner_case(list, list.Notizbuch,"Notizbuch");
                        break;
                    default:
                        System.out.println("Bitte geben sie nur einen der oben genannten Werte ein.");
                        list.main_func(list);
                }
            }
        }catch (Exception e) {
            list.saveChanges(list);
        }

    }
    
    public static void main(String[] args) {
        Liste list = new Liste();
        list.main_func(list);
    }
}
