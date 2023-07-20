package Kalender.Stundenplan;

import Kalender.KalenderElements.KalenderElement;
import org.example.Generators.TimeGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Vorlesung extends KalenderElement {

    private String dozent;

    private LocalDateTime endTime;
    public Vorlesung(String name, String dozent){
        date = generateTime();
        this.name = name;
        this.dozent = dozent;
    }
    public Vorlesung(String name){
        date = generateTime();
        super.name = name;
    }

    public void length(int minutes){
        int hours = minutes/60;
        int endMinutes = minutes%60;

        //this.endTime = super.date;
        endTime = date.plusHours(hours).plusMinutes(endMinutes);
    }

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" - HH:mm");
        return this.date.getDayOfWeek().toString().substring(0,3).toUpperCase() + this.date.format(formatter);
    }

    public String getName(){
        return this.name;
    }

    public String getEndDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" - HH:mm");
        return this.endTime.getDayOfWeek().toString().substring(0,3).toLowerCase() + this.endTime.format(formatter);
    }

     public LocalDateTime generateTime(){

        return TimeGenerator.generateWeekdayTime();
    }
}
