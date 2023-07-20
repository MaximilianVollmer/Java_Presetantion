package org.example.Events;
import java.time.format.DateTimeFormatter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Reminder extends Events {
     static LocalDateTime event_date = LocalDateTime.now();

  
    public Reminder(String event_name, LocalDateTime event_start, LocalDateTime event_end, String event_priority) {
        super(event_name, event_start, event_end, event_priority);
        //TODO Auto-generated constructor stub
    }

    static void Reminder_info(Events reminder){
        String patter = "dd/MM/yyyy HH:mm"; 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patter);
        System.out.println("Name des Events: " + reminder.event_name);
         System.out.println("Anfang des Events: " + dtf.format(reminder.event_start));
          System.out.println("Ende des Events: " +dtf.format(reminder.event_end));
           System.out.println("Priorität des Events: " +reminder.event_priority);
            
                

    }

    // static  DateTimeFormatter dtf = DateTimeFormatter.ofPattern();

       
    static void reminder_sleep (LocalDateTime time, Events rem){
       // String patter = "dd/MM/yyyy HH:mm";
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patter);
        //event_date = time;

        if (time.isBefore(LocalDateTime.now())){
            //System.out.println(dtf.format(time));
            time.plusMinutes(Long.parseLong("1"));
           // System.out.println(LocalDateTime.now().plusMinutes(Long.parseLong("5")));
         //   reminder_sleep(LocalDateTime.now().plusMinutes(Long.parseLong("5")));
         // case 1 : true = sleeper +5 min
             // Rekursiver Aufruf der Methode nach 5 Minuten
            Reminder_info(rem);
            try {
                Thread.sleep(1 * 60 * 1000); // Warte 5 Minuten (in Millisekunden)
    
                reminder_sleep(time,rem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//syso
       
    }

    public static void main(String[] args){
        //  System.out.println( LocalDateTime.now());
         //reminder_sleep( LocalDateTime.now());

         Events test = new Events("test",LocalDateTime.parse("20/07/2023 17:14" , DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), LocalDateTime.parse("20/07/2023 18:00" , DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "high");
        // reminder_sleep();

        // System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
           Events reminder =  new Reminder(test.event_name, test.event_start, test.event_end, test.event_priority);

        reminder_sleep(reminder.event_start, reminder);
        
        }
         
    }
