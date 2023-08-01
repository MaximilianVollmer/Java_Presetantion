package org.example.Listen.Listentypen;

public class Notizen extends Listentyp{
    public String informations;

    public Notizen(String name, String informations){
        this.name = name;
        this.informations = informations;
    }

    public String get_informations(){
        String info = "Name: "+this.name;
        return info;
    }
    public String all_informations(){
        String info = "Name: "+this.name +"\nInhalt: "+this.informations;
        return info;
    }
    public String get_info(String key){
        switch(key){
            case "name":
                return this.name;
            case "informations":
                return this.informations;
            default:
                return "";
        }
    }
}
