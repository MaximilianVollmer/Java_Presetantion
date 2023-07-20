package org.example.Events;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.text.AttributedCharacterIterator.Attribute;
import java.time.LocalDateTime;

public class Events {
    

public String event_name ;
public LocalDateTime event_start ; //strg+d markiert das gleiche um es gleichzeitig zu ändern
public LocalDateTime event_end ;
public String event_priority ;

public Events (String event_name , LocalDateTime event_start , LocalDateTime event_end , String event_priority ){
    this.event_name = event_name;
    this.event_start = event_start;
    this.event_end = event_end;
    this.event_priority = event_priority;
}

public void event_info(){

    System.out.println("Eventname: " + event_name);
    System.out.println("Eventbeginn: " + event_start);
    System.out.println("Eventende: " + event_end);
    System.out.println("Priorität: " + event_priority);
}
    public void update_Event(String AttributName ,String parameter ){
     //  String AttributName = this.getClass().getSimpleName();
        switch(AttributName){
            case "event_name":
                this.event_name = parameter;
                break;

                 case "event_start":
                 LocalDateTime start_date = LocalDateTime.parse(parameter);
                this.event_start = start_date;
                break;

                 case "event_end":
                  LocalDateTime end_date = LocalDateTime.parse(parameter);
                this.event_end= end_date;
                break;
            
                 case "event_priority":
                
                this.event_priority = parameter;
                break;
                 
                default:
            System.out.println("Es liegt ein Fehler vor");
            break;

        }


        // if (time.isBefore(LocalDateTime.now()))
    } 




}
