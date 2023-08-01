package org.example.Listen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.Listen.Listentypen.Listentyp;
import org.example.JsonManager;

import java.io.FileReader;

import org.example.Listen.Listentypen.Aufgaben;
import org.example.Listen.Listentypen.Einkaufsitems;
import org.example.Listen.Listentypen.Kontakte;
import org.example.Listen.Listentypen.Notizen;

import org.json.simple.*;

public class Liste{


    public ArrayList<Listentyp> Einkaufsliste= new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Aufgabenliste = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Kontaktbuch = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Notizbuch = new ArrayList<Listentyp>();


    /**
     * Connects to the JSONManager.java to get all informations from the json. 
     */
    public Liste(){
        try {
            ArrayList<JSONObject> shopping_list = JsonManager.readJson(".\\Data\\ShoppingList.json");
            shopping_list.forEach(entry -> {
                String name =(String) entry.get("name");
                Integer amount = Integer.valueOf((((String) entry.get("amount"))));
                String description = (String) entry.get("description");
                String category = (String) entry.get("category");

                Listentyp item = new Einkaufsitems(name, amount, description, category);
                this.Einkaufsliste.add(item);
            });
        }catch (Exception e) {
            // System.out.println(e);
        }
        try{
            ArrayList<JSONObject> task_list = JsonManager.readJson(".\\Data\\TaskList.json");
            task_list.forEach(entry -> {
                String name =(String) entry.get("name");
                Integer importance = Integer.valueOf((((String) entry.get("importance"))));
                String description = (String) entry.get("description");
                Listentyp task = new Aufgaben(importance, name, description);
                this.Aufgabenliste.add(task);
            });
        }catch (Exception e) {
            // System.out.println(e);
        }
        try{
            ArrayList<JSONObject> note_book = JsonManager.readJson(".\\Data\\NoteBook.json");
            note_book.forEach(entry -> {
                String name =(String) entry.get("name");
                String informations = (String) entry.get("informations");
                Listentyp note = new Notizen(name, informations);
                this.Notizbuch.add(note);
            });
        }catch (Exception e) {
            // System.out.println(e);
        }
        try{
            ArrayList<JSONObject> contact_book = JsonManager.readJson(".\\Data\\Contactbook.json");
            contact_book.forEach(entry -> {
                String name =(String) entry.get("name");
                Integer tel_number = Integer.valueOf((((String) entry.get("tel_number"))));
                Listentyp contact = new Kontakte(name, tel_number);
                this.Kontaktbuch.add(contact);
            });
        } catch (Exception e) {
            // System.out.println(e);
        }
    }

    public void newItem(String name, int amount, String description, String category){
        Listentyp item = new Einkaufsitems(name, amount, description, category);
        this.Einkaufsliste.add(item);
    }

    public void newTask(String name, int importance, String description){
        Listentyp item = new Aufgaben(importance, name, description);
        this.Aufgabenliste.add(item);
    }

    public void newContact(String name, int tel_number){
        Listentyp item = new Kontakte(name, tel_number);
        this.Kontaktbuch.add(item);
    }

    public void newNote(String name, String description){
        Listentyp item = new Notizen(name, description);
        this.Notizbuch.add(item);
    }

    /**
     * Returns all informations and updates or deletes the entry, if wanted.
     * @param listen
     * @param list
     * @param index
     * @param listname
     */
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

    /**
     * Prints the data from the list.
     * Asks if the client wants to create a new entry.
     * @param listen
     * @param list
     * @param listname
     */


