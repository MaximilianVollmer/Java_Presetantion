package org.example.Listen;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.example.Listen.Listentypen.Listentyp;
import org.example.JsonManager;


import java.io.FileReader;


import java.util.ArrayList;
import java.util.Scanner;

import org.example.Listen.Listentypen.Listentyp;

import org.example.Listen.Listentypen.Aufgaben;
import org.example.Listen.Listentypen.Einkaufsitems;
import org.example.Listen.Listentypen.Kontakte;
import org.example.Listen.Listentypen.Notizen;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;



public class Liste{


    public ArrayList<Listentyp> Einkaufsliste= new ArrayList<Listentyp>();

    public ArrayList<Listentyp> Einkaufsliste = new ArrayList<Listentyp>(); 

    public ArrayList<Listentyp> Aufgabenliste = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Kontaktbuch = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Notizbuch = new ArrayList<Listentyp>();


    public ArrayList<Listentyp> Einkaufsliste_old = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Aufgabenliste_old = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Kontaktbuch_old = new ArrayList<Listentyp>();
    public ArrayList<Listentyp> Notizbuch_old = new ArrayList<Listentyp>();

    public Liste(){

        try {
            ArrayList<JSONObject> shopping_list = JsonManager.readJson(".\\Data\\DATA.json", "shopping_list");
            shopping_list.forEach(entry -> {
                String name =(String) entry.get("name");
                Integer amount = Integer.valueOf((((String) entry.get("amount"))));
                String description = (String) entry.get("description");
                String category = (String) entry.get("category");

                Einkaufsitems item = new Einkaufsitems(name, amount, description, category);
                this.Einkaufsliste.add(item);
            });
            this.Einkaufsliste_old = this.Einkaufsliste;
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    


    Liste(){

        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(".\\Data\\DATA.json");
            JSONArray array = (JSONArray) parser.parse(reader);
            array.forEach(object -> {
                JSONObject js_obj = (JSONObject) object;
                String tableName = "table";
                try{
                    JSONObject table = (JSONObject) js_obj.get(tableName);
                    
                    String name = (String) table.get("name");
                    int amount = Integer.parseInt((String) table.get("amount"));
                    String description = (String) table.get("description");
                    String category = (String) table.get("category");
                    Listentyp einkaufsitem = new Einkaufsitems(name, amount, description, category);
                    this.Einkaufsliste.add(einkaufsitem);
                }catch(Exception e){
                    
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public void newContact(String first_name, String last_name, int tel_number){
        Listentyp item = new Kontakte(first_name, last_name, tel_number);
        this.Kontaktbuch.add(item);
    }

    public void newNote(String name, String description){
        Listentyp item = new Notizen(name, description);
        this.Notizbuch.add(item);
    }

    
    /**
     * Asks the client what he wants to do with the data.
     * Does it or connects to the function wich does it.
     * @param listen {type: Liste}
     * @param list {type: ArrayList<Listentyp>} The list the client was communicating with.
     * @param index {type: Int} The index of the entry the client wants to communicate with.
     * @param listname {type: String} The name of the subclass from Listentyp
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
     * Checks if there are entries in the list. 
     * Connects to the corresponding function.
     * @param listen {type: Liste}
     * @param list {type: ArrayList<Listentype>} The list the client wonts to communicate with
     * @param listname {type: String} The name of the subclass fro Listentyp
     */


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


    /**
     * Writes the lists in the JSON data
     * @param listen {type: Liste}
     */



    public void saveChanges(Liste listen){
        System.out.println("So, jetzt sollte der Stuff von den Listen in die JSON-Datei reingepackt werden");
        String old_list = this.Einkaufsliste_old.toString();
        // .map(Listentyp::toString).collect(Collectors.joining(", "));
        // Listentyp listString = Einkaufsliste_old.stream();
        System.out.println(Einkaufsliste);
        JSONObject placeholer = new JSONObject();
        JSONArray liste = new JSONArray();
        Einkaufsliste.forEach(stuff ->{
            System.out.println(stuff);

            placeholer.put("name", stuff.get_info("name"));
            placeholer.put("amount", stuff.get_info("amount"));
            placeholer.put("description", stuff.get_info("description"));
            placeholer.put("category", stuff.get_info("category"));
            
            liste.add(placeholer);
        });
        JSONObject placeholer_old = new JSONObject();
        JSONArray liste_old = new JSONArray();
        Einkaufsliste_old.forEach(stuff ->{
            System.out.println(stuff);

            placeholer_old.put("name", stuff.get_info("name"));
            placeholer_old.put("amount", stuff.get_info("amount"));
            placeholer_old.put("description", stuff.get_info("description"));
            placeholer_old.put("category", stuff.get_info("category"));
            
            liste_old.add(placeholer_old);
        });
        System.out.println(liste);
        // System.out.println(Einkaufsliste_old.stream().map(Listentyp::toString).collect(Collectors.joining(", ")));
        JsonManager.doIt(".\\Data\\DATA.json", liste_old.toString(), liste.toString());
    }



    public void saveChanges(Liste listen){
        System.out.println("So, jetzt sollte der Stuff von den Listen in die JSON-Datei reingepackt werden");
        //Hier muss dann der ganze shit wieder in die JSON-Datei gespeichert werden.
        //Am Besten auch, in dem man nur die Änderungen speichert, aber das wäre wohl nur etwas zusätzliches,
        //Wenn wir noch Zeit dazu haben. Kommt aber darauf an, was die API von dem Ding von Max hergibt. 
    }


    /**
     * Asks client wich list he wants to open.
     * Connects to scanner_case with the needed parameter.
     * @param list {type: Liste}
     */



  
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
