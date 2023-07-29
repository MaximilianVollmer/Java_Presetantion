package org.example.Listen.Listentypen;

public class Listentyp extends Object {
    protected String name;

    /**
     * Gets a few informations
     * @return {String} Containing those informations
     */
    public String get_informations(){
        String info = "Name: "+this.name;
        return info;
    }

    /**
     * Gets all the informations
     * @return {String} Containing those informations
     */
    public String all_informations(){
        String info = "Name: "+this.name;
        return info;
    }

    /**
     * Returning one information
     * @param key
     * @return {String} Containing this information
     */
    public String get_info(String key){
        return "";
    }
}
