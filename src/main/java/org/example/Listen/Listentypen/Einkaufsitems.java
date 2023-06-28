package org.example.Listen.Listentypen;

public class Einkaufsitems extends Liestentyp{

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
        String info = "Name: "+this.name +"\nAnzahl: "+this.amount;
        return info;
    }
}
