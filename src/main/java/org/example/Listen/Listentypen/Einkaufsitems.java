package org.example.Listen.Listentypen;

public class Einkaufsitems extends Listentyp{

    public int amount;
    public String description;
    public String category;

    public Einkaufsitems(String name, int amount, String description, String category){
        System.out.println("TEst");
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    public String get_informations(){
        String info = "Name: "+this.name +"\nAnzahl: "+this.amount;
        return info;
    }
    public String all_informations(){
        String info = "Name: "+this.name +"\nAnzahl: "+this.amount+"\nKategorie: "+this.category+"\nBeschreibung: "+this.description;
        return info;
    }
    public String get_info(String key){
        switch(key){
            case "name":
                return this.name;
            case "amount":
                return String.valueOf(this.amount);
            case "category":
                return this.category;
            case "description":
                return this.description;
            default:
                return "";
        }

    }
}
