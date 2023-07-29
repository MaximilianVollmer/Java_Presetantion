package org.example.Listen.Listentypen;

public class Kontakte extends Listentyp{
    public int tel_number;
    
    public Kontakte(String name, int tel_number){
        this.name = name;
        this.tel_number = tel_number;
    }
    
    public String get_informations(){
        String info = "Name: "+this.name;
        return info;
    }
    public String all_informations(){
        String info = "Name: "+this.name +"\nTelefonnummer: "+this.tel_number;
        return info;
    }
    public String get_info(String key){
        switch(key){
            case "name":
                return this.name;
            case "tel_number":
                return String.valueOf(this.tel_number);
            default:
                return "";
        }
    }
}
