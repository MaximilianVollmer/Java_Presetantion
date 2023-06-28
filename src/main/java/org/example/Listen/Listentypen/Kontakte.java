package org.example.Listen.Listentypen;

public class Kontakte extends Liestentyp{
    public int tel_number;
    
    public Kontakte(String first_name, String last_name, int tel_number){
        this.name = first_name!=null?first_name:""+last_name!=null?last_name:"";
        this.tel_number = tel_number;
    }
}
