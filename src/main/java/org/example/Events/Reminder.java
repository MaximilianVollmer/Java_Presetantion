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
        int diffInMinutesAsInt = Math.toIntExact(diffInMinutes);
        System.out.println(diffInMinutesAsInt);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Reminder.Reminder_info(this_old);
            }
        }, diffInMinutesAsInt, 60000);

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
            System.out.println("[0] Erinnerung löschen");
            System.out.println("[1] Erinnerung schlummern");
            Scanner scan = new Scanner(System.in);
            try{
                while(scan.hasNextLine()){
                    String action = scan.nextLine();
                    switch(String.valueOf(action)){

                        case "0":
                        System.out.println("Erinnerung wurde gelöscht");
                            break;
                        case "1" :
                        System.out.println("Erinnerung wurde um 5 Minuten verlängert");
                        reminder_sleep(time.plusMinutes(5), rem);
                        
                        break;




                    }
                }
            } 
            catch(Exception e) {
                System.out.println(e);
            }

            try {
                Thread.sleep(1 * 60 * 1000); // Warte 5 Minuten (in Millisekunden)
    
                reminder_sleep(time,rem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//syso
       
    }

   public static void main(String[] args) {
        //  System.out.println( LocalDateTime.now());
         //reminder_sleep( LocalDateTime.now());

         Events test = new Events("test",LocalDateTime.parse("27/07/2023 19:52" , DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), LocalDateTime.parse("20/07/2023 19:00" , DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), "high");
        // reminder_sleep();

        // System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
           Events reminder =  new Reminder(test.event_name, test.event_start, test.event_end, test.event_priority);

        reminder_sleep(reminder.event_start, reminder);
        
        }

    }
