package org.example.Listen.Listentypen;

public class Einkaufsitems {

    public String name;
    public int amount;
    public String description;
    public String category;

    public Einkaufsitems(String name, int amount, String description, String category){
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    public String get_informations(){
        String info = this.name+this.amount;
        return info;
    }
}