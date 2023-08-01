package org.example.Listen.Listentypen;

public class Aufgaben extends Listentyp{
    public int importance;
    public String description;

    public Aufgaben(int importance, String name, String description){
        this.name = name;
        this.importance = importance;
        this.description = description;
    }
    public String get_informations(){
        String info = "Name: "+this.name+"\nWichtigkeit: "+this.importance;
        return info;
    }
    public String all_informations(){
        String info = "Name: "+this.name +"\nWichtigkeit: "+this.importance+"\nBeschreibung: "+this.description;
        return info;
    }
    public String get_info(String key){
        switch(key){
            case "name":
                return this.name;
            case "importance":
                return String.valueOf(this.importance);
            case "description":
                return this.description;
            default:
                return "";
        }
    }
}