    public void scanner_case(Liste listen, ArrayList<Listentyp> list, String listname){


        if(list != null && list.size()!=0){
            System.out.println("[0] Zurück");
            for(int index = 0; index<list.size(); index++){
                int schowable_index = index+1;
                System.out.println("["+schowable_index+"]"+list.get(index).get_informations());
            }
            Integer list_plus = list.size()+1;
            System.out.println("["+list_plus+"] Erstellen sie einen neuen Eintrag,");
            System.out.println("oder wählen sie einen Eintrag aus, um genauere Informationen zu sehen, ihn zu bearbeiten, oder zu löschen.");
            Scanner scan_one = new Scanner(System.in);
            String action_one = scan_one.nextLine();
            Integer index = Integer.parseInt(String.valueOf(action_one))-1;
            switch(index){
                case -1:              
                    listen.main_func(listen);      
                    break;
            }
            if(index == list.size()){
                listen.newEntry(listen, list, listname);
            }
            else{
                listen.update_entry(listen, list, index, listname);
            }        
        }
        else{
            System.out.println("In dieser Liste sind keine Einträge.");
            System.out.println("[0] Zurück\n[1] Einen neuen Eintrag der Liste hinzufügen.");
            Scanner new_scan = new Scanner(System.in);
            String new_line = new_scan.nextLine();

            switch(String.valueOf(new_line)){
                case "0":
                    listen.main_func(listen);
                    break;
                case "1":
                    listen.newEntry(listen, list, listname);
                    break;
                default:
                    System.out.println("Sie müssen schon eine der gennanten Nummern eingeben. Ich schreib die nicht aus Spaß!");
                    listen.scanner_case(listen, list, listname);
            }         
        }
        listen.main_func(listen);   
    }

    /**
     * Asks for the corresponding attributes 
     * and creates a new entry in the corresponding list.
     * @param listen
     * @param list
     * @param listname
     */
    public void newEntry(Liste listen, ArrayList<Listentyp> list, String listname){
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
                System.out.println("Vor- und Nachname ");
                String contact_name = add_item.next();

                System.out.println("Telefonnummer: ");
                Integer contact_number = add_item.nextInt();
                
                listen.newContact(contact_name, contact_number);
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
                break;
        }  
    }

    /**
     * Converts the lists to JSONArrays of Strings.
     * Sends them to the JsonManager.java to save them.
     */

    public void saveChanges(){
        System.out.println(Einkaufsliste);
        JSONArray shopping_list = new JSONArray();
        JSONArray contact_book = new JSONArray();
        JSONArray note_book = new JSONArray();
        JSONArray task_list = new JSONArray();

        Einkaufsliste.forEach(stuff ->{
            JSONObject placeholer = new JSONObject();
            placeholer.put("name", stuff.get_info("name"));
            placeholer.put("amount", stuff.get_info("amount"));
            placeholer.put("description", stuff.get_info("description"));
            placeholer.put("category", stuff.get_info("category"));
            shopping_list.add(placeholer);
        });
        Kontaktbuch.forEach(stuff ->{
            JSONObject placeholer = new JSONObject();
            placeholer.put("name", stuff.get_info("name"));
            placeholer.put("tel_number", stuff.get_info("tel_number"));
            contact_book.add(placeholer);
        });
        Aufgabenliste.forEach(stuff ->{
            JSONObject placeholer = new JSONObject();
            placeholer.put("name", stuff.get_info("name"));
            placeholer.put("importance", stuff.get_info("importance"));
            placeholer.put("description", stuff.get_info("description"));
            task_list.add(placeholer);
        });
        Notizbuch.forEach(stuff ->{
            JSONObject placeholer = new JSONObject();
            placeholer.put("name", stuff.get_info("name"));
            placeholer.put("informations", stuff.get_info("informations"));
            note_book.add(placeholer);
        });
        try {
            JsonManager.test(".\\Data\\ShoppingList.json",shopping_list.toJSONString());
            JsonManager.test(".\\Data\\ContactBook.json",contact_book.toJSONString());
            JsonManager.test(".\\Data\\TaskList.json",task_list.toJSONString());
            JsonManager.test(".\\Data\\NoteBook.json",note_book.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Asks client wich list he wants to open.
     * Connects to scanner_case with the needed parameter.
     * @param list {type: Liste}
     */
    public void main_func(Liste list){
        System.out.println("[0] Zurück\n[1] Öffne die Einkaufsliste\n[2] Öffne Aufgabenliste\n[3] Öffne Kontaktbuch \n[4] Öffne Notizbuch");

        Scanner new_scan = new Scanner(System.in);

        boolean exit = false;
        try{
            while(!exit){
                String new_action = new_scan.nextLine();
                switch(String.valueOf(new_action)){
                    case "0":
                        list.saveChanges();
                        exit = true;
                        break;
                    case "1":
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
            list.saveChanges();
        }

    }

    public void commandLoop(){
        main_func(this);
    }
    
    public static void main(String[] args) {
        Liste list = new Liste();
        list.main_func(list);
    }
}
