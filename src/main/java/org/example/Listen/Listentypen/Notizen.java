package org.example.Listen.Listentypen;

public class Notizen extends Listentyp{
    public String informations;

    public Notizen(String name, String informations){
        this.name = name;
        this.informations = informations;
    }
    public String all_informations(){
        String info = "Name: "+this.name +"\nInhalt: "+this.informations;
        return info;
    }
}
