package org.example.Listen.Listentypen;


public class Listentyp {

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

<<<<<<< HEAD
    /**
     * Returning one information
     * @param key
     * @return {String} Containing this information
     */
=======
>>>>>>> 03a3702d5344840ee5e60eaf0c1083e38b1ce899
    public String get_info(String key){
        return "";
    }

}
