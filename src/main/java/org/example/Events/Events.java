package org.example.Events;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.example.Generators.TimeGenerator;

import java.text.SimpleDateFormat;
import java.text.AttributedCharacterIterator.Attribute;
import java.time.LocalDateTime;

public class Events {
    
// Class variables declaration
public String event_name;
public LocalDateTime event_start;
public LocalDateTime event_end;
public String event_priority;

// Constructor to initialize Events object
public Events(String event_name, LocalDateTime event_start, LocalDateTime event_end, String event_priority) {
    this.event_name = event_name;
    this.event_start = event_start;
    this.event_end = event_end;
    this.event_priority = event_priority;
}
 // Method to create a new Event by taking input from the user
   public static Events create_Event() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Geben Sie den Event-Namen ein: ");
        String eventName = inputScanner.nextLine();
        
LocalDateTime Zeit = TimeGenerator.generateDateTime();
       /*   System.out.println("Geben Sie den Event-Start ein : ");
        String eventStart = inputScanner.nextLine();
        LocalDateTime startDateTime = LocalDateTime.parse(eventStart);*/

        System.out.println("Geben Sie das Event-Ende ein  ");
        // String eventEnd = inputScanner.nextLine();
        LocalDateTime endDateTime = TimeGenerator.generateDateTime();

        System.out.println("Geben Sie die Event-Priorität ein: ");
        String eventPriority = inputScanner.nextLine();

        //return new Events(eventName, startDateTime, endDateTime, eventPriority);
        return new Events(eventName,Zeit,endDateTime,eventPriority);
    }

// Method to display event information
public static void event_info(Events event) {
        String patter = "dd/MM/yyyy HH:mm"; 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patter);
    System.out.println("Eventname: " + (event.event_name));
    System.out.println("Eventbeginn: " + dtf.format(event.event_start));
    System.out.println("Eventende: " + dtf.format(event.event_end));
    System.out.println("Priorität: " + event.event_priority);
    System.out.println("Möchten sie eine Erinnerung für dieses Event erstellen? [y/n]");
    Scanner remind= new Scanner(System.in);
    String input= remind.nextLine();
    switch (String.valueOf(input)){
        case "y": 
            Events reminder = new Reminder(event.event_name, event.event_start, event.event_end, event.event_priority);

    }
}

// Method to update the event attributes based on the given attribute name and parameter
public void update_Event(String AttributName, String parameter) {
    // Use a switch statement to update the correct attribute based on the provided attribute name
    switch (AttributName) {
        case "event_name":
            this.event_name = parameter;
            break;
        case "event_start":
            // Parse the parameter string to LocalDateTime and update the event_start attribute
            LocalDateTime start_date = LocalDateTime.parse(parameter);
            this.event_start = start_date;
            break;
        case "event_end":
            // Parse the parameter string to LocalDateTime and update the event_end attribute
            LocalDateTime end_date = LocalDateTime.parse(parameter);
            this.event_end = end_date;
            break;
        case "event_priority":
            // Update the event_priority attribute
            this.event_priority = parameter;
            break;
        default:
            // Handle the case where an incorrect attribute name is provided
            System.out.println("An error occurred: Incorrect attribute name.");
            break;
    }
}


public static void main(String[] args) {
    // create_Event()
    event_info(create_Event());
    // Events test = new Reminder(null, null, null, null);
    // System.out.println());

    //Reminder_info()
}
// Rest of the code stays unchanged
}
