package org.example.Events;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder extends Events{
     static LocalDateTime event_date = LocalDateTime.now();
     
  
    public Reminder(String event_name, LocalDateTime event_start, LocalDateTime event_end, String event_priority) {
        super(event_name, event_start, event_end, event_priority);
        //TODO Auto-generated constructor stub
       // You can also convert the difference to minutes, hours, or any other time unit as needed.
        // For example, to get the difference in minutes:
        long diffInMinutes = Duration.between(LocalDateTime.now(), event_start).toMillis();
        Reminder this_old = this;
        //int diffInMinutesAsInt = Math.toIntExact(diffInMinutes);
        System.out.println(diffInMinutes);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
              //  Reminder.Reminder_info(this_old);
              Reminder.Reminder_sleep( event_start, this_old);
            }
        }, diffInMinutes, 60000);

    }

    static void Reminder_info(Events reminder){
        String patter = "dd/MM/yyyy HH:mm"; 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(patter);
        System.out.println("Name des Events: " + reminder.event_name);
         System.out.println("Anfang des Events: " + dtf.format(reminder.event_start));
          System.out.println("Ende des Events: " +dtf.format(reminder.event_end));
           System.out.println("Priorität des Events: " +reminder.event_priority);
            
                

    }

    static void Reminder_sleep(LocalDateTime time, Events rem) {
    if (time.isBefore(LocalDateTime.now())) {
        Reminder_info(rem);
        System.out.println("[0] Erinnerung löschen");
        System.out.println("[1] Erinnerung schlummern");
        System.out.println("[2] Zurück"); // Hinzufügen einer Zurück-Option
        Scanner scan = new Scanner(System.in);
        boolean runner = true ;
        try {
            while (runner) {
                String action = scan.nextLine();
                switch (String.valueOf(action)) {
                    case "0":
                    runner =false;
                        System.out.println("Erinnerung wurde gelöscht");
                        break; // Beende die Timer-Aufgabe und die Methode
                    case "1":
                        System.out.println("Erinnerung wurde um 5 Minuten verlängert");
                        time = time.plusMinutes(5);
                        break;
                    case "2":
                        System.out.println("Zurück");
                        break; // Beende die Schleife und die Methode
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Thread.sleep(5 * 60 * 1000); // Warte 5 Minuten (in Millisekunden)
            Reminder_sleep(time, rem);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

}
