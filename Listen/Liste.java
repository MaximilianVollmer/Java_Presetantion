package Java_Presetantion.Listen;
import java.util.ArrayList;

import Java_Presetantion.Listen.Listentypen.Aufgaben;
import Java_Presetantion.Listen.Listentypen.Einkaufsitems;
import Java_Presetantion.Listen.Listentypen.Kontakte;
import Java_Presetantion.Listen.Listentypen.Notizen;

public class Liste{

    public ArrayList<Einkaufsitems> Einkaufliste; 
    public ArrayList<Aufgaben> Aufgabenlite;
    public ArrayList<Kontakte> Kontaktbuch;
    public ArrayList<Notizen> Notizbuch;

    Liste(){

    }

    void newItem(String name, int amount, String description, String category){
        Einkaufsitems item = new Einkaufsitems(name, amount, description, category);
        this.Einkaufliste.add(item);
    }

    void deleteItem(int index){
        this.Einkaufliste.remove(index);
    }

    public static void main(String[] args) {
        
    }
}
