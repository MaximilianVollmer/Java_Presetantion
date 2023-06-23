import java.sql.Date;

public class Events {
    

public String event_name ;
public Date event_start ;
public Date event_end ;
public int event_repetition ;

public Events (String event_name , Date event_start , String event_end , int event_repetition ){
    this.event_name = event_name;
    this.event_start = event_start;
    this.event_end = event_end;
    this.event_repetition = event_repetition;
}

public event_info(){

    System.out.printlen("Eventname: " + event_name);
    System.out.printlen("Eventbeginn: " + event_start);
    System.out.printlen("Eventende: " + event_begin);
    System.out.printlen("Eventwiederholungen: " + event_repetition);
}
    public void update_Event(String parameter, String new_value){
        klassenName = this.getClass().getSimpleName();
        switch(klassenName){
            case "event_name":
                this.event_name = new_value;
                break;

                 case "event_start":
                this.event_start = new_value;
                break;

                 case "event_end":
                this.event_end= new_value;
                break;
            
                 case "event_repetition":
                this.event_repetition = new_value;
                break;
                 
                default:
            System.out.println("Es liegt ein Fehler vor");
            break;

        }
    } 




}
